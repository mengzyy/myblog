package com.mzy.blog.controller;

import com.mzy.blog.bean.loginInputmsg;
import com.mzy.blog.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: myblog
 * @author: mengzy 18306299232@163.com
 * @create: 2020-02-13 11:26
 **/

@Controller
public class loginController {
    @Autowired
    userService userService;


    //返回登陆页面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    //处理登录请求
    @RequestMapping(value = "/loginIn", method = RequestMethod.POST)
    public String login(String phone, String password, Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        boolean flag = userService.loginInVerity(phone, password,response,request,session);
        if (flag) {
            return "index";
        } else {
            model.addAttribute("loginError", 1);
            return "login";
        }
    }


    //处理登出请求
    @RequestMapping(value = "/loginOut")
    public String login(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        boolean flag = userService.loginOutVerity(response,request,session);
        if (flag) {
            return "index";
        } else {
            return "error";
        }
    }


}
