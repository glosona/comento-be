# 4주차(08/14/25)
## [과제보고]
1. 5가지 API 개발 및 리팩토링
   1. `getLoginCount`: `year`, `month`, `day` 파라미터를 입력받아 연도별/월별/일자별 로그인 수 반환. year은 필수
   2. `getLoginCountByDepartment`: 가독성을 위해 부서별 로그인 수 조회 분리. date 부분은 옵션
   3. `getDayAverage`: 일자 평균 로그인 수 조회. (모든 로그인 기록) / (총 일자 수) 으로 계산
   4. `getNonRestdayLoginCount`: 쉬는 날 제외한 로그인 수 반환. 주말뿐만 아니라 공공API 연동하여 대한민국 공휴일도 제외함. `fromDate`, `toDate`로 범위 설정 가능
   5. `getAllNonRestdayLoginCount`: 범위 설정 없이 모든 기록에서 쉬는 날 제외한 로그인 수 반환
       ```
          /api/v1/logins?year=2024
          /api/v1/logins/department/A
          /api/v1/logins/dayAverage
          /api/v1/logins/nonRestday
          /api/v1/logins/nonRestday/all
       ```
2. 한국천문연구원 공공API 연동
   1. 특정 월의 대한민국 공휴일 정보 반환
   2. 공휴일 이름, 날짜를 DB에 저장하는 `getRestdaysInRange` 서비스 로직 작성
   3. 테스트용 API 작성
      ```
      /api/v1/restday
      /api/v1/restday/items
      ```
3. @ControllerAdvice으로 validation 작성
   1. year, month, day 검토
   2. fromDate, toDate 검토
4. API가이드 문서 보완: https://documenter.getpostman.com/view/46469267/2sB3BGJVgP#intro

# 3주차(08/07/25)
## 3-1) 스프링 vs 스프링부트
각각 환경설정을 진행하면서 느낀 차이점/공통점을 정리했다.
### 공통점
* Maven을 사용했기에 두 환경 모두 `pom.xml`을 통해 dependency를 관리한다.
* DI, AOP, MVC 등 핵심 Spring 기능은 동일하게 사용된다.
* `@Controller`, `@Service` 등의 공통 Spring 어노테이션을 사용할 수 있다.

### 차이점
* **서버 설정**: 스프링은 `jetty` build를 직접 pom에 작성 및 설정했으나, 스프링부트는 내장 `Tomcat`이 포함돼있기에 추가 설정 없이 바로 실행 가능했다.
* **의존성 관리**: 스프링은 `pom.xml`의 의존성과 버전을 직접 수동으로 명시해야 했지만, 스프링부트는 `start.spring.io`에서 기본 pom과 구조를 자동 생성해줬다.
* **Web 설정**: 스프링은 `applicationContext-webapp.xml`을 통해 servlet-context를 직접 설정했지만, 스프링 부트에서는 `@RestController` 등의 어노테이션 기반으로 자동 설정됐다.
* **데이터소스 설정**: 스프링은 `applicationContext-datasource.xml` 등을 직접 작성해야 했으나, 스프링부트에서는 `application.properties`로 간단히 설정할 수 있었다.
* **프로젝트 초기 구성**: 스프링은 구조/설정 파일을 직접 구성해야 했지만, 스프링부트는 `start.spring.io`로 Web, JPA, Lombok 등의 구성을 자동화할 수 있었다.

더 알아보니 스프링부트는 내부적으로 BOM(Bill of Materials) 방식으로 의존성 버전을 통합 관리해 버전 충돌 가능성을 줄여준다고 한다.

