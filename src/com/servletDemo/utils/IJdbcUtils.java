package com.servletDemo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class IJdbcUtils<T> {
  // 配置属性地址
  private static final String propertiesFilePath = "src/database.properties";
  // jdbc相关地址
  private static String driverClass;
  private static String url;
  private static String userName;
  private static String passWord;
  // 链接对象
  private static Connection con;

  static {
    try {
      // 先加载配置文件
      getProperties();
      // 驱动注入
      Class.forName(driverClass);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 获取配置文件
   * @throws IOException
   */
  private static void getProperties () throws Exception {
    // 创建文件
//    File propertiesFile = new File(propertiesFilePath);
//    FileInputStream fis = new FileInputStream(propertiesFile);
    InputStream fis = IJdbcUtils.class.getClassLoader().getResourceAsStream("database.properties");

    Properties properties = new Properties();
    properties.load(fis);
    if (fis != null) {
      fis.close();
    }

    driverClass = properties.getProperty("driverClass");
    url = properties.getProperty("url");
    userName = properties.getProperty("userName");
    passWord = properties.getProperty("passWord");
  }

  /**
   * 获取链接对象
   * @return 链接对象
   */
  public static Connection getCon () throws SQLException {
    // 链接数据库
    return con = DriverManager.getConnection(url, userName, passWord);
  }

  /**
   * @param sql 语句
   * @return
   * @throws SQLException
   */
  public static PreparedStatement getPrepareStatement (String sql) throws SQLException {
    return IJdbcUtils.getPrepareStatement(sql, null);
  }

  /**
   * @param sql 语句
   * @param paramsMap 语句参数
   * @return
   * @throws SQLException
   */
  public static PreparedStatement getPrepareStatement (String sql, Map<Integer, String> paramsMap) throws SQLException {
    // 获取PreparedStatement对象
    PreparedStatement psd = getCon().prepareStatement(sql);
    // 设置参数
    if (paramsMap != null && !paramsMap.isEmpty()) {
      for (Integer key : paramsMap.keySet()) {
        psd.setObject(key, paramsMap.get(key));
      }
    }
    return psd;
  }

  /**
   * 获取查询结果
   * @param t 映射对象
   * @param sql
   * @return
   * @throws SQLException
   * @throws IllegalAccessException
   */
  public T getResultObj (T t, String sql)
      throws SQLException, IllegalAccessException {
    return this.getResultObj(t, sql, null);
  }

  public T getResultObj (T t, String sql, Map<Integer, String> paramsMap)
      throws SQLException, IllegalAccessException {
    // 获取PreparedStatement对象
    PreparedStatement psd = IJdbcUtils.getPrepareStatement(sql, paramsMap);
    // 获取 结果集
    ResultSet rst = psd.executeQuery();
    // 获取结果集对应的表信息
    // ResultSetMetaData rsmd = rst.getMetaData();

    Class c = t.getClass();
    // 获取类所对应的属性
    Field[] fields = c.getDeclaredFields();

    // 循环遍历结果集
    while (rst.next()) {
      for (Field field : fields) {
        field.setAccessible(true);

        // 判断类型进行赋值
        String fieldType = field.getType().getName();
        if (fieldType.equals(Integer.class.getTypeName())) {
          field.set(t, rst.getInt(field.getName()));
        } else if (fieldType.equals(String.class.getTypeName())) {
          field.set(t, rst.getString(field.getName()));
        }

      }
    }
    rst.close();
    psd.close();
    con.close();
    return t;
  }
  // 返回列表
  public List<T> getResultList (T t, String sql)
      throws SQLException, IllegalAccessException, InstantiationException {
    return this.getResultList(t, sql, null);
  }
  // 返回列表
  public List<T> getResultList (T t, String sql, Map<Integer, String> paramsMap)
      throws SQLException, IllegalAccessException, InstantiationException {
    // 获取PreparedStatement对象
    PreparedStatement psd = IJdbcUtils.getPrepareStatement(sql, paramsMap);
    // 获取 结果集
    ResultSet rst = psd.executeQuery();
    // 获取结果集对应的表信息
    // ResultSetMetaData rsmd = rst.getMetaData();

    Class c = t.getClass();
    // 获取类所对应的属性
    Field[] fields = c.getDeclaredFields();

    List<T> list = new ArrayList<>();

    // 循环遍历结果集
    while (rst.next()) {
      // 第一次使用初始对象
      for (Field field : fields) {
        field.setAccessible(true);
        // 判断类型进行赋值
        String fieldType = field.getType().getName();
        if (fieldType.equals(Integer.class.getTypeName())) {
          field.set(t, rst.getInt(field.getName()));
        } else if (fieldType.equals(String.class.getTypeName())) {
          field.set(t, rst.getString(field.getName()));
        } else {}
      }
      list.add(t);
      // 创建新对象
      t = (T)t.getClass().newInstance();
    }
    rst.close();
    psd.close();
    con.close();
    return list;
  }


  /**
   * 关闭各种链接、各种流
   */
  public static void close () {
    try {
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 将属性转换成其getter方法
  private String formatAttributeToSet (String name) {
    String first = name.substring(0, 1);
    String after = name.substring(1);
    return "get" + first.toUpperCase() + after;
  }
}
