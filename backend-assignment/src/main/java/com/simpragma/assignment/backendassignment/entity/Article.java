package com.simpragma.assignment.backendassignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "article")
public class Article extends BaseEntity {

  @Column(name = "title")
  private String title;

  @Column(name = "body")
  private String content;

  @Column(name = "author")
  private String createdBy;

}
