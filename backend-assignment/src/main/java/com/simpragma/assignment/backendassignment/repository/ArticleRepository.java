package com.simpragma.assignment.backendassignment.repository;

import com.simpragma.assignment.backendassignment.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
