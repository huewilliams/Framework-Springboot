REPOSITORY=/home/ec2-user/server/Master-SpringBoot
PROJECT_NAME=webservice-with-aws
TEST=/home/ec2-user/server/Master-SpringBoot/\[book\]webservice-with-aws

echo "> Build 파일 복사"

sudo cp $REPOSITORY/zip/*.jar $REPOSITORY/[book]webservice-with-aws

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -fl webservice-with-aws | grep jar | awk '{print $1}')

echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    sudo kill -15 $CURRENT_PID
    sleep 5
fi

echo "> 새 애플리케이션 배포"

cd $REPOSITORY/[book]webservice-with-aws
ls -al

JAR_NAME=$(ls -tr *.jar | tail -n 1)

echo "> JAR NAME : $JAR_NAME"

echo "> $JAR_NAME에 실행권한 추가"

sudo chmod u+x $JAR_NAME
sudo chmod u+w+x $REPOSITORY/nohup.out

echo "> $JAR_NAME 실행"

sudo nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,application-oauth.properties,application-real-db.properties \
    -Dspring.profiles.active=real \
    $JAR_NAME > $TEST/nohup.out 2>&1 &