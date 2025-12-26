package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

/* DAO(Data Access Object, DB 접근 객체)
* - 실질적으로 DB에 연결되는 객체
* - SQL을 수행하고 결과를 반환받는 역할
* */
public class MenuDAO {

  public List<MenuDTO> selectAllMenu(SqlSession sqlSession) {
    // sqlSession.SQL호출메서드(namespace.id)
    return sqlSession.selectList("MenuMapper.selectAllMenu");
  }


  public MenuDTO selectMenuByMenuCode(SqlSession sqlSession, int menuCode){
    return sqlSession.selectOne("MenuMapper.selectMenuByMenuCode", menuCode);
  }

}
