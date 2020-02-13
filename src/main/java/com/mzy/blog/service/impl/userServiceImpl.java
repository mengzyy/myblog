package com.mzy.blog.service.impl;

import com.mzy.blog.bean.User;
import com.mzy.blog.mapper.userMapper;
import com.mzy.blog.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: myblog
 * @author: mengzy 18306299232@163.com
 * @create: 2020-02-13 13:25
 **/
@Service
public class userServiceImpl implements userService {
    @Autowired
    userMapper userMapper;

    @Override
    public boolean loginInVerity(String phone, String password, HttpServletResponse response, HttpServletRequest request, HttpSession session) {

        List<User> users = userMapper.selectAll();
        for (User user : users) {
            if (user.getPhone().equals(phone)) {
                if (user.getPassword().equals(password)) {
                    //设置cookie 记住我功能
                    Cookie cookieUserName = new Cookie("loginPhone", phone);
//                    Cookie cookieUserPwd = new Cookie("loginPwd", password);
                    cookieUserName.setMaxAge(30 * 60);
//                    cookieUserPwd.setMaxAge(30 * 60);
                    response.addCookie(cookieUserName);
//                    response.addCookie(cookieUserPwd);
                    //服务器设置session
                    session.setAttribute("sessionPhone", user.getPhone());
                    session.setAttribute("sessionUserName", user.getUsername());
                    session.setAttribute("sessionPassWord", user.getPassword());

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean loginOutVerity(HttpServletResponse response, HttpServletRequest request, HttpSession session) {

        boolean res = true;
        //删除session存在的id即可
        try {
            session.removeAttribute("sessionPhone");
            session.removeAttribute("sessionUserName");
            session.removeAttribute("sessionPassWord");

        } catch (Exception e) {
            res = false;
            e.printStackTrace();
        }
        return res;
    }


}
