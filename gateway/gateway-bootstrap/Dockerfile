FROM docker.io/adoptopenjdk:8u292-b10-jdk-hotspot

MAINTAINER li7hai26@gmail.com

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms4096m -Xmx4096m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /app

WORKDIR /app

EXPOSE 7000

ADD ./target/app.jar app.jar

CMD java $JAVA_OPTS -jar app.jar