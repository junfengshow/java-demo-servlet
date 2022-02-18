package com.servletDemo.demos;

import java.io.*;
import com.servletDemo.utils.FileUtils;
// mybatis的Resources 工具类
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSession;

import com.servletDemo.dao.UserMapper;
import com.servletDemo.entity.UserEntity;

// 文件相关操作
public class FileDemo {
  public void main () {
    System.out.println("this is main");
  }
  public void main2 () {
    try {
      String resource = FileUtils.getAbsolutePath() + "/resources/mybatis.config.xml";
      // System.out.println(new File(resource).exists());
      // 加载配置文件
      InputStream inputStream = Resources.getResourceAsStream("resources/mybatis.config.xml");
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession session = sqlSessionFactory.openSession();
      UserMapper mapper = session.getMapper(UserMapper.class);

      UserEntity user = mapper.getUserById();
      session.close();
      System.out.println(user);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void main1 () {
//    System.out.println(FileUtils.getAbsolutePath());
//    File file1 = new File(FileUtils.getAbsolutePath() + "/statistic/users.json");
//    System.out.println(file1.exists());
    // 根据类所在目录寻找
    File file = new File(FileDemo.class.getResource("/").getPath());
    // 获取绝对路径
    String absolutePath = file.getParentFile().getParentFile().getParentFile().getPath();

    // 创建父类文件
    File parent = new File(absolutePath + "/src/statistic/");
    // 获取user.json文件
    File jsonFile = new File(parent, "users.json");

    // 定义字节输入流变量
    InputStream fis = null;
    // 定义字节输出流对象变量
    FileOutputStream fos = null;
    try{

      File targetFile = new File(parent, "a.json");

      // 创建文件输出流对象
      fos = new FileOutputStream(targetFile, true);
      // 文件输入流对象
      fis = new FileInputStream(jsonFile);
      int len;
      byte[] bytes = new byte[10];

      while ((len = fis.read(bytes)) != -1) {
        // System.out.println(new String(bytes,0, len));
        // 将读入的字节流写入到文件输出流
        fos.write(bytes, 0, len);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // 关流
      try {

        if (fis != null) {
          fis.close();
        }
        if (fos != null) {
          fos.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
