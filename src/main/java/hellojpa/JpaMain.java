package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //로딩 시점 단 한번 작업

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code 작성
        try{

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team); //양방향 매핑 편의 메서드
            em.persist(member);

            team.addMember(member); //Team 쪽에서도 가능

            //양쪽에 값 세팅
            //Member의 changeTeam 메서드를 연관관계 편의 메서드로 설정 -> 양쪽으로 값을 설정할 수 있도록
//            team.getMembers().add(member); //JPA에서는 특별한 동작을 취하지 않음
            // but, 항사 양쪽에 값을 설정하는 습관 필요
            //작성하지 않으면 객체지향적으로 문제점 발생
            //1. flush(), clear() 동작을 하지 않았을 시 -> 1차 캐시에 남아있는 값 때문에 insert 쿼리 문이 작성되지 않음
            //2. 테스트 코드 실행 시

            //1차 캐시 초기화
            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            System.out.println("======================");
            for (Member m : members){
                System.out.println("m = "+m.getUsername());
            }
            System.out.println("======================");

            tx.commit(); //커밋 시점에서 변경 상태를 확인
            //변경점이 발견되었을 시 UPDATE 쿼리문 생성
        }catch (Exception e){
            tx.rollback();
        }finally{
            em.close();
        }

        emf.close();
    }
}
