package com.ohgiraffers.section03.remix;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.section03.remix.Template.getSqlSession;

/* Service 계층
 * - 비즈니스 로직 처리 계층
 * - 데이터 가공 또는 DAO(DB CRUD) 호출, 트랜잭션 관리
 * */
public class MenuService {

  /**
   * 전체 메뉴 조회
   *
   * @return menuList
   */
  public List<MenuDTO> selectAllMenu() {

    // 1. SqlSession 얻어오기
    SqlSession sqlSession = getSqlSession();

    // 2. SQL 수행 후 결과 반환 받기
    MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
    List<MenuDTO> menuList = menuMapper.selectAllMenu();

    // 3. SqlSession 메모리 반환
    sqlSession.close();

    // 4. 결과 반환
    return menuList;
  }

  /**
   * 메뉴 코드가 일치하는 메뉴 조회
   *
   * @param menuCode
   * @return menu
   */
  public MenuDTO selectMenuByMenuCode(int menuCode) {

    SqlSession sqlSession = getSqlSession();

    MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
    MenuDTO menu = menuMapper.selectMenuByMenuCode(menuCode);

    sqlSession.close();

    return menu;
  }

  public boolean registMenu(MenuDTO menu) {

    SqlSession sqlSession = getSqlSession();

    MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
    // insert된 결과 행의 개수를 반환 받아 저장
    int result = menuMapper.insertMenu(menu);

    if (result > 0)
      sqlSession.commit();
    else
      sqlSession.rollback();

    sqlSession.close();

    return result > 0;
  }

  public boolean modifyMenu(MenuDTO menu) {

    SqlSession sqlSession = getSqlSession();

    MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
    int result = menuMapper.updateMenu(menu);

    if (result > 0)
      sqlSession.commit();
    else
      sqlSession.rollback();

    sqlSession.close();

    return result > 0;
  }

  public boolean deleteMenu(int menuCode) {

    SqlSession sqlSession = getSqlSession();

    MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
    int result = menuMapper.deleteMenu(menuCode);

    if (result > 0)
      sqlSession.commit();
    else
      sqlSession.rollback();

    sqlSession.close();

    return result > 0;
  }

}