package com.mzy.blog.service.impl;

import com.mzy.blog.bean.Tags;
import com.mzy.blog.mapper.tagsMapper;
import com.mzy.blog.service.tagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public class tagsServiceImpl implements tagsService {

    @Autowired
    tagsMapper tagsMapper;

    @Override
    public List<Tags> getTagsAll() {
        List<Tags> tagsList = tagsMapper.selectAll();

        return tagsList;
    }
}
