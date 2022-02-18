package com.servletDemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.servletDemo.dao.UsersDao;
import com.servletDemo.dao.impl.UsersDaoImpl;
import com.servletDemo.entity.UserEntity;
import com.servletDemo.service.UsersInterface;

import java.util.List;

public class UsersImpl implements UsersInterface {
  private UsersDao usd = new UsersDaoImpl();

  public List<UserEntity> getUsers () {
    List<UserEntity> list = usd.getUsers();
    return list;
  }
  public JSONObject addUsers(String userName, String userPhone, String userAvatar, Integer userStatus) {
    JSONObject json = new JSONObject();
    try {
      json.put("result", 1);
      if (userName == null) {
        json.put("msg", "用户名称不能为空");
        return json;
      }
      if (userPhone == null) {
        json.put("msg", "用户手机号不能为空");
        return json;
      }
      if (userStatus == null) {
        userStatus = 1;
      }
      // 将数据添加到数据库
      Boolean resultBool = usd.addUsers(userName, userPhone, userAvatar, userStatus);
      if (resultBool) {
        json.put("msg", "success");
        json.put("result", 0);
      }
    } catch (Exception e) {
      json.put("msg", "服务器异常");
      json.put("result", 1);
      e.printStackTrace();
    }
    return json;
  }
  public JSONObject deleteUser(String userId) {
    JSONObject json = new JSONObject();
    try {

      // 将数据添加到数据库
      Boolean resultBool = usd.deleteUser(userId);
      if (resultBool) {
        json.put("msg", "success");
        json.put("result", 0);
      }
    } catch (Exception e) {
      json.put("msg", "服务器异常");
      json.put("result", 1);
      e.printStackTrace();
    }
    return json;
  }
}
