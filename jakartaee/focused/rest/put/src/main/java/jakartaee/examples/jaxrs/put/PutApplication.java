package jakartaee.examples.jaxrs.put;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

/**
 * The application for the JAX-RS @PUT example.
 */
@ApplicationPath("rest") public class PutApplication extends Application {

    /**
     * Get the classes.
     *
     * @return the classes.
     */
    @Override public Set<Class<?>> getClasses() {
        HashSet classes = new HashSet();
        classes.add(PutResource.class);
        return classes;
    }
}
