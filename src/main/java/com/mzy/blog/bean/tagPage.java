package com.mzy.blog.bean;


import lombok.Data;

import java.util.List;

@Data
public class tagPage {
    List<Tags> tagsList;
    int tagslen;
}
