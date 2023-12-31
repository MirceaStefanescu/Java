package jakartaee.examples.jpa.joinedInheritance;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

/**
 * A simple EJB that persist animal data in database
 */
@Stateless public class AnimalEjb {

    /**
     * Stores the persistence context.
     */
    @PersistenceContext(unitName = "inheritancePU") private EntityManager em;

    /**
     * Persist information of a cat and a dog in database
     */
    public void persist() {

        Cat cat = new Cat(10);
        cat.setName("A Cat");
        Dog dog = new Dog(20);
        dog.setName("A Dog");

        em.persist(cat);
        em.persist(dog);

    }

    public List<Cat> findAllCats() {
        return em.createQuery("FROM Cat c").getResultList();
    }

    public List<Dog> findAllDogs() {
        return em.createQuery("FROM Dog r").getResultList();
    }

}
