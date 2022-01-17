#!/bin/sh
ENVIRONMENT="$1"

check_env() {
  if [ -z $ENVIRONMENT ]; then
    echo "Usage: $0 {dev|sit|uat|pp|prod}"
    exit 1;
  fi

  CMD_ARRAY=(java node docker docker-compose)
  for i in ${CMD_ARRAY[@]} ; do
    type $i
    if [ "$?" -ne 0 ]; then
      echo "$i command not found."
      exit 1;
    fi
  done
}

check_env

SOURCE="$0"
while [ -h "$SOURCE"  ]; do
    DIR="$( cd -P "$( dirname "$SOURCE"  )" && pwd  )"
    SOURCE="$(readlink "$SOURCE")"
    [[ $SOURCE != /*  ]] && SOURCE="$DIR/$SOURCE"
done
DIR="$( cd -P "$( dirname "$SOURCE"  )" && pwd  )"
echo $0 '脚本地址上层目录为' $DIR

cd $DIR/gateway/gateway-admin-ui/
echo '开始删除网关管理平台前端包'
rm -rf docker/dist
echo '结束删除网关管理平台前端包'
yarn
yarn build:docker

cd $DIR
echo '开始打包后端包'
mvn clean package -P${ENVIRONMENT} -Dmaven.test.skip=true
echo '结束打包后端包'

cd $DIR
echo '开始打包镜像'
docker-compose -f docker-compose-build.yaml build
echo '结束打包镜像'