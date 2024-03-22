# 该镜像需要依赖的基础镜像
FROM eclipse-temurin:8u402-b06-jdk

# 指定维护者的名字
LABEL org.opencontainers.image.authors="cxhello"

ENV TZ=Asia/Shanghai

RUN apt-get update && \
    apt-get install -yq tzdata && \
    ln -fs /usr/share/zoneinfo/${TZ} /etc/localtime && \
    dpkg-reconfigure -f noninteractive tzdata && \
    rm -rf /var/lib/apt/lists/*

# 声明服务运行在8080端口
EXPOSE 8080

WORKDIR /data

# 将指定目录下的jar包复制到docker容器的/目录下
COPY target/springboot-admin-1.0.0.jar springboot-admin-1.0.0.jar

# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar","springboot-admin-1.0.0.jar"]
