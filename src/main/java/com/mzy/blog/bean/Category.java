package com.mzy.blog.bean;


import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class Category {
  @Id
  private Integer id;
  private Integer cateId;
  private String cateName;
  private String articleId;

}
