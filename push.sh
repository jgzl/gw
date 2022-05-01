#!/bin/sh
image_version=0.0.1-SNAPSHOT
echo '开始推送镜像至dockerhub仓库'
docker push dlhf/gateway-admin-bootstrap:$image_version
docker push dlhf/gateway-admin-ui:$image_version
docker push dlhf/gateway-bootstrap:$image_version
docker push dlhf/monitor:$image_version
docker push dlhf/sentinel-dashboard:$image_version
echo '结束推送镜像至dockerhub仓库'