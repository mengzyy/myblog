package com.mzy.blog.bean;


import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class Visitor {
  @Id
  private Integer id;
  private Integer visitorNum;
  private String updatetime;



}
