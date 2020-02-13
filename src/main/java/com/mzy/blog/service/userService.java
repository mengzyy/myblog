package com.mzy.blog.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: myblog
 * @author: mengzy 18306299232@163.com
 * @create: 2020-02-13 13:22
 **/
public interface userService {
    boolean loginInVerity(String phone, String password, HttpServletResponse response, HttpServletRequest request, HttpSession session);

    boolean loginOutVerity(HttpServletResponse response, HttpServletRequest request, HttpSession session);
}
