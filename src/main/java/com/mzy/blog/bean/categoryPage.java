package com.mzy.blog.bean;

import lombok.Data;

import java.util.List;


//分类集合
//存放分类集合 ，每个分类下的文章集合
@Data
public class categoryPage {
    List<Category> categoryList;
    int categorylen;
}
