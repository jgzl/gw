FROM docker.io/adoptopenjdk:8u292-b10-jdk-hotspot

MAINTAINER li7hai26@gmail.com

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"
ENV SERVER_PORT=10000

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /app

WORKDIR /app

EXPOSE $SERVER_PORT

ADD target/sentinel-dashboard.jar app.jar

CMD java $JAVA_OPTS -Dcsp.sentinel.dashboard.server=localhost:$SERVER_PORT -jar app.jar