package com.ohgiraffers.service;

import com.ohgiraffers.dto.CategoryAndMenuDTO;
import com.ohgiraffers.dto.MenuAndCategoryDTO;
import com.ohgiraffers.dto.MenuDTO;
import com.ohgiraffers.mapper.ElementTestMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.common.Template.getSqlSession;

public class ElementTestService {
    public void selectResultMapTest() {
        SqlSession sqlSession = getSqlSession();
        ElementTestMapper mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<MenuDTO> menuList = mapper.selectResultMapTest();

        if(menuList != null && !menuList.isEmpty()) {
            menuList.forEach(System.out::println);
        } else {
            System.out.println("조회 결과 없음");
        }

        sqlSession.close();
    }

    public void selectResultMapAssociationTest() {
        SqlSession sqlSession = getSqlSession();
        ElementTestMapper mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<MenuAndCategoryDTO> menuList = mapper.selectResultMapAssociationTest();

        if(menuList != null && !menuList.isEmpty()) {
            menuList.forEach(System.out::println);
        } else {
            System.out.println("조회 결과 없음");
        }

        sqlSession.close();
    }

    public void selectResultMapCollectionTest() {
        SqlSession sqlSession = getSqlSession();
        ElementTestMapper mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<CategoryAndMenuDTO> categoryList = mapper.selectResultMapCollectionTest();

        if(categoryList != null && !categoryList.isEmpty()) {
            categoryList.forEach(System.out::println);
        } else {
            System.out.println("조회 결과 없음");
        }

        sqlSession.close();
    }
}