package com.mzy.blog.service;

import com.mzy.blog.bean.siteInfo;

public  interface siteinfoService {
    //返回网站信息
    public siteInfo getSiteInfo();
    //返回网站标签云信息
    public siteInfo getTagsInfo();
}
