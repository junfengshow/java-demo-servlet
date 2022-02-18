/**
 *
 * 发起请求
 */
package com.servletDemo.servlet;
import com.alibaba.fastjson.JSONObject;
import javax.jws.WebMethod;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import com.servletDemo.service.impl.UsersImpl;


@WebServlet("/users/*")
public class UserServlet extends HttpServlet {
  private UsersImpl udi = new UsersImpl();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      resp.setCharacterEncoding("utf8");
      resp.setContentType("text/text;charset=utf8");

      PrintWriter out = resp.getWriter();
      // 获取虚拟目录
      String servletPath = req.getServletPath();
      // 获取请求地址
      String reqUri = req.getRequestURI();
      // 解析出虚拟目录之后的地址
      String remainUrl = reqUri.substring(servletPath.length());
      // 解析出方法名
      // String methodName = remainUrl.substring
      String methodName = remainUrl.split("/")[1];

      Class c = UserServlet.class;
      Method method = null;
      try {
        method = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
      } catch (Exception e) {
        e.printStackTrace();
      }
      if (method != null) {
        resp.setContentType("application/json;utf8");
        Object obj = method.invoke(this, req, resp);
        resp.getWriter().println(obj);
      } else {
        resp.setStatus(404);
        out.println();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doGet(req, resp);
  }

  public JSONObject getInfo (HttpServletRequest req, HttpServletResponse resp) {
    JSONObject json = new JSONObject();
    try {

      json.put("data", udi.getUsers());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return json;
  }
}
