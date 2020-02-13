package com.mzy.blog.controller;


import com.alibaba.fastjson.JSONObject;
import com.mzy.blog.bean.Article;
import com.mzy.blog.bean.ArticlesPage;
import com.mzy.blog.service.articleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class pagedivideController {

    @Autowired
    articleService articleService;


    @RequestMapping("/getArticlePageList")
    @ResponseBody
    public String getArticlePageList(int pageNum) {
        int eachpagecount = 5;
        int articlecount = articleService.getArticleAll().size();
        List<Article> articleList = articleService.getArticleByPageNum(pageNum, 5);
        ArticlesPage articlesPage = new ArticlesPage();

        articlesPage.setAllpagecount(articlecount);
        articlesPage.setEachpagecount(eachpagecount);
        articlesPage.setArticleList(articleList);
        articlesPage.setListlen(articleList.size());
        String res = JSONObject.toJSONString(articlesPage);

        return res;


    }


}
