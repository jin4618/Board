## :pushpin: Board
>게시판 프로젝트[미니]


</br>

### 1. 제작기간 & 참여 인원
* 2024.05.23. ~ 2024.05.24.
* 개인 프로젝트 (1인)

</br>

### 2. 사용기술
* IDE : IntelliJ Ultimate 2024.1
* Famework : Spring 6.1.6
* dependencies : Spring Boot DevTools, Lombok, Spring Web, Spring Data JDBC, Thymeleaf
* Language : Java 21, Html 5, CSS
* Database : MySQL 8.0.33

</br>

### 3. DB 테이블 설계 및 Sample Data
---

0. 테이블 삭제

```
drop table board;
```

---

1. **Board 테이블**
   - 사용자 정보를 저장합니다.

```sql
CREATE TABLE board (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,
title VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL, -- 암호는 해싱하여 저장하는 것이 좋습니다
content TEXT NOT NULL,
created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 등록일
updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정일
);

```

---

2. **예제 데이터 삽입**
```sql
INSERT INTO board (name, title, password, content) VALUES
('김민수', '첫 번째 글입니다!', 'password123', '이것은 첫 번째 게시글의 내용입니다.'),
('이지은', '봄철 원예 팁', 'password123', '봄철 정원 가꾸기에 유용한 팁을 공유합니다.'),
('박영희', '올해의 여행지 추천 10곳', 'password123', '올해 방문하기 좋은 여행지 10곳을 소개합니다.'),
('최준호', 'SQL 이해하기', 'password123', 'SQL과 그 기능들에 대해 깊이 있게 알아봅시다.'),
('황소연', '최고의 코딩 습관', 'password123', '모든 개발자가 따라야 할 코딩 습관에 대해 알아봅시다.'),
('백은영', '사진 촬영 기초', 'password123', '초보자를 위한 사진 촬영 가이드입니다.'),
('윤서준', '기술의 미래', 'password123', '기술과 혁신의 미래에 대한 예측을 해봅니다.'),
('한지민', '예산 내에서 건강하게 먹기', 'password123', '예산을 깨지 않고 건강하게 먹는 방법을 공유합니다.'),
('정태웅', '초보자를 위한 운동 루틴', 'password123', '운동을 막 시작한 사람들을 위한 효과적인 운동 루틴을 소개합니다.'),
('김서영', '지역 뉴스 업데이트', 'password123', '최신 지역 뉴스를 업데이트합니다.'),
('남주혁', '새로운 영화 개봉', 'password123', '이번 주말 개봉하는 새로운 영화들을 확인해보세요.');

```

---
</br>

### 4. 핵심기능
>Client ( 글 등록, 글 목록 보기, 글 상세 조회, 글 수정, 글 삭제 )   
1. 글 등록
   - 이름, 제목, 암호, 본문을 입력
   - 등록일, ID는 자동으로 저장
  
2. 글 목록 보기
   - 최신 글부터 보여짐
   - ID, 제목, 이름, 등록일(YYYY/MM/DD) 형식으로 목록이 보여짐
   - 페이징 처리
     
3. 글 상세 조회
   - 글 등록일, 수정일 YYYY/MM/DD hh24:mi 형식으로 보여짐

4. 글 수정
   - 이름, 제목, 본문을 수정
   - 글 등록 시 입력했던 암호를 입력하여 글 수정
   - 수정일은 자동으로 저장
     
5. 글 삭제
   - 글 등록 시 입력했던 암호를 입력하여 글 삭제
     
</br>





