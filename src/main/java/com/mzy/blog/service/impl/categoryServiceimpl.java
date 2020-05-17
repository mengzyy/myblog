package com.mzy.blog.service.impl;

import com.mzy.blog.bean.Category;
import com.mzy.blog.mapper.categoryMapper;
import com.mzy.blog.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class categoryServiceimpl implements categoryService {
    @Autowired
    categoryMapper categoryMapper;


    @Override
    public List<Category> getCategories() {
        List<Category> categoryList = categoryMapper.selectAll();
        return categoryList;
    }
}
