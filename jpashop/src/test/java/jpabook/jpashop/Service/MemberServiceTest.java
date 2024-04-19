package jpabook.jpashop.Service;

import jpabook.jpashop.repository.MemberRepositoryOld;
import jpabook.jpashop.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class) //junit 실행할 때 , 스프링이랑 같이 엮어서 실행하려면
@SpringBootTest // 이 두 가지가 있어야 스프링부트를 올려서 테스트를 할 수 있음
@Transactional // 테스트 과정에서 데이터 변경이 있기 때문에 rollback이 되게 해야함
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepositoryOld memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("Yu");

        //when
        Long saveId = memberService.join(member); // 중복 회원 검증

        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }


    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
         Member member1 = new Member();
         member1.setName("Kim");

         Member member2 = new Member();
         member2.setName("Kim");

        //when
        memberService.join(member1);
        memberService.join(member2); // 같은 이름이니까 예외가 발생해야함 , 여기서 exception이 발생해서 then으로 진행이 되면 안됨

        //then
        fail("예외가 발생해야한다"); //테스트가 실패

    }
}