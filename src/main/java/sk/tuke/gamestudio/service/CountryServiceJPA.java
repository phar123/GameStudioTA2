package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Country;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class CountryServiceJPA implements CountryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Country> getCountries() {
        return entityManager
                .createQuery("select c from Country c order by c.country desc")
                .getResultList();
    }

    @Override
    public void addCountry(Country country) {
        entityManager.persist(country);
    }


    public void reset() {
        entityManager.createNativeQuery("DELETE FROM country").executeUpdate();
    }
}
