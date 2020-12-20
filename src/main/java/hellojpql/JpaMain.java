package hellojpql;

import hellojpql.domain.Member;

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
            member.setName("Yoon");
            member.setAge(26);
            em.persist(member);

           Member result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", "Yoon")
                .getSingleResult();
           

//            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//            List<Member> resultList = query.getResultList();
//            for (Member member1 : resultList) {
//                System.out.println("member1 = " + member1);
//            }
//
//            TypedQuery<Member> query2 = em.createQuery("select m from Member m where m.age = 26", Member.class);
//            Member singleResult = query2.getSingleResult();
//            System.out.println("singleResult = " + singleResult);
//
//
//
//            TypedQuery<String> query4 = em.createQuery("select m.name from Member m ", String.class);
//            Query query5 = em.createQuery("select m.name, m.age from Member m ", Member.class);


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

