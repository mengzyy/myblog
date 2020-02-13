package com.mzy.blog.bean;


import lombok.Data;

import java.util.List;

@Data
public class ArticlesPage {
    //全部博文数
    private int allpagecount;
    //每页博文数
    private int eachpagecount;
    //页面内容
    private List<Article> articleList;
    //页面长度
    private int listlen;


}
