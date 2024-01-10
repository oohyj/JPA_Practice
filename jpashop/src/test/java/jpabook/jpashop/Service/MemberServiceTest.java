package jpabook.jpashop.Service;

import jpabook.jpashop.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest // 이 두 가지가 있어야 스프링부트를 올려서 테스트를 할 수 있음
@Transactional // 테스트 과정에서 데이터 변경이 있기 때문에 rollback이 되게 해야함
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //given

        //when

        //then


    }


    @Test
    public void 중복_회원_예외() throws Exception{
        //given

        //when

        //then


    }
}