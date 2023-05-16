package jpamvcexam.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;

import jpamvcexam.model.vo.Student;

public class StudentDAO {
    private EntityManagerFactory emf;
    private EntityManager em;


    public StudentDAO() {
        emf = Persistence.createEntityManagerFactory("emptest");
    }


    public boolean insertStudent(Student entity) {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
    }

    public List<Student> getAllStudent() {
        em = emf.createEntityManager();
        List<Student> selectTFromStudentT = em.createQuery("select t from student t").getResultList();
        em.close();
        return selectTFromStudentT;
    }


    public Student getScore(String name) {
        em = emf.createEntityManager();
        Student student = em.find(Student.class, name);
        em.close();
        return student;
    }

    public boolean updateStudent(Student entity) {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Student student = em.find(Student.class, entity.getName());
            student.setScore(entity.getScore());
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
    }

    public boolean deleteStudent(String name) {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.find(Student.class, name));
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
    }

    public void close() {
        if (emf != null)
            emf.close();
    }




}
