# SongLibrary

## 1. 프로젝트 목적
 - 수업 내용 복습과 개념 재확립 및 계층형 게시판 구조 설계와 구조에 맞는 게시글/댓글 등 CRUD 구현
 - 원하는 음악 쉽게 찾기 등의 편의성
 - 신곡은 노래방에 없는 경우가 많은데, 노래방에 신곡이 있나 없나 하나하나 찾아보기엔 번거롭고 귀찮으므로 한 눈에 알아보기 쉽게 하기 위함

## 2. 타겟
 - 노래 듣기를 좋아하는 불특정다수

## 3. 개발환경
 - 운영체제 : Windows10
 - Front-end : HTML, CSS, JS, Bootstrap
 - Back-end : JDK 11, Spring Framework
 - Database : MySQL
 - Server : Tomcat
 - Version Control : Git

## 4. 개발 일정
 - 기간 : 2022.03.14 ~ 2022.03.31

|날짜|내용|
|---|---|
|2022.03.14 ~ 2022.03.15|계획 설계| 
|2022.03.16 ~ 2022.03.20|레이아웃 설계 및 수정|
|2022.03.21 ~ 2022.03.23|Member CRUD 구현|
|2022.03.24 ~ 2022.03.28|Board CRUD 구현|
|2022.03.29 ~ 2022.03.31|오류 수정 및 마무리|

## 5. 주요기능 개발 우선순위

|우선순위|기능|
|---|---|
|1|Member : 로그인 / 회원가입 / 아이디찾기 / 비밀번호찾기<br>Board : 글 등록 / 글 상세보기 / 파일관리 / 관련 API 가져오기 / 크롤링|
|2|Member : 내정보 / 정보수정 / 회원탈퇴<br>Board : 글 수정 / 글 삭제 / 글 상세보기 / 관련 API 활용하기 / 크롤링 활용|
|3|페이징 / 검색 / 댓글 / 원하는 가수 알림|
|4|기타 세부기능|

## 6. 명명규칙
 - Project : PascalCase(UpperCamelCase) 적용
 - Folder, File : PascalCase(UpperCamelCase) 적용
 - Class, Interface : PascalCase(UpperCamelCase) 적용
 - Method
   - 이동(Mapping) : goTo --
   - 컨트롤러(Controller) : -- Controller
 - 주석 : Controller(Mapping) / Service(Method) 설명 기재

## 7. Font


![11](https://user-images.githubusercontent.com/91529033/158154105-a2d0c2f4-6a2c-4040-b35c-85591cc19bb4.png)
