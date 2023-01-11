package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //로딩 시점 단 한번 작업

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code 작성
        try{

//            Team teamA = new Team();
//            teamA.setName("teamA");
//            em.persist(teamA);
//
//            Team teamB = new Team();
//            teamB.setName("teamB");
//            em.persist(teamB);
//
//            Member member1 = new Member();
//            member1.setUsername("member1");
//            member1.setTeam(teamA);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("member2");
//            member2.setTeam(teamB);
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
////            Member m = em.find(Member.class, member1.getId());
////
////            System.out.println("m = " + m.getTeam().getClass());
//
//            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
//                            .getResultList();
//
//            System.out.println("========================");
//            member1.getTeam().getName(); //초기화
//            System.out.println(member1.getTeam().getName());
//            member2.getTeam().getName(); //초기화
//            System.out.println(member2.getTeam().getName());
//            System.out.println("========================");


            Member member = new Member();
            member.setUsername("hello");
            member.setHomeAddress("city", "street", "zipcode");
            member.setWorkPeriod(new Period());

            em.persist(member);
            //child까지 persist하는 작업을 하지 않고 연관관계에 따라 persist: cascade활용
//            em.persist(child1);
//            em.persist(child2);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }

        emf.close();
    }
}
