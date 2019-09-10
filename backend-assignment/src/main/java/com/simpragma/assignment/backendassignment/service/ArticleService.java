package com.simpragma.assignment.backendassignment.service;

import com.simpragma.assignment.backendassignment.dto.SimpleResponseDto;
import com.simpragma.assignment.backendassignment.dto.request.article.ArticleCreateRequestDto;

public interface ArticleService {

  public SimpleResponseDto createArticle(ArticleCreateRequestDto articleCreateRequestDto);

  public SimpleResponseDto getArticles(int page, int size);
}
