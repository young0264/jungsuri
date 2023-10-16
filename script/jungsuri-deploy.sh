#!/usr/bin/env bash

PROJECT_ROOT="/home/ec2-user/jungsuri"
JAR_FILE="$PROJECT_ROOT/build/libs/jungsuri-0.0.1-SNAPSHOT.jar"

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

# build 파일 복사
echo "$TIME_NOW > $JAR_FILE 파일 복사" >> $DEPLOY_LOG
sudo cp $PROJECT_ROOT/jungsuri-0.0.1-SNAPSHOT.jar $PROJECT_ROOT

# jar 파일 실행ㄷㅋ
echo "$TIME_NOW > $JAR_FILE 파일 실행" >> $DEPLOY_LOG
nohup java -jar -Dspring.profiles.active=prod $JAR_FILE > $APP_LOG 2> $ERROR_LOG &

#프로파일 명시
CURRENT_PID=$(pgrep -f $JAR_FILE)
echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." >> $DEPLOY_LOG
#$ chmod +x ./deploy.sh  // 권한 부여
#$ ./deploy.sh           // 실행
#$ vim nohup.out         // 로그 확인