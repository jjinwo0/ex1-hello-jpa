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

            Member member1 = new Member();
            member1.setUsername("A");

            Member member2 = new Member();
            member2.setUsername("B");

            Member member3 = new Member();
            member3.setUsername("C");

            System.out.println("======================");

//            Hibernate:
//            call next value for member_seq -> 첫 호출은 SEQ = 1
//            Hibernate:
//            call next value for member_seq -> 두 번째 호출은 SEQ = 51

            //DB_SEQ : 1 | 1
            //DB_SEQ : 51 | 2
            //DB_SEQ : 51 | 3
            //50개 마다 sequence 호출 (미리 칸을 만들어둔다고 생각하면 편함)
            em.persist(member1); //1, 51
            em.persist(member2); //MEMORY
            em.persist(member3); //MEMORY

            System.out.println("member.id : "+member1.getId());
            System.out.println("member.id : "+member2.getId());
            System.out.println("member.id : "+member3.getId());

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
