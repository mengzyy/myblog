package com.mzy.blog.service.impl;

import com.mzy.blog.bean.Article;
import com.mzy.blog.bean.Tags;
import com.mzy.blog.bean.Visitor;
import com.mzy.blog.bean.siteInfo;
import com.mzy.blog.mapper.articleMapper;
import com.mzy.blog.mapper.tagsMapper;
import com.mzy.blog.mapper.visitorMapper;
import com.mzy.blog.service.siteinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class siteinfoServiceimp implements siteinfoService {
    @Autowired
    articleMapper articleMapper;
    @Autowired
    tagsMapper tagsMapper;

    @Autowired
    visitorMapper visitorMapper;




    @Override
    public siteInfo getSiteInfo() {
        List<Article> articles =articleMapper.selectAll();
        List<Tags> tags = tagsMapper.selectAll();
        List<Visitor> visitors = visitorMapper.selectAll();
        //返回
        siteInfo siteInfo = new siteInfo();
        siteInfo.setArticleNum(articles.size());
        siteInfo.setTagsNum(tags.size());
        siteInfo.setVistorNum(visitors.size());

        return siteInfo;
    }

    @Override
    public siteInfo getTagsInfo() {
        List<Tags> tags = tagsMapper.selectAll();
        //返回
        siteInfo siteInfo = new siteInfo();
        if(tags.size()!=0){
            siteInfo.setTaglen(tags.size());
            siteInfo.setTagsList(tags);
        }
        return siteInfo;
    }
}
