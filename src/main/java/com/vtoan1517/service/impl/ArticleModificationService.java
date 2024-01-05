package com.vtoan1517.service.impl;

import com.vtoan1517.dto.ArticleDTO;
import com.vtoan1517.entity.Access;
import com.vtoan1517.entity.Article;
import com.vtoan1517.entity.Category;
import com.vtoan1517.entity.Status;
import com.vtoan1517.exception.ArticleNotFoundException;
import com.vtoan1517.exception.MethodNotAllowException;
import com.vtoan1517.repository.AccessRepository;
import com.vtoan1517.repository.ArticleRepository;
import com.vtoan1517.repository.CategoryRepository;
import com.vtoan1517.repository.StatusRepository;
import com.vtoan1517.service.IArticleModificationService;
import com.vtoan1517.utils.CollectionMapper;
import com.vtoan1517.utils.SecurityUtils;
import com.vtoan1517.utils.SlugGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ArticleModificationService implements IArticleModificationService {

    private final ArticleRepository articleRepository;

    private final AccessRepository accessRepository;

    private final CategoryRepository categoryRepository;

    private final StatusRepository statusRepository;

    private final CollectionMapper mapper = new CollectionMapper();

    private final MessageSource messageSource;

    private Article article;

    @Autowired
    public ArticleModificationService(ArticleRepository articleRepository, AccessRepository accessRepository,
                                      CategoryRepository categoryRepository, StatusRepository statusRepository,
                                      MessageSource messageSource) {
        this.articleRepository = articleRepository;
        this.accessRepository = accessRepository;
        this.categoryRepository = categoryRepository;
        this.statusRepository = statusRepository;
        this.messageSource = messageSource;
    }

    @Override
    @Transactional
    public ArticleDTO save(ArticleDTO articleDTO) throws MethodNotAllowException {
        Access accessEntity = accessRepository.findByCode(articleDTO.getAccessCode());
        Category categoryEntity = categoryRepository.findByCode(articleDTO.getCategoryCode());
        Status statusEntity = statusRepository.findByCode(articleDTO.getStatusCode());
        Article oldArticle = new Article();
        article = mapper.map(articleDTO, Article.class);

        article.setAccess(accessEntity);
        article.setCategory(categoryEntity);
        article.setStatus(statusEntity);

        if (articleDTO.getId() != 0) {
            oldArticle = articleRepository.findById(articleDTO.getId());
            String currentStatus = oldArticle.getStatus().getCode();

            if (currentStatus.equals(Status.STATUS_PENDING) || currentStatus.equals(Status.STATUS_PUBLISHED))
                throw new MethodNotAllowException("Bài viết này hiện không thể sửa");

            article.setCreatedBy(oldArticle.getCreatedBy());
            article.setCreatedDate(oldArticle.getCreatedDate());
        }

        String newSlug = SlugGenerator.slugify.slugify(articleDTO.getSlug());
        String oldSlug = oldArticle.getSlug();

        if (!newSlug.equals(oldSlug)) {
            if (newSlug.isBlank()) {
                newSlug = SlugGenerator.generateUniqueSlug(articleDTO.getTitle());
            }
            if (!isUniqueSlug(newSlug)) {
                newSlug = SlugGenerator.generateUniqueSlug(newSlug);
            }
        }
        article.setSlug(newSlug);

        article = articleRepository.save(article);
        articleDTO = mapper.map(article, articleDTO.getClass());
        return articleDTO;
    }

    @Override
    @Transactional
    public void publish(long id) throws ArticleNotFoundException {
        article = articleRepository.findById(id);
        if (article == null) throw new ArticleNotFoundException("Bài viết không tồn tại hoặc không còn nữa");

        if (article.getStatus().getCode().equalsIgnoreCase("draft")) {
            Status statusEntity = statusRepository.findByCode("pending");
            article.setStatus(statusEntity);
            article = articleRepository.save(article);
        }
    }

    @Override
    @Transactional
    public void approve(long id) throws ArticleNotFoundException {
        article = articleRepository.findById(id);
        if (article == null) throw new ArticleNotFoundException("Bài viết không tồn tại hoặc không còn nữa");

        if (article.getStatus().getCode().equalsIgnoreCase("pending")) {
            Status status = statusRepository.findByCode("published");
            article.setStatus(status);
            article.setPublishedDate(new Date());
            article = articleRepository.save(article);
        }
    }

    @Override
    @Transactional
    public void refuse(long id) throws ArticleNotFoundException {
        article = articleRepository.findById(id);
        if (article == null) throw new ArticleNotFoundException("Bài viết không tồn tại hoặc không còn nữa");

        if (article.getStatus().getCode().equalsIgnoreCase("pending")) {
            Status statusEntity = statusRepository.findByCode("draft");
            article.setStatus(statusEntity);
            article = articleRepository.save(article);
        }
    }

    @Override
    @Transactional
    public void reject(long id) throws ArticleNotFoundException {
        article = articleRepository.findById(id);
        if (article == null) throw new ArticleNotFoundException("Bài viết không tồn tại hoặc không còn nữa");

        String currentStatus = article.getStatus().getCode();
        if (currentStatus.equalsIgnoreCase("pending") || currentStatus.equalsIgnoreCase("published")) {
            Status statusEntity = statusRepository.findByCode("draft");
            article.setStatus(statusEntity);
            article = articleRepository.save(article);
        }
    }

    @Override
    @Transactional
    public void restore(long id) throws ArticleNotFoundException {
        article = articleRepository.findById(id);
        if (article == null) throw new ArticleNotFoundException("Bài viết không tồn tại hoặc không còn nữa");

        if (article.getStatus().getCode().equalsIgnoreCase("trash")) {
            Status statusEntity = statusRepository.findByCode("draft");
            article.setStatus(statusEntity);
            article = articleRepository.save(article);
        }
    }

    @Override
    @Transactional
    public void trash(long id) throws ArticleNotFoundException {
        article = articleRepository.findById(id);
        if (article == null) throw new ArticleNotFoundException("Bài viết không tồn tại hoặc không còn nữa");

        Status statusEntity = statusRepository.findByCode("trash");
        article.setStatus(statusEntity);
        article.setFeatured(false);
        article = articleRepository.save(article);
    }

    @Override
    @Transactional
    public void trash(long[] ids) {
        Status status = statusRepository.findByCode("trash");
        for (long id : ids) {
            article = articleRepository.findById(id);
            article.setStatus(status);
            article.setFeatured(false);
            articleRepository.save(article);
        }
    }

    @Override
    @Transactional
    public void delete(long[] ids) throws ArticleNotFoundException, MethodNotAllowException {
        for (long id : ids) {
            this.delete(id);
        }
    }

    @Override
    @Transactional
    public void delete(long id) throws ArticleNotFoundException, MethodNotAllowException {
        article = articleRepository.findById(id);

        if (article == null) {
            throw new ArticleNotFoundException(
                    messageSource.getMessage("article.id.notfound", null, null) + id);
        }
        if (!article.getStatus().getCode().equalsIgnoreCase("trash")) {
            throw new MethodNotAllowException(
                    messageSource.getMessage("article.delete.notallowed", null, null) + id);
        }
        articleRepository.delete(id);
    }

    private boolean isUniqueSlug(String slug) {
        return articleRepository.findBySlug(slug) == null;
    }
}
