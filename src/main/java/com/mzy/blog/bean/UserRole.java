package com.mzy.blog.bean;


import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class UserRole {
  @Id
  private Integer id;
  private Integer roleId;


}
