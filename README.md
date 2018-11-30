## mysql 环境安装
```
docker pull mysql
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql
```
![](https://user-gold-cdn.xitu.io/2018/11/10/166fe1b8061275ab?w=1952&h=106&f=png&s=69014)

## xxl-job本地docker安装
`git clone https://github.com/xuxueli/xxl-com.mty.jpsite.server.job.git`

### 修改配置文件
[Spring Boot 属性配置和使用](https://blog.csdn.net/isea533/article/details/50281151)   
xxl-com.mty.jpsite.server.job/xxl-com.mty.jpsite.server.job-admin/src/main/resources/application.properties
```
### xxl-com.mty.jpsite.server.job, datasource
spring.datasource.url=jdbc:mysql://${MYSQL_PORT_3306_TCP_ADDR:MYSQL_PORT_3306_TCP_PORT}/xxl-com.mty.jpsite.server.job?Unicode=true&characterEncoding=UTF-8
spring.datasource.password=root
```
* MYSQL_PORT_3306_TCP_ADDR
* MYSQL_PORT_3306_TCP_PORT

分别是获取系统环境变量配置

### 调度中心docker镜像构建
可以通过以下命令快速构建调度中心，并启动运行；
```
mvn clean install package   //maven打包
docker build -t xuxueli/xxl-com.mty.jpsite.server.job-admin ./xxl-com.mty.jpsite.server.job-admin  //构建image
docker run --name xxl-com.mty.jpsite.server.job-admin -p 8089:8080 --link mysql -d xuxueli/xxl-com.mty.jpsite.server.job-admin    // 创建并启动container
docker exec xxl-com.mty.jpsite.server.job-admin env   //查看系统env
```
![](https://user-gold-cdn.xitu.io/2018/11/10/166fe1b8061275ab?w=1952&h=106&f=png&s=69014)
--link后mysql的系统环境变量

![](https://user-gold-cdn.xitu.io/2018/11/10/166fe1dca255d1b6?w=1860&h=1136&f=png&s=401863)

![](https://user-gold-cdn.xitu.io/2018/11/10/166fe1e212197a72?w=1950&h=1082&f=png&s=218053)
http://localhost:8089/xxl-com.mty.jpsite.server.job-admin/
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