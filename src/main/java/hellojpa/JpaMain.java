package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("m1 = "+refMember.getClass());

            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("reference = "+findMember.getClass());

            System.out.println("refMember == findMember : "+(refMember == findMember));

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally{
            em.close();
        }

        emf.close();
    }
}
