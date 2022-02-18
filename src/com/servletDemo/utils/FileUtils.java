package com.servletDemo.utils;
import java.io.File;

public class FileUtils {

  private static String absolutePath = null;
  // 获取绝对路径
  static {
    String filePath = FileUtils.class.getResource("/").getFile();
    // 创建File对象
    File file = new File(filePath);
    absolutePath = file.getPath();
  }

  public static String getAbsolutePath () {
    return absolutePath;
  }
}
