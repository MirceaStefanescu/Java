package com.forest.ejb;

import com.forest.entity.Administrator;
import com.forest.entity.Groups;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Stateless public class AdministratorBean extends AbstractFacade<Administrator> {
    @PersistenceContext(unitName = "forestPU") private EntityManager em;

    public AdministratorBean() {
        super(Administrator.class);
    }

    @Override protected EntityManager getEntityManager() {
        return em;
    }

    @Override public void create(Administrator admin) {
        Groups adminGroup = (Groups) em.createNamedQuery("Groups.findByName").setParameter("name",
                                                                                           "Administrator")
                                       .getSingleResult();
        admin.getGroupsList().add(adminGroup);
        adminGroup.getPersonList().add(admin);
        em.persist(admin);
        em.merge(adminGroup);
    }

    @Override public void remove(Administrator admin) {
        Groups adminGroup = (Groups) em.createNamedQuery("Groups.findByName").setParameter("name",
                                                                                           "Administrator")
                                       .getSingleResult();
        adminGroup.getPersonList().remove(admin);
        em.remove(em.merge(admin));
        em.merge(adminGroup);
    }

}
