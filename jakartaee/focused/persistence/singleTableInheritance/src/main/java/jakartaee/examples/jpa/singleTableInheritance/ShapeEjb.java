package jakartaee.examples.jpa.singleTableInheritance;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * A simple EJB that persist shapes in database
 */
@Stateless public class ShapeEjb {

    /**
     * Stores the persistence context.
     */
    @PersistenceContext(unitName = "inheritancePU") private EntityManager em;

    /**
     * Persist a circle and a rectangle in database
     */
    public void persist() {

        Circle circle = new Circle(10.0F);
        circle.setName("A Circle");
        Rectangle rectangle = new Rectangle(20, 30);
        rectangle.setName("A Rectangle");

        em.persist(circle);
        em.persist(rectangle);

    }

    public List<Circle> findAllCircles() {
        return em.createQuery("FROM Circle c").getResultList();
    }

    public List<Rectangle> findAllRectangles() {
        return em.createQuery("FROM Rectangle r").getResultList();
    }

}
