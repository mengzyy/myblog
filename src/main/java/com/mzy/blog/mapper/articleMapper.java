package com.mzy.blog.mapper;

import com.mzy.blog.bean.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface articleMapper extends Mapper<Article> {

    //在xml中写sql
    List<Article> getArticleByCateName(@Param("categoryName") String categoryName);
    //Like 在xml中写sql
    List<Article> getArticlesByTagName(@Param("tagName")String tagName);
}
