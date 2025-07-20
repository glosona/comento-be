# 1. 개발 환경 세팅
## 1-1. mariaDB Connection
1. root 계정으로 MariaDB 접속
   ```
   mysql -u root -p
   ```
2. DBeaver에서 mariaDB 커넥션 진행
   * Server Host: localhost
   * Database: statistic

## 1-2. Spring MVC 환경설정 & API 호출
1. jetty 실행 설정
   * Run > Edit Configuration 에서 Maven 항목 추가
   * Run에 `jetty:run` 입력
2. Jetty 실행 후 Started Jetty 메시지 확인
3. 아래 주소에서 API 호출 확인
   ```
   localhost:9080/ping
   localhost:9080/requests
   ```