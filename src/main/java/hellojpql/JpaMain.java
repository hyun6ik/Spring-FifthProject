package hellojpql;

import hellojpql.domain.*;

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
                member.setName("member1");
                member.setAge(10);
                member.setType(MemberType.ADMIN);

                em.persist(member);

            em.flush();
            em.clear();

            String query = "select size(t.members) From team t";



//            String query = "select locate('de','abcdefg') From Member m";
//            List<Integer> resultList = em.createQuery(query, Integer.class)
//                    .getResultList();
//            for (Integer integer : resultList) {
//                System.out.println("integer = " + integer);
//            }
//            String query = "select substring(m.name,2,3) From Member m";
//            List<String> resultList = em.createQuery(query, String.class)
//                    .getResultList();
//            for (String s : resultList) {
//                System.out.println("s = " + s);
//            }

//            String query = "select concat('a', 'b') From Member m";
//            List<String> resultList = em.createQuery(query, String.class)
//                    .getResultList();
//            for (String s : resultList) {
//                System.out.println("s = " + s);
//            }


//            String query = "select nullif(m.name, '관리자') as name from Member m";
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

//            String query = "select coalesce(m.name, '이름 없는 회원') as name from Member m";
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

//            String query =
//                    "select " +
//                            "case when m.age <=10 then '학생요금'" +
//                            "     when m.age >=60 then '경로요금'" +
//                            "     else '일반요금'" +
//                    "end from Member m";
//            List<String> resultList = em.createQuery(query, String.class)
//                    .getResultList();
//            for (String s : resultList) {
//                System.out.println("s = " + s);
//            }


//            String query = "select m.name, 'HELLO', TRUE From Member m where m.type = :userType";
//            List<Object[]> resultList = em.createQuery(query)
//                    .setParameter("userType", MemberType.ADMIN)
//                    .getResultList();
//            for (Object[] objects : resultList) {
//                System.out.println("objects = " + objects[0]);
//                System.out.println("objects = " + objects[1]);
//                System.out.println("objects = " + objects[2]);
//            }


//            String query = "select m from Member m left join m.team t on t.name = 'teamA'";
//            List<Member> resultList = em.createQuery(query, Member.class)
//                    .getResultList();
//            System.out.println("resultList = " + resultList);
//            String query = "select m from Member m, Team t where m.name = t.name";
//            List<Member> resultList = em.createQuery(query, Member.class)
//                    .getResultList();
//            System.out.println("resultList = " + resultList);

//            List<Member> resultList = em.createQuery("select m from Member m join m.team t where t.name = :teamName", Member.class)
//                    .setParameter("teamName", "teamA")
//                    .getResultList();
//            System.out.println("resultList = " + resultList);


//
//            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(0)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            System.out.println("result.size() = " + result.size());
//            for (Member member1 : result) {
//                System.out.println("member1 = " + member1);
//
//            }


//            List<MemberDTO> resultList = em.createQuery("select new hellojpql.domain.MemberDTO(m.name, m.age) from Member m", MemberDTO.class)
//                    .getResultList();
//            MemberDTO memberDTO = resultList.get(0);
//            System.out.println("memberDTO.getName() = " + memberDTO.getName());
//            System.out.println("memberDTO.getName() = " + memberDTO.getAge());


//            List<Object[]> resultList = em.createQuery("select m.name, m.age from Member m")
//                    .getResultList();
//            Object[] objects = resultList.get(0);
//            System.out.println("objects[0] = " + objects[0]);
//            System.out.println("objects[0] = " + objects[1]);



//            List resultList = em.createQuery("select distinct m.name, m.age from Member m ")
//                    .getResultList();
//            Object o = resultList.get(0);
//            Object[] result = (Object[]) o;
//            System.out.println("result = " + result[0]);
//            System.out.println("result = " + result[1]);


//            List<Address> resultList = em.createQuery("select o.address from Order o", Address.class)
//                    .getResultList();



//            List<Member> result = em.createQuery("select m From Member m", Member.class)
//                    .getResultList();
//
//            Member findMember = result.get(0);
//            findMember.setAge(20);



//            Member result = em.createQuery("select m from Member m where m.name = :name", Member.class)
//                    .setParameter("name", "member1")
//                    .getSingleResult();
//            System.out.println("result = " + result);
//

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

