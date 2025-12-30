package com.ohgiraffers.springmybatis.section01.factorybean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/* 단위 테스트
* - Mockito를 활용하여 의존성을 모킹(Mocking) 후
*   MenuService의 순수 비즈니스 로직을 검증
* 
* - Mockito : 가짜 객체(Mock)를 만들어서 
*   해당 객체가 어떤 상황에서 어떤 값을 반환할지 정의할 수 있게 하는
*   테스트 프레임워크
* */
@ExtendWith(MockitoExtension.class)
class MenuServiceTest {
  
  @Mock // 모의 객체로 생성
  private SqlSessionTemplate sqlSession; // 실제 DB 연결 없이도 메서드 호출 가능
  
  @Mock
  private MenuMapper menuMapper; // Mybatis가 Mapper를 실제 구현하지 않아도 메서드 호출 가능  

  @InjectMocks // 모의 객체를 MenuService에 주입 (DI 대체)
  private MenuService menuService;


  @BeforeEach // 테스트 메서드가 매번 수행되기 전에 실행
  public void setUp(){
    Mockito
        .when(sqlSession.getMapper(MenuMapper.class))
        .thenReturn(menuMapper);
  }


  /* 1. 주문 가능 상태("Y")를 전달하는 테스트 시나리오 */
  @Test
  void findAllMenuByOrderableStatus_Y() {
    // given
    String orderableStatus = "Y";

    MenuDTO menu1
        = new MenuDTO(
            1,
            "김치찌개",
            9000,
            1,
            orderableStatus);

    MenuDTO menu2
        = new MenuDTO(
            1,
            "된장찌개",
            8000,
            1,
            orderableStatus);

    List<MenuDTO> originalList = Arrays.asList(menu1, menu2);

    // 모의 객체 menuMapper의 메서드 수행 시 originalList가 반환하도록 설정
    // (originalList가 DB 조회 결과)
    Mockito.when(
        menuMapper.findAllMenuByOrderableStatus(orderableStatus)
    ).thenReturn(originalList);



    // when
    List<MenuDTO> resultList
        = menuService.findAllMenuByOrderableStatus(orderableStatus);


    // then
    
    // 주어진 결과 값이 올바른 비즈니스 로직을 통해 가공 되었는지 확인
    assertNotNull(resultList); // null이 아니면 성공
    assertEquals(2, resultList.size());
    assertEquals("김치찌개(주문 가능)", resultList.get(0).getMenuName());
    assertEquals("된장찌개(주문 가능)", resultList.get(1).getMenuName());

    /* 해당 객체에서 메서드 호출 여부 확인
    * -> 서비스 내부의 상호 작용이 기대한대로 이루졌는지 확인
    * */
    Mockito.verify(sqlSession)
        .getMapper(MenuMapper.class);
    
    Mockito.verify(menuMapper)
        .findAllMenuByOrderableStatus(orderableStatus);
  }


  /* 2. 주문 가능 상태("N")를 전달하는 테스트 시나리오 */
  @Test
  void findAllMenuByOrderableStatus_N() {
    // given
    String orderableStatus = "N";

    MenuDTO menu1
        = new MenuDTO(
        1,
        "김치찌개",
        9000,
        1,
        orderableStatus);

    MenuDTO menu2
        = new MenuDTO(
        1,
        "된장찌개",
        8000,
        1,
        orderableStatus);

    List<MenuDTO> originalList = Arrays.asList(menu1, menu2);

    // 모의 객체 menuMapper의 메서드 수행 시 originalList가 반환하도록 설정
    // (originalList가 DB 조회 결과)
    Mockito.when(
        menuMapper.findAllMenuByOrderableStatus(orderableStatus)
    ).thenReturn(originalList);



    // when
    List<MenuDTO> resultList
        = menuService.findAllMenuByOrderableStatus(orderableStatus);


    // then

    // 주어진 결과 값이 올바른 비즈니스 로직을 통해 가공 되었는지 확인
    assertNotNull(resultList); // null이 아니면 성공
    assertEquals(2, resultList.size());
    assertEquals("김치찌개(주문 불가능)", resultList.get(0).getMenuName());
    assertEquals("된장찌개(주문 불가능)", resultList.get(1).getMenuName());

    /* 해당 객체에서 메서드 호출 여부 확인
     * -> 서비스 내부의 상호 작용이 기대한대로 이루졌는지 확인
     * */
    Mockito.verify(sqlSession)
        .getMapper(MenuMapper.class);

    Mockito.verify(menuMapper)
        .findAllMenuByOrderableStatus(orderableStatus);

  }


}