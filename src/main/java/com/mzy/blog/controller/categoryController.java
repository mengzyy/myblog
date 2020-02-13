package com.mzy.blog.controller;


import com.alibaba.fastjson.JSONObject;
import com.mzy.blog.bean.Category;
import com.mzy.blog.bean.categoryPage;
import com.mzy.blog.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class categoryController {
    @Autowired
    categoryService categoryService;


    //直接跳转分类页面
    @RequestMapping("/categories")
    public String getCategories() {
        return "categories";
    }

    //直接跳转特定分类页面
    @RequestMapping("/category")
    public String getCategory(@RequestParam String articleCategories,Model model) {
        model.addAttribute("categoryName",articleCategories);
        return "category";
    }



    //后台ajax填充分类列表
    @RequestMapping("/categoriesAll")
    @ResponseBody
    public String getCategoriesAll(Model model) {
        List<Category> categoryList = categoryService.getCategories();
        categoryPage categoryPage = new categoryPage();
        categoryPage.setCategoryList(categoryList);
        String res = JSONObject.toJSONString(categoryPage);
        return res;
    }





}
