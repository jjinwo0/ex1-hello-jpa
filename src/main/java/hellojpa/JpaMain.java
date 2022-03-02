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
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

            //준영속 : 객체를 영속성 컨텍스트에서 빼냄
            em.detach(member);

            //영속성 컨텍스트를 통째로 삭제
            em.clear();

            //영속성 컨텍스트를 초기화 후 같은 값을 조회한다면, 쿼리 문을 다시 작성하게 되고 새롭게 영속속
            Member memer2 = em.find(Member.class, 150L);

            //영속성 컨텍스트를 종료
            em.close();

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
