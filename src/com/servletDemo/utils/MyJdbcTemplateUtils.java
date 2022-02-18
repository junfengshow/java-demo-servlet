package com.servletDemo.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class MyJdbcTemplateUtils {
  private static Properties properties = null;
  private static DataSource ds = null;
  static {
    try {
      properties = getProperties();
      System.out.println(properties);
      ds = DruidDataSourceFactory.createDataSource(properties);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  private static Properties getProperties () throws Exception {

    // 创建文件
    InputStream fis = MyJdbcTemplateUtils.class.getClassLoader().getResourceAsStream("database.properties");

    Properties properties = new Properties();
    properties.load(fis);
    if (fis != null) {
      fis.close();
    }
    return properties;
  }
  public static DataSource getDataSource () {
    return ds;
  }
}
