package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Player;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class PlayerServiceJPA implements PlayerService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Player> getPlayersByUserName(String uName) {
        return entityManager
                .createQuery("select pl from Player pl where pl.userName = :myName order by pl.userName desc")
                .setParameter("myName", uName)
                .setMaxResults(5)
                .getResultList();


    }

    @Override
    public void addPlayer(Player player) {
        entityManager.persist(player);

    }
}
