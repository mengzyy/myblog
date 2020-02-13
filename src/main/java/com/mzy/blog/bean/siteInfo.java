package com.mzy.blog.bean;

import lombok.Data;

import java.util.List;

@Data
public class siteInfo {

    private int articleNum;
    private int tagsNum;
    private int vistorNum;
    private int taglen;
    private List<Tags> tagsList;


}
