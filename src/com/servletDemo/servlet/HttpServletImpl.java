package com.servletDemo.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpServletImpl extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("this is doGet");
  }
  @Override
  public void service (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    System.out.println("this is service");
    res.getWriter().println("this is service");
  }
}
