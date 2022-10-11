# Karaokelon 

## 1. 프로젝트 목적 
 - 신곡이 발매되어도 노래방에 나오지 않는 곡이 훨씬 많다. 
 - 부르고 싶은 노래가 있는데 이게 노래방에 안나왔다면?
 - 매번 나왔는지 안나왔는지 확인하는 것은 매우 번거로우므로 한 눈에 보기 편하게

## 2. 타겟 
 - 노래 듣기를 좋아하고 노래방을 좋아하는 남녀노소 불특정다수

## 3. 개발환경 
 - 운영체제 : Windows10
 - Front-end : HTML, CSS, JS, Bootstrap
 - Back-end : JDK 11, Spring Framework
 - Database : MySQL
 - Server : Tomcat
 - Version Control : Git

## 4. 개발 일정
 - 기간 : 2022.06.30 ~ 2022.07.25

|날짜|내용| 
|---|---|
|2022.06.30 ~ 2022.07.01|계획 설계| 
|2022.07.02 ~ 2022.07.07|레이아웃 설계 및 수정|
|2022.07.08 ~ 2022.07.08|Member CRUD 구현| 
|2022.07.09 ~ 2022.07.11|Board CRUD 구현|
|2022.07.12 ~ 2022.07.23|MainContent 구현|
|2022.07.24 ~ 2022.07.25|오류 수정 및 마무리| 

## 5. 주요기능 개발 우선순위 

|우선순위|기능|
|---|---|
|1|MainContents : 크롤링 / 크롤링데이터 가공 및 활용|
|2|Member : 로그인 / 회원가입 <br>Board : 글 등록 / 글 상세보기 |
|3|Member : 내정보<br>Board : 글 상세보기|
|4|페이징 / 댓글 / 원하는 가수 알림|
|5|기타 세부기능|

## 6. 명명규칙
 - Project : PascalCase(UpperCamelCase) 적용
 - Folder, File : PascalCase(UpperCamelCase) 적용
 - Class, Interface : PascalCase(UpperCamelCase) 적용
 - Method
   - 이동(Mapping) : goTo --
   - 컨트롤러(Controller) : -- Controller
 - 주석 : Controller(Mapping) / Service(Method) 설명 기재


[ERD.drawio.pdf](https://github.com/kangbokyun/SpringAPI/files/9751011/ERD.drawio.pdf)
