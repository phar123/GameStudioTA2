package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class StudentServiceJPA{
    @PersistenceContext
    public EntityManager entityManager;

    public void addStudent(Student student) {entityManager.persist(student);}


    public List<Student> getAllStudents() {
        return entityManager
                .createQuery("select s from Student s")
                .getResultList();

    }
}
