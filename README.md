# demo
spring boot+jwt+hibernate valid+mybatis plus+dubbo + 权限检验 的小demo


# 准备
1.安装mysql执行 demo/demo-admin/src/main/resources/demo.sql 进行建表
2.安装zookeeper启动，地址可以在demo/demo-admin/src/main/resources/application.yml 修改
3.启动项目 com/thrcat/DemoApplication.java


# 测试

登陆/非登陆校验
- 请求 http://localhost:8080/echo/hello 进行登陆校验
{
"code": 20003,
"data": null,
"success": false,
"message": "未登录"
}

- 请求 http://localhost:8080/echo/hello1 不进行登陆校验
{
"code": 20000,
"data": "hello1",
"success": true,
"message": "查询成功"
}

---
权限校验
- 请求超级管理员接口 http://localhost:8080/echo/super

{
"code": 20002,
"data": null,
"success": false,
"message": "无权限访问SUPER_ADMIN接口"
}

- 请求管理员接口 http://localhost:8080/echo/manager

{
"code": 20002,
"data": null,
"success": false,
"message": "无权限访问MANAGER接口"
}
- 请求用户接口 http://localhost:8080/echo/user

{
"code": 20002,
"data": null,
"success": false,
"message": "无权限访问USER接口"
}

---
登陆校验
- 登陆校验，会生成登陆token
http://localhost:8080/login/in?username=sucx&password=3cat

{
"code": 20000,
"data": "登陆成功",
"success": true,
"message": "查询成功"
}

