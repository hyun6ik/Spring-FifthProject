package hellojpql;

import hellojpql.domain.Address;
import hellojpql.domain.Member;
import hellojpql.domain.MemberDTO;
import hellojpql.domain.Team;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        tx.begin();

        try {
            Member member = new Member();
            member.setName("member1");
            member.setAge(27);
            em.persist(member);

            Member result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                    .setParameter("name", "member1")
                    .getSingleResult();
            System.out.println("result = " + result);


//            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//            List<Member> resultList = query.getResultList();
//            for (Member member1 : resultList) {
//                System.out.println("member1 = " + member1);
//            }
//
//            TypedQuery<String> query2 = em.createQuery("select m.name from Member m", String.class);
//            String singleResult = query2.getSingleResult();
//            System.out.println("singleResult = " + singleResult);
//
//
//            Query query3 = em.createQuery("select m.name, m.age from Member m");


            tx.commit();
        } catch (Exception e){
            tx.rollback();
            System.out.println("e = " + e);
        } finally {
            em.close();
        }

        emf.close();

    }

}

