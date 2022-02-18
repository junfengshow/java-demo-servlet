package com.servletDemo.servlet;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import java.util.Enumeration;
import java.io.PrintWriter;
import java.util.Map;

public class DemoServlet implements Servlet {
  public void init (ServletConfig servletConfig) {
    System.out.println("init");
  }
  public void service (ServletRequest req, ServletResponse res) {
    // 1 使用json方便序列化
    JSONObject json = new JSONObject();
    try {
      // AsyncContext:
      // 1.servlet3.0新增的功能
      // 2.作用: 支持异步处理，使得长时间业务处理不至于阻塞程序

      // ServletRequest
      // 1.getParameterNames 返回参数的枚举集
      // 2.getParameterMap 获取参数集合 其中value为字符串数组
      Enumeration<String> em = req.getParameterNames();
      Map<String, String[]> hm = req.getParameterMap();
      while (em.hasMoreElements()) {
        String name = em.nextElement();
        String[] valueArray = hm.get(name);
        if (valueArray != null) {
          json.put(name, valueArray[0]);
        }
      }
      // AsyncContext
      AsyncContext ac = req.startAsync();
      // setAttribute getAttributeNames
      req.setAttribute("name", "李四");

      Enumeration<String> attributeNames = req.getAttributeNames();
      while (attributeNames.hasMoreElements()) {
        String attributeName = attributeNames.nextElement();
        System.out.println(attributeName + ": " + req.getAttribute(attributeName));
      }

      req.setCharacterEncoding("utf-8");

      // 设置返回的字符集
      res.setCharacterEncoding("utf-8");
      // 设置返回的类型
      res.setContentType("application/json");
      // 获取可写对象
      PrintWriter pw = res.getWriter();
      // 将返回值写入并返回
      pw.println(json.toString());
      ac.complete();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public String getServletInfo () {
    System.out.println("info");
    return null;
  }
  public ServletConfig getServletConfig () {
    return null;
  }
  public void destroy () {}
}
