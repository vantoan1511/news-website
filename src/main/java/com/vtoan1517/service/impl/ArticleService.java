package com.vtoan1517.service.impl;

import com.vtoan1517.dto.ArticleDTO;
import com.vtoan1517.entity.AccessEntity;
import com.vtoan1517.entity.ArticleEntity;
import com.vtoan1517.entity.CategoryEntity;
import com.vtoan1517.entity.StatusEntity;
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

    private ArticleEntity articleEntity;

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
    public ArticleDTO findById(long id) {
        return mapper.map(articleRepository.findById(id), ArticleDTO.class);
    }

    @Override
    public ArticleDTO findBySlug(String slug) throws ArticleNotFoundException {
        articleEntity = articleRepository.findBySlug(slug);

        if (articleEntity == null) {
            throw new ArticleNotFoundException(messageSource.getMessage("article.slug.notfound", null, null) + slug);
        }

        return mapper.map(articleEntity, ArticleDTO.class);
    }

    @Override
    public ArticleDTO findBySlugAndStatus(String slug, String status) throws ArticleNotFoundException {
        articleEntity = articleRepository.findBySlugAndStatusCode(slug, status);
        if (Objects.isNull(articleEntity)) {
            throw new ArticleNotFoundException("Bài viết không tồn tại hoặc không còn trên hệ thống");
        }
        return mapper.map(articleEntity, ArticleDTO.class);
    }

    @Override
    @Transactional
    public ArticleDTO save(ArticleDTO articleDTO) {
        AccessEntity accessEntity = accessRepository.findByCode(articleDTO.getAccessCode());
        CategoryEntity categoryEntity = categoryRepository.findByCode(articleDTO.getCategoryCode());
        StatusEntity statusEntity = statusRepository.findByCode(articleDTO.getStatusCode());
        ArticleEntity oldArticle = new ArticleEntity();
        articleEntity = mapper.map(articleDTO, ArticleEntity.class);

        articleEntity.setAccess(accessEntity);
        articleEntity.setCategory(categoryEntity);
        articleEntity.setStatus(statusEntity);

        if (articleDTO.getId() != 0) {
            oldArticle = articleRepository.findById(articleDTO.getId());
            articleEntity.setCreatedBy(oldArticle.getCreatedBy());
            articleEntity.setCreatedDate(oldArticle.getCreatedDate());
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
        articleEntity.setSlug(newSlug);

        articleEntity = articleRepository.save(articleEntity);
        articleDTO = mapper.map(articleEntity, articleDTO.getClass());
        return articleDTO;
    }

    @Override
    public ArticleDTO publish(long id) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleEntity = articleRepository.findById(id);
        if (articleEntity.getStatus().getCode().equalsIgnoreCase("draft")) {
            StatusEntity statusEntity = statusRepository.findByCode("pending");
            articleEntity.setStatus(statusEntity);
            articleEntity = articleRepository.save(articleEntity);
            articleDTO = mapper.map(articleEntity, ArticleDTO.class);
        }
        return articleDTO;
    }

    @Override
    public ArticleDTO approve(long id) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleEntity = articleRepository.findById(id);
        if (articleEntity.getStatus().getCode().equalsIgnoreCase("pending")) {
            StatusEntity status = statusRepository.findByCode("published");
            articleEntity.setStatus(status);
            articleEntity.setPublishedDate(new Date());
            articleEntity = articleRepository.save(articleEntity);
            articleDTO = mapper.map(articleEntity, ArticleDTO.class);
        }
        return articleDTO;
    }

    @Override
    public ArticleDTO refuse(long id) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleEntity = articleRepository.findById(id);
        if (articleEntity != null && articleEntity.getStatus().getCode().equalsIgnoreCase("pending")) {
            StatusEntity statusEntity = statusRepository.findByCode("draft");
            articleEntity.setStatus(statusEntity);
            articleEntity = articleRepository.save(articleEntity);
            articleDTO = mapper.map(articleEntity, ArticleDTO.class);
        }
        return articleDTO;
    }

    @Override
    public ArticleDTO unpublish(long id) throws ArticleNotFoundException {
        ArticleDTO articleDTO = new ArticleDTO();
        articleEntity = articleRepository.findById(id);
        if (articleEntity != null) {
            String currentStatus = articleEntity.getStatus().getCode();
            if (currentStatus.equalsIgnoreCase("pending") || currentStatus.equalsIgnoreCase("published")) {
                StatusEntity statusEntity = statusRepository.findByCode("draft");
                articleEntity.setStatus(statusEntity);
                articleEntity = articleRepository.save(articleEntity);
                mapper.map(articleEntity, ArticleDTO.class);
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
        articleEntity = articleRepository.findById(id);
        if (articleEntity.getStatus().getCode().equalsIgnoreCase("trash")) {
            StatusEntity statusEntity = statusRepository.findByCode("draft");
            articleEntity.setStatus(statusEntity);
            articleEntity = articleRepository.save(articleEntity);
            mapper.map(articleEntity, ArticleDTO.class);
        }
        return articleDTO;
    }

    @Override
    public boolean isUniqueSlug(String slug) {
        return articleRepository.findBySlug(slug) == null;
    }

    @Override
    public ArticleDTO trash(long id) throws ArticleNotFoundException {
        articleEntity = articleRepository.findById(id);

        if (articleEntity == null) {
            throw new ArticleNotFoundException(
                    messageSource.getMessage("article.id.notfound", null, null) + id);
        }

        StatusEntity statusEntity = statusRepository.findByCode("trash");
        articleEntity.setStatus(statusEntity);
        articleEntity.setFeatured(false);
        articleEntity = articleRepository.save(articleEntity);
        return mapper.map(articleEntity, ArticleDTO.class);
    }

    @Override
    @Transactional
    public void trash(long[] ids) {
        StatusEntity status = statusRepository.findByCode("trash");
        for (long id : ids) {
            articleEntity = articleRepository.findById(id);
            articleEntity.setStatus(status);
            articleEntity.setFeatured(false);
            articleRepository.save(articleEntity);
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
        articleEntity = articleRepository.findById(id);

        if (articleEntity == null) {
            throw new ArticleNotFoundException(
                    messageSource.getMessage("article.id.notfound", null, null) + id);
        }
        if (!articleEntity.getStatus().getCode().equalsIgnoreCase("trash")) {
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
