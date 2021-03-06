#!/bin/sh
SOURCE="$0"
while [ -h "$SOURCE"  ]; do
    DIR="$( cd -P "$( dirname "$SOURCE"  )" && pwd  )"
    SOURCE="$(readlink "$SOURCE")"
    [[ $SOURCE != /*  ]] && SOURCE="$DIR/$SOURCE"
done
DIR="$( cd -P "$( dirname "$SOURCE"  )" && pwd  )"
echo $0 '脚本地址上层目录为' $DIR
cd $DIR

if [ -z $IMAGE_VERSION ]; then
  echo "Usage: $0 {gitCommitId-currentTime}/8728616-20220502130315"
  exit 1;
fi

if [ -z $IMAGE_REPO_URL ]
then
  export IMAGE_REPO_URL=docker.io/dlhf
fi

docker-compose -f docker-compose-run.yaml pull
docker-compose -f docker-compose-run.yaml up -d
