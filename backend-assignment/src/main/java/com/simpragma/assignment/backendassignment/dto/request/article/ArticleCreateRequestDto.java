package com.simpragma.assignment.backendassignment.dto.request.article;

import lombok.Data;

@Data
public class ArticleCreateRequestDto {

  private String title;

  private String body;

  private String author;

}
