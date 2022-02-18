package com.servletDemo.dao.impl;

import com.servletDemo.dao.UserLoginDao;
import com.servletDemo.entity.UserEntity;
import com.servletDemo.utils.IJdbcUtils;

import java.util.List;

public class UserLoginDaoImpl implements UserLoginDao {
  public List getUserList () throws Exception {
    IJdbcUtils<UserEntity> IJdbcUtils = new IJdbcUtils();

    String sql = "select * from user";
//    Map<Integer, String> paramsMap = new HashMap<>();
//    paramsMap.put(1, "1");
    return IJdbcUtils.getResultList(new UserEntity(), sql);
  }

  public void main () {
    try {


    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
