package com.vtoan1517.service.impl;

import com.vtoan1517.dto.ArticleDTO;
import com.vtoan1517.entity.Article;
import com.vtoan1517.exception.ArticleNotFoundException;
import com.vtoan1517.repository.AccessRepository;
import com.vtoan1517.repository.ArticleRepository;
import com.vtoan1517.repository.CategoryRepository;
import com.vtoan1517.repository.StatusRepository;
import com.vtoan1517.service.IArticleRetrievalService;
import com.vtoan1517.utils.CollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class ArticleRetrievalService implements IArticleRetrievalService {

    private final ArticleRepository articleRepository;

    private final CollectionMapper mapper = new CollectionMapper();

    private final MessageSource messageSource;

    private Article article;

    @Autowired
    public ArticleRetrievalService(ArticleRepository articleRepository, AccessRepository accessRepository,
                                   CategoryRepository categoryRepository, StatusRepository statusRepository,
                                   MessageSource messageSource) {
        this.articleRepository = articleRepository;
        this.messageSource = messageSource;
    }

    @Override
    public Page<ArticleDTO> findAllByKeyword(String keyword, Pageable pageable) {
        Page<ArticleDTO> articles = articleRepository.findAllByTitleContainingIgnoreCase(keyword, pageable)
                .map(item -> mapper.map(item, ArticleDTO.class));
        if (articles.getTotalElements() != 0) {
            for (ArticleDTO articleDTO : articles) {
                String highlightStr = articleDTO.getTitle().replaceAll(
                        "(?i)" + Pattern.quote(keyword), "<span style='color:red!important'>$0</span>");
                articleDTO.setTitle(highlightStr);
            }
            return articles;
        }

        articles = articleRepository.findAllByDescriptionContainingIgnoreCase(keyword, pageable)
                .map(item -> mapper.map(item, ArticleDTO.class));
        if (articles.getTotalElements() != 0) {
            for (ArticleDTO articleDTO : articles) {
                String highlightStr = articleDTO.getDescription().replaceAll(
                        "(?i)" + Pattern.quote(keyword), "<span style='color:red!important'>$0</span>");
                articleDTO.setDescription(highlightStr);
            }
            return articles;
        }

        articles = articleRepository.findAllByContentIsContainingIgnoreCase(keyword, pageable)
                .map(item -> mapper.map(item, ArticleDTO.class));
        for (ArticleDTO articleDTO : articles) {
            String highlightStr = "..." + "<span style='color:red!important'>" + keyword + "...";
            articleDTO.setDescription(highlightStr);
        }

        return articles;
    }

    @Override
    public Page<ArticleDTO> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable).map(item -> mapper.map(item, ArticleDTO.class));
    }

    @Override
    public List<ArticleDTO> findAllByCategoryCode(String code, Pageable pageable) {
        return mapper.map(articleRepository.findAllByStatusCodeAndCategoryCode("published", code, pageable), ArticleDTO.class);
    }

    @Override
    public Page<ArticleDTO> findAllByAuthorOrPublicAccess(String authorName, Pageable pageable) {
        return articleRepository.findAllByCreatedByAndStatusCodeNotOrAccessCodeAndStatusCodeNot(
                        authorName, "trash", "public", "trash", pageable)
                .map(item -> mapper.map(item, ArticleDTO.class));
    }

    @Override
    public Page<ArticleDTO> findAllByFeaturedAndAuthor(boolean featured, String authorName, Pageable pageable) {
        return articleRepository.findAllByFeaturedAndCreatedByOrFeaturedAndAccessCode(featured, authorName, featured, "public", pageable)
                .map(item -> mapper.map(item, ArticleDTO.class));
    }

    @Override
    public Page<ArticleDTO> findAllByStatusCode(String statusCode, Pageable pageable) {
        return articleRepository.findAllByStatusCode(statusCode, pageable).map(item -> mapper.map(item, ArticleDTO.class));
    }

    @Override
    public Page<ArticleDTO> findAllByStatusCodeAndAuthor(String statusCode, String authorName, Pageable pageable) {
        return articleRepository.findAllByStatusCodeAndCreatedByOrStatusCodeAndAccessCode(statusCode, authorName, statusCode, "public", pageable)
                .map(item -> mapper.map(item, ArticleDTO.class));
    }

    @Override
    public Page<ArticleDTO> findAllByFeatured(boolean featured, Pageable pageable) {
        return articleRepository.findAllByFeatured(featured, pageable).map(item -> mapper.map(item, ArticleDTO.class));
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
}
