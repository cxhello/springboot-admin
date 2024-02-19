# 该镜像需要依赖的基础镜像
FROM eclipse-temurin:8u402-b06-jdk-centos7

# 指定维护者的名字
MAINTAINER cxhello

# 将指定目录下的jar包复制到docker容器的/目录下
COPY target/springboot-admin-0.0.1-SNAPSHOT.jar /export/Apps/springboot-admin/springboot-admin-0.0.1-SNAPSHOT.jar

# 声明服务运行在8080端口
EXPOSE 8080

# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar","/export/Apps/springboot-admin/springboot-admin-0.0.1-SNAPSHOT.jar"]
