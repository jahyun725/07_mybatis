package com.kth.section01.javaconfig;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class Application {

  private static String DRIVER = "com.mysql.cj.jdbc.Driver";
  private static String URL = "jdbc:mysql://localhost:3306/menudb";
  private static String USER = "swcamp";
  private static String PASSWORD = "swcamp";


  public static void main(String[] args) {

    /* 1. Environment 객체 생성 : DB 접속 환경 설정
    * JdbcTransactionFactory
    * - 수동 커밋 모드 설정 -> 트랜잭션 제어를 JDBC가 한다
    *
    * PooledDataSource
    * - 커넥션풀 사용
    * - Connection Pool
    *  - DB 연결 정보를 담은 커넥션을 미리 만들어 모아두는 것
    *  - 미리 만들어둔 커넥션을 요청 시 1개씩 빌려줌
    *  - 사용이 끝난 커넥션은 반환 받음
    * */
    Environment environment = new Environment(
        "dev",
        new JdbcTransactionFactory(),
        new PooledDataSource(DRIVER, URL, USER, PASSWORD)
    );

    /* 2. Configuration 객체 생성
    * - 마이바티스 전체 설정 정보를 담는 객체
    * */
    Configuration configuration = new Configuration(environment);

    /* 3. Mapper 등록
    * - SQL문이 정의된 인터페이스를 마이바티스에 알림
    * */
    configuration.addMapper(Mapper.class);


    /* 4. SqlSessionFactory 객체 생성
    * - SqlSession을 생성하기 위한 팩토리 설정
    * */
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);


    /* 5. SqlSession 오픈
    * SqlSession : 실제 DB와 연결되어 상호작용하는 객체
    * openSession(false) : 수동 커밋 모드로 Session을 생성
    * */
    SqlSession sqlSession = sqlSessionFactory.openSession(false);


    /* 6. Mapper 인터페이스 꺼내기
    * - 마이바티스가 Mapper 인터페이스를
    *   상속 받은 구현체(Proxy객체)를 알아서 만들어 반환
    *  */
    Mapper mapper = sqlSession.getMapper(Mapper.class);

    /* 7. 추상 메서드를 호출하여 쿼리 실행 */
    java.util.Date now = mapper.selectDate();
    System.out.println("now = " + now);

    /* 8. SqlSession 닫기 */
    sqlSession.close();
  }
}
