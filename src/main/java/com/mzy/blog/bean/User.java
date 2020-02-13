package com.mzy.blog.bean;


import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class User {
  @Id
  private Integer id;
  private String phone;
  private String username;
  private String password;
  private String gender;
  private String truename;
  private String birthday;
  private String email;
  private String personalbrief;
  private String imgurl;


}
