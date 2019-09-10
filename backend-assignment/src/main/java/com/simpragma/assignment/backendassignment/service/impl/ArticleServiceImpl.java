package com.simpragma.assignment.backendassignment.service.impl;

import com.simpragma.assignment.backendassignment.constants.ApplicationConstants;
import com.simpragma.assignment.backendassignment.dto.ArticleDto;
import com.simpragma.assignment.backendassignment.dto.SimpleResponseDto;
import com.simpragma.assignment.backendassignment.dto.request.article.ArticleCreateRequestDto;
import com.simpragma.assignment.backendassignment.entity.Article;
import com.simpragma.assignment.backendassignment.repository.ArticleRepository;
import com.simpragma.assignment.backendassignment.service.ArticleService;
import com.simpragma.assignment.backendassignment.utils.Utils;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

  @Autowired
  private ArticleRepository articleRepository;

  @Override
  public SimpleResponseDto createArticle(ArticleCreateRequestDto articleCreateRequestDto) {

    SimpleResponseDto articleCreationResponse = new SimpleResponseDto();

    Article article = articleRepository.save(Article.builder()
        .title(articleCreateRequestDto.getTitle())
        .content(articleCreateRequestDto.getBody())
        .createdBy(articleCreateRequestDto.getAuthor())
        .build());

    if (Objects.nonNull(article)) {
      articleCreationResponse.put(ApplicationConstants.MESSAGE.getValue(),
          ApplicationConstants.ARTICLE_CREATED.getValue());
    } else {
      articleCreationResponse
          .put(ApplicationConstants.MESSAGE.getValue(), ApplicationConstants.FAILED.getValue());
    }

    return articleCreationResponse;
  }

  @Override
  public SimpleResponseDto getArticles(int page, int size) {

    SimpleResponseDto articlesListRespose = new SimpleResponseDto();
    Page<Article> articles = articleRepository.findAll(PageRequest.of(page, size));

    List<ArticleDto> articleDtoList = articles.getContent().stream()
        .map(Utils::fromArticleToArticleDto).collect(
            Collectors.toList());

    articlesListRespose.put(ApplicationConstants.DATA.getValue(), articleDtoList);

    return articlesListRespose;
  }
}
