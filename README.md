# Medi-Insight_backend

This one is a back-end project for Medi-Insight.

***

### 本仓库采用了 `Springboot3` + `Vue3` 框架来实现前后端分离项目

### 提示

由于包含系统包含管理员、医生和患者三种角色，已经对每种角色进行权限隔离，每个角色的业务接口需要按照如下命名方式：

```bash
/api/admin/** or /api/doctor/** or /api/patient/**
```

### 后端技术栈

- 采用 `Mybatis-Plus` 作为持久层框架，使用更加便捷


- 采用 `Redis` 存储注册和重置操作的验证码，并带过期时间限制


- 采用 `Redis` 进行IP地址限流处理，防刷接口


- 采用 `RabbitMQ` 积压短信发送任务，再由监听器统一处理


- 采用 `SpringSecurity` 作为权限校验框架，同时整合 `Jwt` 校验方案


- 视图层对象和数据层对象分离，编写工具方法利用反射快速互相转换


- 错误和异常页面统一采用JSON格式返回，前端处理响应更统一


- 手动处理跨域，采用过滤器实现

### 前端技术栈

- 采用 `Vue-Router` 作为路由


- 采用 `Axios` 作为异步请求框架


- 采用 `Element-Plus` 作为UI组件库
