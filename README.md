# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## step1 질문 삭제하기 요구사항
* [X] 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
  * [X] question -> 삭제를 하는 경우 상태가 변경한다. (매개변수 삭제)
* [X] 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
  * [X] question 삭제 요청 시 loginUser와 다른 경우 exception throw
  * [X] question 삭제 요청 시 loginUser와 같으면 삭제한다.
* [X] 답변이 없는 경우 삭제가 가능하다.
  * [X] 질문에 답변이 있는지 체크하고 없으면 삭제한다.
* [X] 질문자와 답변 글의 모든 답변자 같은 경우 삭제가 가능하다.
  * [X] question에 해당하는 모든 답변을 조회할 수 있다.
  * [X] 답변자와 질문자가 다른지 체크할 수 있다.
  * [X] 답변 중에 questionUser와 하나라도 다른 경우 exception throw
  * [X] 답변이 loginUser와 모두 같으면 삭제한다.
* [X] 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
  * [X] answer -> 삭제하는 경우 상태가 변경한다. (매개변수 삭제)
* [X] 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
  * [X] 질문자와 답변자가 다른 경우 exception Throw
* [X] 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.
* [X] 질문이 추가되는 경우 답변이 추가된다.

## step1 리팩토링 todo
* [X] question -> 
  * [X] 여러 Answer를 저장하는 일급콜렉션 클래스를 생성한다.
  * [X] setTitle 삭제
  * [X] setContents 삭제
  * [X] setDeleted -> delete로 변경
  * [X] 인스턴스변수에 final
* [X] Answers indent줄이기
* [ ] questionRespository -> 
  * [ ] save method 생성
  * [ ] Question 객체 id가 자동 증가하며 저장 (find max id)
* [X] deleteHistories -> 삭제이력을 쌓는 경우 createDate 매개변수 줄이기
* [ ] AOP class 활용(생성시간, 업데이트 시간)
* [ ] 자주 사용되고 있는 인스턴스변수 포장해보기

## 궁금한점
* [X] answers를 처음에 빈 collection으로 만들고, 계속 add를 해서 새로운 객체로 반환하면 계속해서 인스턴스 생성되어 메모리 이슈는 없을지

## Step2 수강신청(도메인 모델) 기능 요구사항
* [X] 과정(Course)은 기수 단위로 운영하며, 여러 개의 강의(Session)를 가질 수 있다.
  * [X] 기수별로 과정을 가질 수 있다.
  * [X] 과정은 강의가 계속 추가될 수 있다. -> List<Session> -> Sessions
* [X] 강의는 시작일과 종료일을 가진다.
* [X] 강의는 강의 커버 이미지 정보를 가진다. 
  * [X] 이미지 크기는 1MB 이하여야 한다. 
  * [X] 이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용한다.
  * [X] 이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 height의 비율은 3:2여야 한다. 
* [X] 강의는 무료 강의와 유료 강의로 나뉜다. 
  * [X] 무료 강의는 최대 수강 인원 제한이 없다. 
  * [X] 유료 강의는 강의 최대 수강 인원을 초과할 수 없다. 
  * [X] 유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다. 
* [X] 강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다. 
* [X] 강의 수강신청은 강의 상태가 모집중일 때만 가능하다. 
* [X] 유료 강의의 경우 결제는 이미 완료한 것으로 가정하고 이후 과정을 구현한다. 
  * [X] 결제를 완료한 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반한된다.
## 리팩토링 Todo
* [ ] 시스템 ID, 시스템일시는 모두 AOP로 구성
* [ ] 기수(Group) 번호 순차 증가
* [ ] Session 인스턴스 변수 줄일 수 있을지
* [X] Answers 방어적 복사 보완

## 프로그래밍 요구사항
* DB 테이블 설계 없이 도메인 모델부터 구현한다. 
* 도메인 모델은 TDD로 구현한다. 
* 단, Service 클래스는 단위 테스트가 없어도 된다. 
* 다음 동영상을 참고해 DB 테이블보다 도메인 모델을 먼저 설계하고 구현한다.