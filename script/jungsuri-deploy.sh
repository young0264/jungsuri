#!/usr/bin/env bash

#/** 배포시 진행 */

PROJECT_ROOT="/home/ec2-user/jungsuri"
JAR_FILE="$PROJECT_ROOT/build/libs/jungsuri-0.0.1-SNAPSHOT.jar"
APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

# 현재 구동 중인 애플리케이션 pid 확인
CURRENT_PID=$(pgrep -f $JAR_FILE)
#echo "pid가 왜 안나오니1 : " CURRENT_PID >> $DEPLOY_LOG # 이건 문자 current_pid가 나옴
echo "pid가 왜 안나오니2 : " "$CURRENT_PID" >> $DEPLOY_LOG #실행중인게 없으니 안나오는건 맞음.

# 프로세스가 켜져 있으면 종료
if [ -z $CURRENT_PID ]; then
  echo "$TIME_NOW > 현재 실행중인 애플리케이션이 없습니다" >> $DEPLOY_LOG
else
  echo "$TIME_NOW > 실행중인 $CURRENT_PID 애플리케이션 종료 " >> $DEPLOY_LOG
  kill -15 $CURRENT_PID
fi

# build 파일 복사
echo "$TIME_NOW > $JAR_FILE 파일 복사" >> $DEPLOY_LOG
cp "$JAR_FILE" "$PROJECT_ROOT/" #파일 복사 정상적으로 됨

# cp :: "/home/ec2-user/jungsuri/build/libs/jungsuri-0.0.1-SNAPSHOT.jar" "/home/ec2-user/jungsuri"
# nohup java -jar -Dspring.profiles.active=prod $JAR_FILE > $APP_LOG 2> $ERROR_LOG &
# nohup java -jar -Dspring.profiles.active=prod /home/ec2-user/jungsuri/build/libs/jungsuri-0.0.1-SNAPSHOT.jar

# jar 파일 실행
echo "$TIME_NOW > $PROJECT_ROOT /jar 파일 실행" >> $DEPLOY_LOG
#nohup java -jar -Dspring.profiles.active=prod "$PROJECT_ROOT"/jungsuri-0.0.1-SNAPSHOT.jar > "$APP_LOG" 2 > "$ERROR_LOG &" #& 위치 등 잘못
nohup java -jar -Dspring.profiles.active=prod \
-DrdsUrl=$rdsUrl \
-DdbPassword=$dbPassword \
-Dprofile=$profile \
-DweatherAPIKey=$weatherAPIKey \
-DmailSendLoginId=$mailSendLoginId \
-DmailSendPassword=$mailSendPassword \
-jooqVersion=$jooqVersion \
-accessKey=$accessKey \
-DdbLoginId=$dbLoginId \
 "$PROJECT_ROOT"/jungsuri-0.0.1-SNAPSHOT.jar > "$APP_LOG" 2> "$ERROR_LOG" &


# nohup java -jar -Dspring.profiles.active=prod /home/ec2-user/jungsuri/jungsuri-0.0.1-SNAPSHOT.jar > /home/ec2-user/jungsuri/application.log 2> /home/ec2-user/jungsuri/error.log &
#nohup java -jar -Dspring.profiles.active=prod /home/ec2-user/jungsuri/jungsuri-0.0.1-SNAPSHOT.jar > "$APP_LOG" 2 > "$ERROR_LOG &"
#프로파일 명시
NEW_PID=$(pgrep -f $JAR_FILE)
#echo "current pid 입니다 : " CURRENT_PID >> $DEPLOY_LOG # 문자가 나옴
echo "달러 new pid 입니다 : $NEW_PID" >> $DEPLOY_LOG
echo "$TIME_NOW > 실행된 프로세스 아이디 $NEW_PID 입니다." >> "$DEPLOY_LOG"
