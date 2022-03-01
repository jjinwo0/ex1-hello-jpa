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
            //영속
            Member member = new Member(150L, "A");
            member.setName("ZZZZZ");

//            em.persist(member); -> 데이터를 수정하고 나면 다시 persist 해줘야하지 않을까?
            //JPA -> 데이터베이스의 객체화가 목적
            //자바에서는 객체의 값이 변경된다고 해서 뭔가 새롭게 값을 대입하는 구문을 넣을 이유가 없다.
            //JPA도 동일

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
