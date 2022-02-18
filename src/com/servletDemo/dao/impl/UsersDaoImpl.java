package com.servletDemo.dao.impl;

import com.servletDemo.dao.UsersDao;
import com.servletDemo.entity.UserEntity;
import com.servletDemo.utils.MyJdbcTemplateUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class UsersDaoImpl implements UsersDao {
  public List<UserEntity> getUsers() {
    List<UserEntity> list = null;
    try {
      JdbcTemplate jdbcTemplate = new JdbcTemplate(MyJdbcTemplateUtils.getDataSource());
      String sql = "select * from user";
      list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserEntity.class));

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public Boolean addUsers(String userName, String userPhone, String userAvatar, Integer userStatus) throws SQLException {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(MyJdbcTemplateUtils.getDataSource());
    String sql = "insert into user (userName, userPhone, userAvatar, userStatus) values (?,?,?,?)";
    int result = jdbcTemplate.update(sql, new Object[]{userName, userPhone, userAvatar, userStatus});
    return result > 0;
  }
  public Boolean deleteUser (String userId) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(MyJdbcTemplateUtils.getDataSource());
    String sql = "delete from user where id=(?)";
    int result = jdbcTemplate.update(sql, userId);
    return result > 0;
  }
}
