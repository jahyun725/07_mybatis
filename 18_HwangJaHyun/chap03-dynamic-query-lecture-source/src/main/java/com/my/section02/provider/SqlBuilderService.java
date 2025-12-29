package com.my.section02.provider;

import com.my.common.MenuDTO;
import org.apache.ibatis.session.SqlSession;
import static com.my.common.Template.getSqlSession;

/**
 * SqlBuilder 기능을 테스트하는 Service 클래스
 * INSERT, UPDATE, DELETE 동적 SQL 구문을 실행하고 트랜잭션을 처리
 */
public class SqlBuilderService {
    /**
     * 신규 메뉴 등록
     * SqlBuilder를 통해 생성된 INSERT 구문으로 메뉴를 등록하고 트랜잭션 처리
     * @param menuDTO 등록할 메뉴 정보
     */
    public void registMenu(MenuDTO menuDTO) {
        SqlSession sqlSession = getSqlSession();
        SqlBuilderMapper mapper = sqlSession.getMapper(SqlBuilderMapper.class);

        int result = mapper.insertMenu(menuDTO);

        if(result > 0) {
            sqlSession.commit();
            System.out.println("신규 메뉴 등록 완료");
        } else {
            sqlSession.rollback();
            System.out.println("신규 메뉴 등록 실패");
        }

        sqlSession.close();

    }

    /**
     * 메뉴 정보 수정
     * SqlBuilder를 통해 생성된 동적 UPDATE 구문으로 메뉴를 수정하고 트랜잭션 처리
     * 유효한 값만 UPDATE 대상에 포함됨
     * @param menuDTO 수정할 메뉴 정보
     */
    public void modifyMenu(MenuDTO menuDTO) {
        SqlSession sqlSession = getSqlSession();
        SqlBuilderMapper mapper = sqlSession.getMapper(SqlBuilderMapper.class);

        int result = mapper.updateMenu(menuDTO);

        if(result > 0) {
            sqlSession.commit();
            System.out.println("메뉴 수정 완료");
        } else {
            sqlSession.rollback();
            System.out.println("메뉴 수정 실패");
        }

        sqlSession.close();
    }

    /**
     * 메뉴 삭제
     * SqlBuilder를 통해 생성된 DELETE 구문으로 메뉴를 삭제하고 트랜잭션 처리
     * @param menuCode 삭제할 메뉴의 코드
     */
    public void deleteMenu(int menuCode) {
        SqlSession sqlSession = getSqlSession();
        SqlBuilderMapper mapper = sqlSession.getMapper(SqlBuilderMapper.class);

        int result = mapper.deleteMenu(menuCode);

        if(result > 0) {
            sqlSession.commit();
            System.out.println("메뉴 삭제 완료");
        } else {
            sqlSession.rollback();
            System.out.println("메뉴 삭제 실패");
        }

        sqlSession.close();

    }
}