## [과제 보고]
1. 스프링 환경과 스프링 부트 환경 비교 작성 ([# 3-1)](#3-1-스프링-vs-스프링부트))
2. SW활용현황 API 중 연, 연월 기준 로그인 수 조회 API 개발
   * YearCountDto.java, YearMonthCountDto.java 작성 
    ```
    localhost:8031/api/v1/logins/24
    localhost:8031/api/v1/logins/24/04
    ```
3. 마지막 주차 API 구현에 필요한 SQL 작성
   * 월별 접속자 수
      ```mysql
      SELECT
        CONCAT('20', LEFT(create_date, 4)) AS month,
        COUNT(*) AS totCnt
      FROM request_info
      WHERE LEFT(create_date, 4) = '2305';
      ```
   * 일자별 접속자 수
     ```mysql
     SELECT
       CONCAT('20', LEFT(create_date, 6)) AS day,
       COUNT(*) AS totCnt
     FROM request_info
     WHERE LEFT(create_date, 6) = '230531';
     ```
   * 평균 하루 로그인 수
     ```mysql
     SELECT
       COUNT(DISTINCT LEFT(create_date, 6)) AS totDay,
       COUNT(*) AS totCnt,
       ROUND(COUNT(*) / COUNT(DISTINCT LEFT(create_date, 6)), 2) AS dayAverage
     FROM request_info;
     ```
   * 휴일을 제외한 로그인 수(주말만 적용)
     ```mysql
     SELECT
       COUNT(*) AS totCnt,
       COUNT(CASE WHEN DAYOFWEEK(STR_TO_DATE(create_date, '%y%m%d%H%i')) IN (1, 2) THEN 1 END) AS weekdayCnt,
       COUNT(CASE WHEN DAYOFWEEK(STR_TO_DATE(create_date, '%y%m%d%H%i')) NOT IN (1, 2) THEN 1 END) AS nonHolydayCnt
     FROM request_info;
     ```
   * 부서별 월별 로그인 수
     ```mysql
     SELECT
        CONCAT('20', LEFT(ri.create_date, 4)) AS yearMonth, 
        hr_organ AS department, 
        COUNT(LEFT(ri.create_date, 4)) AS totCnt
     FROM request_info ri JOIN user
     ON ri.user_id = user.user_id
     WHERE hr_organ = 'A' and LEFT(ri.create_date, 4) = '2305';
     ```


# 2주차(07/31/25)
## 2-1) HTTP 통신이란?
* HTTP(Hypertext Transfer Protocol)는 웹 브라우저와 서버가 하이퍼텍스트, 즉 웹 페이지의 내용을 주고받을 때 사용하는 규칙이다.
### HTTP 구조
1. 요청
   ```
   <요청라인>
   <헤더들>
   <빈 줄>
   <본문(Body) (옵션)>
   ```
   ```
   GET /api/info HTTP/1.1
   Host: example.com
   User-Agent: Mozilla/5.0
   Accept: application/json
   ```

2. 응답
   ```
   <상태라인>
   <헤더들>
   <빈 줄>
   <본문(Body)>
   ```
   ```
   HTTP/1.1 200 OK
   Content-Type: application/json
   Content-Length: 123
   
   {
      "id": 1,
      "conent": "Hello World"
   }
   ```
### HTTP의 주요 특징
1. **Client-Server**: 웹 브라우저(클라이언트)가 요청을 보내고, 서버가 응답을 반환하는 구조
2. **Connectionless**: HTTP 요청이 완료되면 클라이언트와 서버 연결은 유지되지 않고 종료된다.
3. **Stateless**: 클라이언트 또는 서버는 이전 요청의 상태를 기억하지 않는다.

### 주요 메서드
* GET: 자원 조회
* POST: 자원 생성
* PUT: 자원 전체 수정
* PATCH: 자원 일부 수정
* DELETE: 자원 삭제

### 상태 코드
* 200: 요청 성공
* 201: 생성 성공
* 400: 잘못된 요청
* 401: 인증 실패
* 404: 자원 없음
* 500: 서버 내부 오류 

### URL 입력부터 요청, 서버 응답까지의 흐름
1. 사용자 입력: 브라우저 주소창에 URL 입력
2. DNS 조회
3. TCP 연결
4. HTTP 요청 전송
5. 서버 처리
6. HTTP 응답 전송
7. 브라우저 렌더링

## 2-2) REST API란?
* REST(Representational State Transfer)는 웹에서 데이터를 주고받기 위한 규칙이다.
* REST는 HTTP를 기반으로 동작하지만, 모든 HTTP API가 RESTful한 것은 아니다. REST를 따를 것인지는 API를 설계하는 이들이 판단하여 결정한다.

### REST의 기본 규칙
  1. 자원(Resource)은 URL로 표현한다.
  2. 행동(Action)은 HTTP 메서드로 표현한다.
  3. 자원에는 명사를 사용하고, 동사는 쓰지 않는다.
  4. 서버는 요청 결과에 따라 적절한 HTTP 상태 코드를 반환해야 한다.

### REST 아키텍처의 6가지 구성 원칙
  1. **Client-Server 구조**: 클라이언트와 서버는 독립적으로 개발되고 배포된다.
  2. **Stateless**: 서버는 요청 간 클라이언트 상태를 저장하지 않는다.
  3. **Cache**: 응답 데이터는 캐싱이 가능해야 한다.
  4. **Uniform Interface**
     1. URI로 리소스를 식별된다.
     2. 리소스 조작은 HTTP 메시지를 통해 표현한다.
     3. 메시지만 봐도 온전한 해석이 가능해야 한다.
     4. 상태는 하이퍼링크(HATEOAS)를 통해 전이돼야 한다.
  5. **Layered System**: API 서버는 여러 중간 계층(프록시, 게이트웨이)을 통해 구성될 수 있다.
  6. **Code-on-Demand**(optional)

## [과제 보고]
1. HTTP 통신 학습
   * 개념, 구조, 특징, 메서드 학습
   * 크롬 브라우저의 개발자 모드를 통해 다양한 HTTP 요청 관측
2. REST API 학습
    * 개념, 기본 규칙, 아키텍처 구성 원칙 학습
    * 작성한 API 가이드가 REST 규칙을 따르는지 복습 필요
3. API 문서 작성
   * 문서 위치:
   * 주제에 맞게 재작성 필요(sw 활용률)

# 1주차(07/20/25)
## [과제 보고]
1. 개발 환경 세팅(완료)
   * IntelliJ, MariaDB, DBeaver 설치
   * DB 연동 및 statistic 테이블 생성
   * Spring MVC 환경설정
   * datasource & mybatis 연동
   * 테스트용 API 설계
      ```
      localhost:9080/ping
      localhost:9080/requests
      ```
     
### 피드백
1. 환경 설정 문서 작성: 버전 정보와 스크린샷을 포함하여 내용을 풍부히 작성하는 것이 좋다.
2. 개발 환경 호환성: 실무에서는 Docker를 활용하거나 기존 설치 디렉토리를 카피하여 환경 세팅을 빠르게 하는 경우가 많다.
3. Maven 의존성 관리: 스프링에서는 pom.xml을 통해 의존성을 관리하지만, 폐쇄망 환경에서는 라이브러리를 직접 다운로드하여 폴더에 추가해야할 수 있다. 또, 실무에서는 문제가 생겼을 경우 동료와 환경 차이를 비교분석하여 해결할 수 있다.
4. MVC 패턴 및 데이터 접근 기능 이해
   1. URL 요청이 오면 연관 controller로 요청을 받는다.
   2. service 영역에서 데이터 처리를 담당한다.
   3. MyBatis를 활용하여 데이터베이스와 상호작용한다.(쿼리의 효율을 위해 사용)
   4. VO 클래스는 데이터 형식을 정의하는 데 사용된다.
   * 복잡한 쿼리를 위해 MyBatis에 hashmap, List 형태로 리턴할 수 있다.
   
     
