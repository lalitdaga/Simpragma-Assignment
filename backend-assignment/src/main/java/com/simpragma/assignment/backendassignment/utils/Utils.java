package com.simpragma.assignment.backendassignment.utils;

import com.simpragma.assignment.backendassignment.dto.ArticleDto;
import com.simpragma.assignment.backendassignment.entity.Article;

public interface Utils {

  static ArticleDto fromArticleToArticleDto(Article article){

    return ArticleDto.builder()
        .title(article.getTitle())
        .body(article.getContent())
        .build();
  }

}
