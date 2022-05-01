FROM docker.io/adoptopenjdk:8u292-b10-jdk-hotspot as builder
RUN mkdir -p /app
WORKDIR /app
ADD ./target/sentinel-dashboard.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM docker.io/adoptopenjdk:8u292-b10-jdk-hotspot
MAINTAINER li7hai26@gmail.com

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN mkdir -p /app

WORKDIR /app
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/application/ ./

ENV SERVER_PORT=9100
ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms1024m -Xmx1024m -Djava.security.egd=file:/dev/./urandom"

EXPOSE $SERVER_PORT

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Dserver.port=$SERVER_PORT \
   -Dcsp.sentinel.dashboard.server=localhost:$SERVER_PORT \
   -Dproject.name=sentinel-dashboard \
   org.springframework.boot.loader.JarLauncher"]