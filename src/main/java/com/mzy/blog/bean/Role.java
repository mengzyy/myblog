package com.mzy.blog.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class Role {
  @Id
  private Integer id;
  private String name;


}
