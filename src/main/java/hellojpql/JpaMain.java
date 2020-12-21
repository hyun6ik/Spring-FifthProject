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
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName("teamA");
            member.setAge(26);
            member.setTeam(team);

                em.persist(member);


            em.flush();
            em.clear();
            String query = "select m from Member m left join Team t on m.name = t.name";
            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();
            System.out.println("result.size() = " + result.size());
            for (Member member1 : result) {
                System.out.println("member1 = " + member1);
            }


//            List<MemberDTO> result = em.createQuery("select new hellojpql.domain.MemberDTO(m.name, m.age) from Member m", MemberDTO.class)
//                    .getResultList();
//            MemberDTO memberDTO = result.get(0);
//            System.out.println("memberDTO = " + memberDTO.getName());
//            System.out.println("memberDTO = " + memberDTO.getAge());


//            List<Address> orderResult = em.createQuery("select o.address from Order o", Address.class)
//                    .getResultList();
//
//            List<Team> teamresult = em.createQuery("select m.team From Member m", Team.class)
//                    .getResultList();
//
//            List<Member> result2 = em.createQuery("select m.team from Member m", Member.class)
//                    .getResultList();
//
//            Member findMember = result2.get(0);
//            findMember.setAge(20);


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

