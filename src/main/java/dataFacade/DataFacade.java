/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataFacade;

import entities.Base;
import entities.CoinPair;
import entities.PriceMove;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author tha
 */
public class DataFacade {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private EntityManager getManager(){
        return emf.createEntityManager();
    }
    
    public void createPriceMove(PriceMove pm){
        EntityManager em = getManager();
        em.getTransaction().begin();
        em.persist(pm);
        em.getTransaction().commit();
        em.close();
    }
    public void createBase(Base base){
        EntityManager em = getManager();
        em.getTransaction().begin();
        em.persist(base);
        em.getTransaction().commit();
        em.close();
        
    }
    public void updatePriceMove(PriceMove pm){
        EntityManager em = getManager();
        em.getTransaction().begin();
        em.merge(pm);
        em.getTransaction().commit();
        em.close();
    }
    public void updateBase(Base base){
        EntityManager em = getManager();
        em.getTransaction().begin();
        em.merge(base);
        em.getTransaction().commit();
        em.close();
    
    }
    public List<Base> getBasesFromCoinPair(CoinPair cp){
        EntityManager em = getManager();
        TypedQuery<Base> q = em.createQuery("SELECT c.bases From CoinPair c WHERE c.name: name", Base.class);
        q.setParameter("name", cp.getName());
        List<Base> bases = q.getResultList();
        em.close();
        return bases;
    }
    public List<PriceMove> getAllPriceMoves(){
        EntityManager em = getManager();
        TypedQuery<PriceMove> q = em.createQuery("SELECT p FROM PriceMove p", PriceMove.class);
        List<PriceMove> moves = q.getResultList();
        em.close();
        return moves;
    }
}
