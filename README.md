# servlet
1.概念 server applet
运行在服务器端的小程序
2.servlet就是一个接口，定义了java类被浏览器访问到(Tomcat识别)的规则。
3.将来我们定义一个类，实现servlet接口，复写方法。

# jdbc
链接mysql数据库

# 目录
```bash
 |—— src
    |—— com
        |—— servletDemo // 各种类和接口
            |—— servlet
               |—— DemoServlet
               |—— UsersServlet
            |—— dao 
               |—— mapper文件
               |—— 接口
               |—— 实现类
            |—— entity // 实体类
            |—— service
               |—— 接口 
               |—— impl
                   |—— 实现类
            |—— utils
               |—— FileUtils
               |—— IJdbcUtils
               |—— MyJdbcTemplateUtils
 |—— web
    |—— scripts // js脚本
    |—— WEB-INF // 不能访问的资源
        |—— lib // jar包
    |—— index.jsp // 首页
 
```
# UsersServlet
使用WebServlet注解的方式配置servlet

# DemoServlet
使用在web.xml中配置的方式配置servlet