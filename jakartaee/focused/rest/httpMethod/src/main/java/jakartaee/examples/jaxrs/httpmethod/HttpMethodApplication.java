package jakartaee.examples.jaxrs.httpmethod;

import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * The application for the @HttpMethod example.
 */
@ApplicationPath("rest") public class HttpMethodApplication extends Application {

    /**
     * Get the classes.
     *
     * @return the classes.
     */
    @Override public Set<Class<?>> getClasses() {
        HashSet classes = new HashSet();
        classes.add(HttpMethodResource.class);
        return classes;
    }
}
