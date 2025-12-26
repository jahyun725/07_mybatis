package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/* SqlSessionFactory 객체를 하나만 만들어서 계속 재사용
*  --> Singleton 패턴
*
* */

public class Template {

  private static SqlSessionFactory sqlSessionFactory = null;

  private static final String RESOURCE = "com/ohgiraffers/section01/xmlconfig/mybatis-config.xml";

  /* mybatis-config.xml 설정이 적용된 SqlSession 생성 메서드
  * - SqlSession : Mybatis의 DB 연결 및 SQL 수행 객체
  * */
  public static SqlSession getSqlSession() {

    if (sqlSessionFactory == null) {
      try {
        // 1. 설정 파일 읽어올 스트림 만들기
        InputStream inputStream = Resources.getResourceAsStream(RESOURCE);

        // 2. SqlSessionFactoryBuilder를 이용해 SqlSessionFactory 생성
        sqlSessionFactory
            = new SqlSessionFactoryBuilder().build(inputStream);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    return sqlSessionFactory.openSession(false);
  }

}
