#!/bin/sh
ENVIRONMENT="$1"
SOURCE="$0"
while [ -h "$SOURCE"  ]; do
    DIR="$( cd -P "$( dirname "$SOURCE"  )" && pwd  )"
    SOURCE="$(readlink "$SOURCE")"
    [[ $SOURCE != /*  ]] && SOURCE="$DIR/$SOURCE"
done
DIR="$( cd -P "$( dirname "$SOURCE"  )" && pwd  )"
echo 'build.sh脚本地址上层目录为' $DIR

cd $DIR/gateway/gateway-admin-ui/
echo '开始删除网关管理平台前端包'
rm -rf docker/dist
echo '结束删除网关管理平台前端包'
yarn
yarn build:docker

cd $DIR
mvn clean package -P${ENVIRONMENT} -Dmaven.test.skip=true
docker-compose -f docker-compose-build.yaml build