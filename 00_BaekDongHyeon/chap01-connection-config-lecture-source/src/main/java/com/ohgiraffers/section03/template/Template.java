package com.ohgiraffers.section03.template;

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

  private static final String RESOURCE = "mybatis-config.xml";


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
