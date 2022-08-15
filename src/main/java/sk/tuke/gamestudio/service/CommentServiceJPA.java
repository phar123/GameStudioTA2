package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Score;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CommentServiceJPA implements CommentService{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addComment(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    public List<Comment> getComments(String game) {
        return entityManager
                .createQuery("select co from Comment co where co.game = :myGame order by co.commented_on desc")
                .setParameter("myGame", game)
                .setMaxResults(5)
                .getResultList();
    }

    @Override
    public void reset() {
        entityManager.createNativeQuery("DELETE FROM comment").executeUpdate();

    }
   /* @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addComment(Score score) {
        entityManager.persist(score);

    }

    @Override
    public List<Score> getBestScores(String game) {
        return entityManager
                .createQuery("select sc from Score sc where sc.game = :myGame order by sc.points desc")
                .setParameter("myGame", game)
                .setMaxResults(5)
                .getResultList();

    }

    @Override
    public void reset() {
        entityManager.createNativeQuery("DELETE FROM score").executeUpdate();

    }*/



}
