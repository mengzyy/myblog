package com.mzy.blog.bean;


import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class Tags {
  @Id
  private Integer id;
  private String tagName;
  private Integer tagSize;
  private String tagArticle;


}
