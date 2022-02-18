package com.servletDemo.dao;

import com.servletDemo.entity.UserEntity;

import java.sql.SQLException;
import java.util.List;

public interface UsersDao {
  public List<UserEntity> getUsers();
  public Boolean addUsers(String userName, String userPhone, String userAvatar, Integer userStatus) throws SQLException;
  public Boolean deleteUser(String userId);
}
