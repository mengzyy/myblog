package com.mzy.blog.service;

import com.mzy.blog.bean.Article;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface articleService {
    List<Article> getArticleByPageNum(int pageNum,int eachpagecount);
    List<Article> getArticleAll();

    Article getArticleById(int articleId);

    List<Article> getArticleByCateName(String categoryName);

    List<Article> getArticlesByTagName(String tagName);

    Boolean insertArticle(Article article);
}
