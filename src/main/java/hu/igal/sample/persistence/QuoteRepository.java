package hu.igal.sample.persistence;

import hu.igal.sample.persistence.entity.Quote;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class QuoteRepository {

//    @Inject
//    private EntityManagerFactory entityManagerFactory;


    @PersistenceContext
    private EntityManager entityManager;

//    @PostConstruct
//    public void init(){
//        entityManager = entityManagerFactory.createEntityManager();
//    }

    @Transactional
    public void addQuote(Quote quote){
        entityManager.persist(quote);

    }

    public List<Quote> findAll(){
        List<Quote> list = entityManager.createQuery("SELECT q FROM Quote q").getResultList();
        return list;
    }

}
