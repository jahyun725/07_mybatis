package com.ohgiraffers.section02.javaconfig;

import com.ohgiraffers.section01.xmlconfig.MenuDAO;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.DelayQueue;

/* SqlSessionFactory 객체를 하나만 만들어서 계속 재사용
*  --> Singleton 패턴
*
* */

public class Template {

  private static SqlSessionFactory sqlSessionFactory = null;

  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String URL = "jdbc:mysql://localhost:3306/menudb";
  private static final String USERNAME = "swcamp";
  private static final String PASSWORD = "swcamp";

  /* mybatis-config.xml 설정이 적용된 SqlSession 생성 메서드
  * - SqlSession : Mybatis의 DB 연결 및 SQL 수행 객체
  * */
  public static SqlSession getSqlSession() {

    if (sqlSessionFactory == null) {
      try {
        // 1. 환경 설정 객체 만들기
        Environment environment = new Environment(
          "development",
          new JdbcTransactionFactory(),
          new PooledDataSource(DRIVER,URL, USERNAME, PASSWORD)
        );

        Configuration configuration = new Configuration(environment);

        // 2. 매퍼 등록
        configuration.addMapper(MenuMapper.class);

        // 3. SqlSessionFactoryBuilder를 이용해 SqlSessionFactory 생성
        sqlSessionFactory
            = new SqlSessionFactoryBuilder().build(configuration);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

    return sqlSessionFactory.openSession(false);
  }

}
