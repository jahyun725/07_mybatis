package com.ohgiraffers.section03.template;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class Application2 {
  public static void main(String[] args) {

    SqlSession sqlSession = Template.getSqlSession();

    List<String> menuNameList
        = sqlSession.selectList("mapper.selectMenuNameList");

    menuNameList.forEach(System.out::println);

    sqlSession.close();
  }
}
