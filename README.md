
![](https://user-gold-cdn.xitu.io/2018/11/12/16705ec180084551?w=673&h=373&f=png&s=184964)

## 技术选型
|技术| 版本|
|---|---|
|Spring boot|2.0.6 |
|Swagger2||
|Spring security|5.0.9|
|docker|最新|
|Mysql|5.8 |
|java|8|
|mybatis-plus| 3.0.6|
|Redis| 最新|

## 项目介绍
git 地址：==>  [jpsiet-v1](https://github.com/jiangeeq/jpsite-v1)
* 集成Swagger API文档自动生成功能，提供丰富的API管理
* 支持一二级缓存，使得性能到达极致（一级缓存是由 ConcurrentHashMap ，二级缓存使用 redis ）
* session登录成功存储 redis 中，实现 sso单点登录
* session 并发数，过期时间可随意指定
* 带有@ResponseBody 的返回 json 格式实现自定义 type 返回格式
* 所有http请求拦截打印日志
* 支持开启异步线程配置，处理多任务
* REST接口开发规范
* 基于Spring boot构建，配置文件能少则少
* 基于Spring security 的权限验证      
  1. 图形验证码，短信验证码，手机登录，微信登录，QQ登录
  2. 可用配置文件指定哪些 request url 需要验证码校验及验证码类型
  3. 记住我功能
* 集成xxl-job轻量级分布式任务调度平台
* 集成Apollo配置中心   
* 集成 actuator 详细的应用监控，包括http，线程栈，内存等信息 
* 集成 docker 的容器化构建，开箱即用
* 集成Zookeeper
* 集成 MyBatis-Plus   
  1. 指定表名生成对应的 entity, dao, service , impl ,controller 类文件。
  2. 自定义风格包名名称，模块名称，文件名称
  3. 热加载、代码生成、分页、性能分析等功能一应俱全。 

* 集成Spring Data Elastic方便使用ElasticSearch
* **使用drools规则引擎的风控系统基础功能   （后续开发）**
    1. 账号：垃圾注册、撞库、盗号等
    2. 交易：盗刷、恶意占用资源、篡改交易金额等
    3. 活动：薅羊毛
    4. 短信：短信轰炸
    5. 原则上可以动态配置规则

## mysql 环境安装
```
docker pull mysql
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql
```
![](https://user-gold-cdn.xitu.io/2018/11/10/166fe1b8061275ab?w=1952&h=106&f=png&s=69014)

## xxl-job本地docker安装
`git clone https://github.com/xuxueli/xxl-job.git`

### 修改配置文件
[Spring Boot 属性配置和使用](https://blog.csdn.net/isea533/article/details/50281151)   
xxl-job/xxl-job-admin/src/main/resources/application.properties
```
### xxl-job, datasource
spring.datasource.url=jdbc:mysql://${MYSQL_PORT_3306_TCP_ADDR:MYSQL_PORT_3306_TCP_PORT}/xxl-job?Unicode=true&characterEncoding=UTF-8
spring.datasource.password=root
```
* MYSQL_PORT_3306_TCP_ADDR
* MYSQL_PORT_3306_TCP_PORT

分别是获取系统环境变量配置

### 调度中心docker镜像构建
可以通过以下命令快速构建调度中心，并启动运行；
```
mvn clean install package   //maven打包
docker build -t xuxueli/xxl-job-admin ./xxl-job-admin  //构建image
docker run --name xxl-job-admin -p 8089:8080 --link mysql -d xuxueli/xxl-job-admin    // 创建并启动container
docker exec xxl-job-admin env   //查看系统env
```
![](https://user-gold-cdn.xitu.io/2018/11/10/166fe1b8061275ab?w=1952&h=106&f=png&s=69014)
--link后mysql的系统环境变量

![](https://user-gold-cdn.xitu.io/2018/11/10/166fe1dca255d1b6?w=1860&h=1136&f=png&s=401863)

![](https://user-gold-cdn.xitu.io/2018/11/10/166fe1e212197a72?w=1950&h=1082&f=png&s=218053)
http://localhost:8089/xxl-job-admin/
## apollo配置中心搭建
```
git clone https://github.com/nobodyiam/apollo-build-scripts.git
```
### 创建ApolloPortalDB
通过各种MySQL客户端导入sql/apolloportaldb.sql即可。
### 创建ApolloConfigDB
通过各种MySQL客户端导入sql/apolloconfigdb.sql即可。
### 配置数据库连接信息
编辑demo.sh，修改ApolloPortalDB和ApolloConfigDB相关的数据库连接串信息。
```
# apollo config db info
apollo_config_db_url=jdbc:mysql://${MYSQL_PORT_3306_TCP_ADDR}:${MYSQL_PORT_3306_TCP_PORT}/ApolloConfigDB?characterEncoding=utf8
apollo_config_db_username=root
apollo_config_db_password=root

# apollo portal db info
apollo_portal_db_url=jdbc:mysql://${MYSQL_PORT_3306_TCP_ADDR}:${MYSQL_PORT_3306_TCP_PORT}/ApolloPortalDB?characterEncoding=utf8
apollo_portal_db_username=root
apollo_portal_db_password=root
```
**注意：不要修改demo.sh的其它部分**
### docker容器化
apollo-build-scripts目录下
```
build -t apollo-quick-start .  #dockerfile所在目录
docker run --name apollo-admin -p 8070:8070 -p 8080:8080 -p 8090:8090 --link mysql -d apollo-quick-start
```
启动成功，如果启动失败可以把启动日志copy到宿主机查看
```
docker cp apollo-admin:/apollo-quick-start/service/apollo-service.log .
docker cp apollo-admin:/apollo-quick-start/portal/apollo-portal.log .
```

![](https://user-gold-cdn.xitu.io/2018/11/11/16701ead4387745c?w=2400&h=1332&f=png&s=287410)
![](https://user-gold-cdn.xitu.io/2018/11/11/16701ec1ed5d8fb0?w=2400&h=1332&f=png&s=556523)
因为Eureka的Instance Info IP为docker内部IP，外部无法使用
![](https://user-gold-cdn.xitu.io/2018/11/11/167029de824698ac?w=1492&h=308&f=png&s=30190)
所以添加vm启动参数为宿主机ip
![](https://user-gold-cdn.xitu.io/2018/11/11/167029d682aefa21?w=1882&h=470&f=png&s=79986)
源码阅读解惑
```
/Users/haha/.m2/repository/com/ctrip/framework/apollo/apollo-client/1.0.0/apollo-client-1.0.0-sources.jar!/com/ctrip/framework/apollo/internals/RemoteConfigRepository.java:313
/Users/haha/.m2/repository/com/ctrip/framework/apollo/apollo-client/1.0.0/apollo-client-1.0.0-sources.jar!/com/ctrip/framework/apollo/internals/ConfigServiceLocator.java:64
/Users/haha/.m2/repository/com/ctrip/framework/apollo/apollo-client/1.0.0/apollo-client-1.0.0-sources.jar!/com/ctrip/framework/apollo/internals/ConfigServiceLocator.java:75
```
http://localhost:8080
http://localhost:8070
http://localhost:8090
