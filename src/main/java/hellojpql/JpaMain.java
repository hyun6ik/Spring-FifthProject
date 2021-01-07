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
                Team teamA = new Team();
                teamA.setName("팀A");
                em.persist(teamA);

                Team teamB = new Team();
                teamB.setName("팀B");
                em.persist(teamB);

                Member member1 = new Member();
                member1.setName("회원1");
                member1.setTeam(teamA);
                em.persist(member1);

                Member member2 = new Member();
                member2.setName("회원2");
                member2.setTeam(teamA);
                em.persist(member2);

                Member member3 = new Member();
                member3.setName("회원3");
                member3.setTeam(teamB);
                em.persist(member3);

                Member member4 = new Member();
                member4.setName("회원4");
                em.persist(member4);


            em.flush();
            em.clear();

            List<Member> resultList = em.createNamedQuery("Member.findByName", Member.class)
                    .setParameter("name", "회원1")
                    .getResultList();
            for (Member member : resultList) {
                System.out.println("member = " + member);
            }

//            String query = "select m from Member m where m.team = :team";
//            List<Member> resultList = em.createQuery(query, Member.class)
//                    .setParameter("team", teamA)
//                    .getResultList();
//            for (Member member : resultList) {
//                System.out.println("member = " + member);
//            }


//            String query = "select m from Member m where m = :member";
//            Member findMember = em.createQuery(query, Member.class)
//                    .setParameter("member", member1)
//                    .getSingleResult();
//            System.out.println("findMember = " + findMember);


            //JPQL에서 엔티티를 직접 사용하면 SQL에서 해당 엔티티의 기본 키 값을 사용
            //select count(m.id) from Member m = select count(m) from Member m



//            String query = "select t From Team t";
//            List<Team> result = em.createQuery(query, Team.class)
//                    .setFirstResult(0)
//                    .setMaxResults(2)
//                    .getResultList();
//            System.out.println("result.size() = " + result.size());
//            for (Team team : result) {
//                System.out.println("team = " + team.getName() + "| members= " + team.getMembers().size());
//                for( Member member : team.getMembers()){
//                    System.out.println("member = " + member);
//
//                }
//            }


//            String query = "select t From Team t join t.members m";
//            List<Team> resultList = em.createQuery(query, Team.class)
//                    .getResultList();
//
//            System.out.println("resultList.size() = " + resultList.size());
//            for (Team team : resultList) {
//                System.out.println("team = " + team);
//            }

//            String query = "select distinct t from Team t join fetch t.members";
//            List<Team> result = em.createQuery(query, Team.class)
//                    .getResultList();
//
//            System.out.println("result.size() = " + result.size());
//            for (Team team : result) {
//                System.out.println("team = " + team.getName() + "| members= " + team.getMembers().size());
//                for( Member member : team.getMembers()){
//                    System.out.println("member = " + member);
//
//                }
//            }


//            String query = "select t from Team t join fetch t.members";
//            List<Team> result = em.createQuery(query, Team.class)
//                    .getResultList();

//            System.out.println("result.size() = " + result.size());
//            for (Team team : result) {
//                System.out.println("team = " + team.getName() + "| members= " + team.getMembers().size());
//                for( Member member : team.getMembers()){
//                    System.out.println("member = " + member);
//
//                }
//            }



//            String query = "select t from Team t join fetch t.members";
//            List<Team> result = em.createQuery(query, Team.class)
//                    .getResultList();
//            for (Team team : result) {
//                System.out.println("team = " + team.getName() + "| members= " + team.getMembers().size());
//                for( Member member : team.getMembers()){
//                    System.out.println("member = " + member);
//
//                }
//            }



//            String query = "select t from Team t join fetch t.members";
//            List<Team> result = em.createQuery(query, Team.class)
//                    .getResultList();
//            for (Team team : result) {
//                System.out.println("team = " + team.getName() + "|" + team.getMembers().size());
//                for( Member member : team.getMembers()){
//                    System.out.println("member = " + member);
//
//                }
//            }

//            String query = "select m from Member m join fetch m.team";
//            List<Member> members = em.createQuery(query, Member.class)
//                    .getResultList();
//            for (Member member : members) {
//                System.out.println("member = " + member.getName() + ", " + member.getTeam().getName());
//                // 실제 데이터 (처음부터 채워져 있음)
//                // 지연로딩 X
//                //if 회원 100명 --> N + 1
//            }


//            String query = "select m from Member m";
//            List<Member> members = em.createQuery(query, Member.class)
//                    .getResultList();
//            for (Member member : members) {
//                System.out.println("member = " + member.getName() + ", " + member.getTeam().getName());
                  // 프록시
//                //회원1, 팀A(SQL)
//                //회원2, 팀A(1차캐시)
//                //회원3, 팀B(SQL)
//                //회원4, 팀X(SQL)
//
//                //if 회원 100명 --> N + 1
//            }

            // 상태 필드 : String query = "select m.name from Member m";
            // 단일 값 연관 경로 : 묵시적 내부 조인 발생, 탐색 O (묵시적 내부 조인은 좋은 방향이 아니다)
            // String query = "select m.team from Member m";
            // 컬렉션 값 연관 경로 : 묵시적 내부 조인 발생, but 탐색X
            // String query = "select t.members From Team t";
            // From절에서 명시적 조인을 통해 별칭을 얻으면 별칭을 통해 탐색 가능
            // --> String query = "select m.name From Team t join t.members m";



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

