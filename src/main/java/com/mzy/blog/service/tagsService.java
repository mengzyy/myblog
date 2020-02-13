package com.mzy.blog.service;

import com.mzy.blog.bean.Tags;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface tagsService {
    List<Tags> getTagsAll();
}
