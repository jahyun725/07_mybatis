package com.my.springmybatis.config;

import com.my.springmybatis.section01.factorybean.MenuDTO;
import com.my.springmybatis.section01.factorybean.MenuMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Bean 등록 + 내부 메서드 모두 실행해서 설정 등록
public class MybatisConfig {
  @Value("${spring.datasource.driver-class-name}")
  private String driverClassName;

  @Value("${spring.datasource.jdbc-url}")
  private String jdbcUrl;

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  @Bean
  public HikariDataSource dataSource(){
    HikariDataSource dataSource = new HikariDataSource();

    /* HikariCP 설정 추가
    * 연결하는 커넥션을 만들어주겠다*/
    dataSource.setDriverClassName(driverClassName);
    dataSource.setJdbcUrl(jdbcUrl);
    dataSource.setUsername(username);
    dataSource.setPassword(password);

    /* 커넥션 풀 설정 */
    /* 커넥션 요청 후 획득 대기 시간 */
    dataSource.setConnectionTimeout(30000); // 30초

    /* 풀에서 동시에 유지 가능한 최대 커넥션 수 */
    dataSource.setMaximumPoolSize(5);

    /* 사용하지 않는 커넥션 유휴 시간(유통기한) */
    dataSource.setIdleTimeout(60000); // 60초

    /* 커넥션의 최대 생명 주기 설정(오래된 커넥션을 주기적으로 교체) */
    dataSource.setMaxLifetime(1800000);  // 30분

    return dataSource;
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {

    org.apache.ibatis.session.Configuration configuration
        = new org.apache.ibatis.session.Configuration();
    configuration.getTypeAliasRegistry().registerAlias("MenuDTO", MenuDTO.class);
    configuration.addMapper(MenuMapper.class);
    configuration.setMapUnderscoreToCamelCase(true);

    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean(); // sql 팩토리 객체 생성
    sqlSessionFactoryBean.setDataSource(dataSource());
    sqlSessionFactoryBean.setConfiguration(configuration);

    return sqlSessionFactoryBean.getObject();

  }

  // SqlSessionTemplate : SqlSession + openSession() + close()
  @Bean
  public SqlSessionTemplate sqlSessionTemplate() throws Exception {

    return new SqlSessionTemplate(sqlSessionFactory());
  }
}
