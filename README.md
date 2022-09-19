# Mission1

# 구성
- Java
    - controller
        - ConnService : SQLite 연결, 연결 해제, 롤백 등.
        - HistoryService : History 정보 저장, 불러오기, 삭제.
        - HttpService : 오픈 API에 http 요청을 보내서 데이터 가져오기, Json 파일 가공.
        - WifiService : Wifi 정보 저장, 불러오기.
    - db : 프로젝트에 사용 될 데이터
        - HistoryData
        - WifiData
- Webapp
    - history : Wifi 위치 정보를 조회했던 History 정보를 보여주는 화면. 삭제 기능 구현.
    - home : 프로젝트 홈 화면. 내 위치 찾기 기능 구현. 위치와 가까운 Wifi 정보 가져오기 기능 구현. 오픈 API와 통신하여 정보 불러오기 기능 구현.
    - load_info : 오픈 API와 통신 성공 시 정보를 저장하며 노출되는 화면.

# 사용한 기능과 정보
- 서울시 공공 Wifi 서비스 위치 정보
- Eclipse IDE, Maven
- JAVA (JDK 11)
- Tomcat v8.5
- okhttp3 (4.9.3)
- gson (2.9.0)
- lombok (1.18.24)
- sqlite-jdbc (3.36.0.3)
- HTML, CSS, JS
