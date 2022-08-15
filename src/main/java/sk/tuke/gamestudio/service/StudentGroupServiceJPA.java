package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.StudyGroup;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class StudentGroupServiceJPA {

   @PersistenceContext
    public EntityManager entityManager;

   public void addStudyGroup(StudyGroup studyGroup) {entityManager.persist(studyGroup);}

    public List<StudyGroup> getAllStudyGroups() {
       return entityManager
               .createQuery("select g from StudyGroup g")
               .getResultList();
    }
}
