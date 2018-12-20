# 指定以openjdk:8-jdk-alpine3.8 为基础镜像，来构建此镜像，可以理解为运行的需要基础环境
FROM openjdk:8-jdk-alpine3.8
# VOLUME 指向了一个/tmp的目录，由于 Spring Boot 使用内置的Tomcat容器，Tomcat 默认使用/tmp作为工作目录。这个命令的效果是：在宿主机的/var/lib/docker目录下创建一个临时文件并把它链接到容器中的/tmp目录
VOLUME /tmp
# 拷贝文件并且重命名
ADD jpsite-client-1.0-SNAPSHOT.jar /app.jar
#容器启动时执行的命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]