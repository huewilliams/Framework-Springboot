#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

REPOSITORY=/home/ec2-user/server/Master-SpringBoot
PROJECT_NAME=webservice-with-aws
TEST=/home/ec2-user/server/Master-SpringBoot/\[book\]webservice-with-aws

echo "> Build 파일 복사"

sudo cp $REPOSITORY/zip/*.jar $REPOSITORY/[book]webservice-with-aws

echo "> 새 애플리케이션 배포"

cd $REPOSITORY/[book]webservice-with-aws
JAR_NAME=$(ls -tr *.jar | tail -n 1)

echo "> JAR NAME : $JAR_NAME"

echo "> $JAR_NAME에 실행권한 추가"

sudo chmod u+x $JAR_NAME

echo "> $JAR_NAME 실행"

IDLE_PROFILE=$(find_idle_profile)

echo "> $JAR_NAME 를 profile=$IDLE_PROFILE 로 실행합니다."

sudo nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,application-oauth.properties,application-real-db.properties \
    -Dspring.profiles.active=$IDLE_PROFILE \
    $JAR_NAME > $TEST/nohup.out 2>&1 &