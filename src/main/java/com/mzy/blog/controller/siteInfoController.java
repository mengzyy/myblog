package com.mzy.blog.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzy.blog.bean.siteInfo;
import com.mzy.blog.service.siteinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class siteInfoController {
    @Autowired
    siteinfoService siteinfoService;

    @RequestMapping("/blog")
    public String indexBlog() {
        return "index";

    }
    @RequestMapping("/mylove")
    public String mylove() {
        return "mylove";

    }

    @RequestMapping("/picture")
    public String picture() {
        return "picture";

    }

    @RequestMapping("/aboutme")
    public String aboutme() {
        return "404";

    }




    @RequestMapping("/getSiteInfo")
    @ResponseBody
    public String getsiteInfo() {
        siteInfo siteInfo = siteinfoService.getSiteInfo();
        String res = JSONObject.toJSONString(siteInfo);
        return res;
    }


    @RequestMapping("/getTagsCloud")
    @ResponseBody
    public String getTagsInfo() {
        siteInfo siteInfo = siteinfoService.getTagsInfo();
        String res = JSONObject.toJSONString(siteInfo);
        return res;
    }


}
