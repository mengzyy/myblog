package com.mzy.blog.mapper;

import com.mzy.blog.bean.Role;
import com.mzy.blog.bean.Tags;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;


//@Component(value = "tagsMapper")
public interface tagsMapper  extends Mapper<Tags> {
}
