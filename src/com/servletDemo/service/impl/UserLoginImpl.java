package com.servletDemo.service.impl;

import com.servletDemo.dao.impl.UserLoginDaoImpl;
import com.servletDemo.entity.UserEntity;
import com.servletDemo.service.UserLoginInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/userList")
public class UserLoginImpl extends HttpServlet implements UserLoginInterface {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      UserLoginDaoImpl uli = new UserLoginDaoImpl();
      List<UserEntity> userList = uli.getUserList();
      req.setAttribute("userList", userList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    req.getRequestDispatcher("/userList.jsp").forward(req, resp);
  }
}
