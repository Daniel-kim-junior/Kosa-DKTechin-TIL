package jpamvcexam.mainview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import jpamvcexam.model.entity.Emp;

public class EmpDeptLab {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("emptest");
        EntityManager em = factory.createEntityManager();
        Random random = new Random();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = random.nextBoolean();
        if(flag) {
            System.out.println("사원명을 입력 하세요");
            String name = br.readLine();
            try {
                Query query = em.createQuery("select t from EMP t join t.dept d where t.eName = :name")
                                                    .setParameter("name", name);
                Emp singleResult = (Emp) query.getSingleResult();
                System.out.println(singleResult.geteName() + "님의 부서명은 " + singleResult.getDept().getdName() + " 입니다.");

            } catch (NoResultException e) {
                System.out.println("부서를 찾을 수가 없네요 ㅜㅜ..");
            }
        } else {
            System.out.println("부서명을 입력 하세요");
            String name = br.readLine();
            try {
                TypedQuery<Emp> list = em.createQuery("select t from EMP t join t.dept d where d.dName = :name", Emp.class)
                                                   .setParameter("name", name);
                List<Emp> l = list.getResultList();
                System.out.println(l.get(0).getDept().getdName() + " 부서에 있는 직원들입니다.");
                for(Emp o : l) {
                    System.out.println(o.geteName());
                }

            } catch (NoResultException e) {
                System.out.println("부서를 찾을 수가 없네요 ㅜㅜ..");
            }

        }
        em.close();
        factory.close();
    }
}
