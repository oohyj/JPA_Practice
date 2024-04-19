package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long > {
    //Name을 보고 spring date jpa가 쿼리를 만듦 : select m from Member m where m.name =?
    List<Member> findByName(String name);
}
