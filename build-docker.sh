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

# 镜像打包命名需要如下参数
commit_id=$(git rev-parse --short HEAD)
current_time=`date '+%Y%m%d%H%M%S'`
export image_version=$commit_id"-"$current_time

build_frontend() {
  cd $DIR/gateway/gateway-admin-ui/
  echo '开始删除网关管理平台前端包'
  rm -rf dist
  rm -rf docker/dist
  echo '结束删除网关管理平台前端包'
  yarn
  yarn build:docker
}

build_backend() {
  cd $DIR
  echo '开始打包后端包'
  mvn clean package -P${ENVIRONMENT} -Dmaven.test.skip=true
  echo '结束打包后端包'
}

build_docker() {
  cd $DIR
  echo '开始打包镜像,version为:'$image_version
  echo
  docker-compose -f docker-compose-build.yaml build
  echo '结束打包镜像,version为:'$image_version
}

push_docker() {
  echo '开始推送image至仓库'
  docker push dlhf/gateway-admin-bootstrap:$image_version
  docker push dlhf/gateway-admin-ui:$image_version
  docker push dlhf/gateway-bootstrap:$image_version
  docker push dlhf/monitor:$image_version
  docker push dlhf/sentinel-dashboard:$image_version
  echo '结束推送image至仓库'
}

build_frontend
build_backend
build_docker
push_docker