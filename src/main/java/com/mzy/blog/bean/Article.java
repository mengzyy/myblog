package com.mzy.blog.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {


  @Id
  private Integer id;

  private Integer articleId;
  private String author;
  private String articleTitle;
  private String articleContent;
  private String articleTags;
  private String articleType;
  private String articleCategories;
  private String publishDate;
  private String updateDate;
  private String articleTabloid;
  private Integer visitor;

  @Transient
  private List<Tags> tagsList;

  //专门为了提交文章使用的字段
  @Transient
  private  List<String> articleTagsValue;







}
