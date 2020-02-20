package com.mzy.blog.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzy.blog.bean.Article;
import com.mzy.blog.bean.ArticlesPage;
import com.mzy.blog.bean.Tags;
import com.mzy.blog.service.articleService;
import com.mzy.blog.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class articleController {

    @Autowired
    articleService articleService;

//    @Autowired
//    RedisUtil redisUtil;

    //查询特定的文章
    @RequestMapping("/getArticleById")
    public String getArticleById(@RequestParam int articleId, Model model) {
//暂时不使用redis，因为服务器出现了一些意外
        Article article = null;
//        String articleContent = redisUtil.get("articleId:" + articleId, 1);
//        if (articleContent == null) {
        article = articleService.getArticleById(articleId);
//            //加入缓存
//            String setex = redisUtil.setex("articleId:" + articleId, JSON.toJSONString(article), 60 * 60 * 24, 1);
//            System.out.println(setex);
//        } else {
//            article = JSON.parseObject(articleContent, Article.class);
//        }
//        加入model
        model.addAttribute("article", article);
        return "article";


    }


    //查询某分类的文章
    @RequestMapping("/getArticlesByCateName")
    @ResponseBody
    public String getArticleByCateName(@RequestParam String categoryName) {


        List<Article> articleList = articleService.getArticleByCateName(categoryName);
        loadArticleTags(articleList);
        ArticlesPage articlesPage = new ArticlesPage();

        articlesPage.setArticleList(articleList);
        articlesPage.setEachpagecount(5);
        articlesPage.setAllpagecount(articleList.size());

        String res = JSONObject.toJSONString(articlesPage);
        return res;
    }


    //查询某分类的文章
    @RequestMapping("/getArticlesByTagName")
    @ResponseBody
    public String getArticlesByTagName(@RequestParam String tagName) {

        List<Article> articleList = articleService.getArticlesByTagName(tagName);
        loadArticleTags(articleList);
        ArticlesPage articlesPage = new ArticlesPage();
        articlesPage.setArticleList(articleList);
        articlesPage.setEachpagecount(5);
        articlesPage.setAllpagecount(articleList.size());
        String res = JSONObject.toJSONString(articlesPage);
        return res;
    }

    //装载tags
    public void loadArticleTags(List<Article> articleList) {
        for (Article article : articleList) {
            String tagString = article.getArticleTags();
            String[] split = tagString.split(",");
            List<Tags> tagsList = new ArrayList<>();
            for (String s : split) {
                Tags t = new Tags();
                t.setTagName(s);
                tagsList.add(t);

            }
            article.setTagsList(tagsList);
        }

    }


}
