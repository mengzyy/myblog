package com.mzy.blog.controller;


import com.alibaba.fastjson.JSONObject;
import com.mzy.blog.bean.Article;
import com.mzy.blog.bean.requestResInfo;
import com.mzy.blog.service.articleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

@Controller
public class editorController {
    @Autowired
    articleService articleService;

    //返回写博客页面
    @RequestMapping("/editor")
    public String editor() {


        return "editor";
    }


    //发表文章
    @RequestMapping(value="/publishArticle",method=RequestMethod.POST)
    @ResponseBody
    public String publishArticle(@RequestBody Article article) {
        //将文章加入数据库中
        Boolean flag = articleService.insertArticle(article);
        requestResInfo requestReInfo = new requestResInfo();
        requestReInfo.setState(flag ? 0 : 1);
        String res = JSONObject.toJSONString(requestReInfo);
        return res;

    }




}
