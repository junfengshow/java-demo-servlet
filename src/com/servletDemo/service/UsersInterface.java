package com.servletDemo.service;

import com.alibaba.fastjson.JSONObject;
import com.servletDemo.entity.UserEntity;

import java.util.List;

public interface UsersInterface {
  public List<UserEntity> getUsers ();
  public JSONObject addUsers(String userName, String userPhone, String userAvatar, Integer userStatus);
  public JSONObject deleteUser(String userId);
}
