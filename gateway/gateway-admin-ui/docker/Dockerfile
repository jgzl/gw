FROM 10.91.196.30/library/nginx:1.21.3-alpine

MAINTAINER li7hai26@gmail.com

COPY ./dist /data

RUN rm /etc/nginx/conf.d/default.conf

ADD gateway-admin-ui.conf /etc/nginx/conf.d/default.conf

RUN /bin/sh -c 'echo init ok'