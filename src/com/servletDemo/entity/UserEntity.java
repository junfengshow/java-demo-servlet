package com.servletDemo.entity;

public class UserEntity {
  private Integer id;
  private String userName;
  private String userPhone;
  private String userAvatar;
  private Integer userStatus;

  public String getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }

  public String getUserAvatar() {
    return userAvatar;
  }

  public void setUserAvatar(String userAvatar) {
    this.userAvatar = userAvatar;
  }

  public Integer getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(Integer userStatus) {
    this.userStatus = userStatus;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  @Override
  public String toString() {
    return "{" +
        "id:" + id +
        ", userName:'" + userName + '\'' +
        ", userPhone:'" + userPhone + '\'' +
        ", userAvatar:'" + userAvatar + '\'' +
        ", userStatus:" + userStatus +
        '}';
  }
}
