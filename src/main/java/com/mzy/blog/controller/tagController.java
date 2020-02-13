package com.mzy.blog.controller;


import com.alibaba.fastjson.JSONObject;
import com.mzy.blog.bean.Category;
import com.mzy.blog.bean.Tags;
import com.mzy.blog.bean.categoryPage;
import com.mzy.blog.bean.tagPage;
import com.mzy.blog.service.categoryService;
import com.mzy.blog.service.tagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class tagController {

    @Autowired
    tagsService tagsService;




    @RequestMapping("/tags")
    public String getTags(){
        return "tags";
    }

    //直接跳转特定标签页面
    @RequestMapping("/tag")
    public String getCategory(@RequestParam String tag, Model model) {
        model.addAttribute("tag",tag);
        return "tag";
    }




    //后台ajax填充分类列表
    @RequestMapping("/tagsAll")
    @ResponseBody
    public String tagsAll() {
        List<Tags> tagsList = tagsService.getTagsAll();
        tagPage tagPage=  new tagPage();
        tagPage.setTagsList(tagsList);
        tagPage.setTagslen(tagsList.size());
        String res = JSONObject.toJSONString(tagPage);
        return res;
    }



}
