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
3. 자율 주제 API 문서 작성(80%)
    * 문서 위치: `./APIGuide_HayoungSon.pdf`
    * 자율주제 선정: 기존에 진행 중인 개인 프로젝트 "직주근접" 서비스를 활용
      * 사용자가 입력한 특정 주소(직장 등)를 기준으로, 통근에 유리한 주변 거주지를 추천하는 서비스
      * 직장, 발령 등으로 거처를 옮겨야 하는 사용자를 주요 타깃으로 설정
    * GET API 명세 2개 작성 완료
      * Path, Request Parameter, Response Message, Response Example 작성
    * REST API 규칙에 따른 HTTP 상태 코드 명세 추가 필요
    * 다수의 API 요청 시 백엔드 로직 부하가 우려되어 개선 필요한 상황
      * 성능 최적화를 위해 데이터베이스 구성 및 캐싱 적용을 검토 중
    * POST, PATCH, DELETE 메서드 활용 방안도 함께 모색 중

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
   
     
