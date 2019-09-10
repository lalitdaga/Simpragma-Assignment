package com.simpragma.assignment.backendassignment.controller;

import com.simpragma.assignment.backendassignment.dto.SimpleResponseDto;
import com.simpragma.assignment.backendassignment.dto.request.article.ArticleCreateRequestDto;
import com.simpragma.assignment.backendassignment.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/article/v1")
@RestController
@Api(tags = {"Article Controller"})
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  @PostMapping(value = "/articles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "create articles", response = SimpleResponseDto.class)
  public ResponseEntity createArticle(
      @RequestHeader("Authorisation") String access_token,
      @RequestBody ArticleCreateRequestDto articleCreateRequestDto) {

    return new ResponseEntity<>(articleService.createArticle(articleCreateRequestDto),
        HttpStatus.CREATED);
  }

  @GetMapping(value = "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "get articles", response = SimpleResponseDto.class)
  public ResponseEntity getArticleList(
      @RequestHeader("Authorisation") String access_token,
      @RequestParam int page, @RequestParam int size) {

    return new ResponseEntity<>(articleService.getArticles(page, size),
        HttpStatus.CREATED);
  }

}
