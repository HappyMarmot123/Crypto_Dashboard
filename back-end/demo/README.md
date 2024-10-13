## Spring boot

1. 톰캣이 내장 탑재되어서 Boot Dashboard 에서 바로 실행가능
2. application.yml 톰캣 세팅과 db 커넥션 세팅하면 끝.
   빈 팩토리 애노테이션으로 필요한 데이터를 자바문서에서 끌어올 수 있다.
   - @Value("${spring.data.mongodb.uri}")
3. static에 class 선언하는 이유
   - 메모리 절약, 인스턴스 없이 호출
   - 외부 호출이 없을 경우에 유용하다.

## MongoDB Connect

1. root App에 mongo annotation 선언.
2. MongoTemplate 사용 하기 위해서 빈 객체를 만들어야 한다.
3. JSON 문자열로 데이터를 삽입하면 몽고db가 알아서 Object로 변환후 받는다.
4. Aggregate 복잡한 통계 구현 가능 MongoTemplate을 사용하여 DB에 소통
5. \_id를 id로 매핑하거나 String 자료형으로 선언해도 ㅆ!ㅣ발 에러가 안뜨니 유의할것.

- @MongoId
- private ObjectId \_id

## Lombok

1. 그래들 디펜던시는 lombok 기능 적용안됨 ㅆㅂ lombok.jar 설치해야 함
