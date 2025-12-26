package com.ohgiraffers.section02.xmlconfig;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Application {

  // Mybatis 설정 파일의 경로(resources 폴더 기준)
  private static final String RESOURCE = "mybatis-config.xml";

  public static void main(String[] args) {

    try {
      // 1. 설정 파일 읽어올 스트림 만들기
      InputStream inputStream = Resources.getResourceAsStream(RESOURCE);

      // 2. SqlSessionFactoryBuilder를 이용해 SqlSessionFactory 생성
      SqlSessionFactory sqlSessionFactory
          = new SqlSessionFactoryBuilder().build(inputStream);

      // 3. SqlSessionFactory를 이용해 SqlSession 생성
      // openSession(false) : 자동 커밋이 off된 세션 얻어오기
      SqlSession sqlSession = sqlSessionFactory.openSession(false);

      // 4. SQL 수행 후 결과 반환 받기
      // - selectOne() : 조회 결과가 1행인 SQL을 수행 후 결과 반환 받기
      // - "mapper.selectDate" : 등록된 매퍼 중 namespace가 "mapper"인 xml 파일을 찾아
      //                        id가 "selectDate"인 SQL을 선택
      java.util.Date now = sqlSession.selectOne("mapper.selectDate");

      System.out.println("now = " + now);

      sqlSession.close();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
