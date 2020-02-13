package com.mzy.blog.mapper;

import com.mzy.blog.bean.Category;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

//@Component(value = "categoryMapper")
public interface categoryMapper  extends Mapper<Category> {
}
