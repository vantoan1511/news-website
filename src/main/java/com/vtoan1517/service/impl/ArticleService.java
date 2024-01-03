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
import com.vtoan1517.service.IArticleService;
import com.vtoan1517.utils.CollectionMapper;
import com.vtoan1517.utils.SlugGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ArticleService implements IArticleService {

    private final ArticleRepository articleRepository;

    private final AccessRepository accessRepository;

    private final CategoryRepository categoryRepository;

    private final StatusRepository statusRepository;

    private final CollectionMapper mapper = new CollectionMapper();

    private final MessageSource messageSource;

    private Article article;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, AccessRepository accessRepository,
                          CategoryRepository categoryRepository, StatusRepository statusRepository,
                          MessageSource messageSource) {
        this.articleRepository = articleRepository;
        this.accessRepository = accessRepository;
        this.categoryRepository = categoryRepository;
        this.statusRepository = statusRepository;
        this.messageSource = messageSource;
    }

    @Override
    public List<ArticleDTO> findAll() {
        return mapper.map(articleRepository.findAll(), ArticleDTO.class);
    }

    @Override
    public List<ArticleDTO> findAll(Pageable pageable) {
        return mapper.map(articleRepository.findAllByStatusCodeNot("trash", pageable), ArticleDTO.class);
    }

    @Override
    public List<ArticleDTO> findAllByAuthor(String author, Pageable pageable) {
        return mapper.map(articleRepository.findAllByStatusCodeNotAndCreatedBy("trash", author, pageable), ArticleDTO.class);
    }

    @Override
    public List<ArticleDTO> findAllByFeaturedAndAuthor(boolean featured, String author, Pageable pageable) {
        return mapper.map(articleRepository.findAllByFeaturedAndCreatedBy(featured, author, pageable), ArticleDTO.class);
    }

    @Override
    public List<ArticleDTO> findAllByStatusCode(String statusCode, Pageable pageable) {
        return mapper.map(articleRepository.findAllByStatusCode(statusCode, pageable), ArticleDTO.class);
    }

    @Override
    public List<ArticleDTO> findAllByStatusCodeAndAuthor(String statusCode, String author, Pageable pageable) {
        return mapper.map(articleRepository.findAllByStatusCodeAndCreatedBy(statusCode, author, pageable), ArticleDTO.class);
    }

    @Override
    public List<ArticleDTO> findAllByFeatured(boolean featured, Pageable pageable) {
        return mapper.map(articleRepository.findAllByFeatured(featured, pageable), ArticleDTO.class);
    }

    @Override
    public List<ArticleDTO> findAllByStatusCodeAndFeatured(String statusCode, boolean featured, Pageable pageable) {
        return mapper.map(articleRepository.findAllByStatusCodeAndFeatured(statusCode, featured, pageable), ArticleDTO.class);
    }

    @Override
    public ArticleDTO findById(long id) throws ArticleNotFoundException {
        try {
            return mapper.map(articleRepository.findById(id), ArticleDTO.class);
        } catch (Exception e) {
            throw new ArticleNotFoundException(messageSource.getMessage("article.id.notfound", null, null) + id);
        }
    }

    @Override
    public ArticleDTO findBySlug(String slug) throws ArticleNotFoundException {
        article = articleRepository.findBySlug(slug);

        if (article == null) {
            throw new ArticleNotFoundException(messageSource.getMessage("article.slug.notfound", null, null) + slug);
        }

        return mapper.map(article, ArticleDTO.class);
    }

    @Override
    public ArticleDTO findBySlugAndStatus(String slug, String status) throws ArticleNotFoundException {
        article = articleRepository.findBySlugAndStatusCode(slug, status);
        if (Objects.isNull(article)) {
            throw new ArticleNotFoundException("Bài viết không tồn tại hoặc không còn trên hệ thống");
        }
        return mapper.map(article, ArticleDTO.class);
    }

    @Override
    @Transactional
    public ArticleDTO save(ArticleDTO articleDTO) {
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
    public ArticleDTO publish(long id) {
        ArticleDTO articleDTO = new ArticleDTO();
        article = articleRepository.findById(id);
        if (article.getStatus().getCode().equalsIgnoreCase("draft")) {
            Status statusEntity = statusRepository.findByCode("pending");
            article.setStatus(statusEntity);
            article = articleRepository.save(article);
            articleDTO = mapper.map(article, ArticleDTO.class);
        }
        return articleDTO;
    }

    @Override
    @Transactional
    public ArticleDTO approve(long id) {
        ArticleDTO articleDTO = new ArticleDTO();
        article = articleRepository.findById(id);
        if (article.getStatus().getCode().equalsIgnoreCase("pending")) {
            Status status = statusRepository.findByCode("published");
            article.setStatus(status);
            article.setPublishedDate(new Date());
            article = articleRepository.save(article);
            articleDTO = mapper.map(article, ArticleDTO.class);
        }
        return articleDTO;
    }

    @Override
    @Transactional
    public ArticleDTO refuse(long id) {
        ArticleDTO articleDTO = new ArticleDTO();
        article = articleRepository.findById(id);
        if (article != null && article.getStatus().getCode().equalsIgnoreCase("pending")) {
            Status statusEntity = statusRepository.findByCode("draft");
            article.setStatus(statusEntity);
            article = articleRepository.save(article);
            articleDTO = mapper.map(article, ArticleDTO.class);
        }
        return articleDTO;
    }

    @Override
    @Transactional
    public ArticleDTO unpublish(long id) throws ArticleNotFoundException {
        ArticleDTO articleDTO = new ArticleDTO();
        article = articleRepository.findById(id);
        if (article != null) {
            String currentStatus = article.getStatus().getCode();
            if (currentStatus.equalsIgnoreCase("pending") || currentStatus.equalsIgnoreCase("published")) {
                Status statusEntity = statusRepository.findByCode("draft");
                article.setStatus(statusEntity);
                article = articleRepository.save(article);
                mapper.map(article, ArticleDTO.class);
            }
            return articleDTO;
        } else {
            throw new ArticleNotFoundException(
                    messageSource.getMessage("article.id.notfound", null, null) + id);
        }
    }

    @Override
    @Transactional
    public ArticleDTO restore(long id) {
        ArticleDTO articleDTO = new ArticleDTO();
        article = articleRepository.findById(id);
        if (article.getStatus().getCode().equalsIgnoreCase("trash")) {
            Status statusEntity = statusRepository.findByCode("draft");
            article.setStatus(statusEntity);
            article = articleRepository.save(article);
            mapper.map(article, ArticleDTO.class);
        }
        return articleDTO;
    }

    @Override
    public boolean isUniqueSlug(String slug) {
        return articleRepository.findBySlug(slug) == null;
    }

    @Override
    @Transactional
    public ArticleDTO trash(long id) throws ArticleNotFoundException {
        article = articleRepository.findById(id);

        if (article == null) {
            throw new ArticleNotFoundException(
                    messageSource.getMessage("article.id.notfound", null, null) + id);
        }

        Status statusEntity = statusRepository.findByCode("trash");
        article.setStatus(statusEntity);
        article.setFeatured(false);
        article = articleRepository.save(article);
        return mapper.map(article, ArticleDTO.class);
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

    @Override
    public long getTotalItems(String author) {
        return articleRepository.countAllByCreatedByAndStatusCodeNotOrAccessCode(author, "trash", "public");
    }

    @Override
    public long getTotalItemsExceptTrash() {
        return articleRepository.countAllByStatusCodeNot("trash");
    }
}
