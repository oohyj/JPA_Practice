package jpabook.jpashop.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    //저장
    public void save(Member member){
        em.persist(member);
    }

    //단건 조회
    public Member findOne(Long id){
        return em.find(Member.class , id);
    }

    //리스트 조회
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class) //JPQL , 반환 타입
                .getResultList();
    }

    //이름으로 찾는 경우
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name =:name",Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
