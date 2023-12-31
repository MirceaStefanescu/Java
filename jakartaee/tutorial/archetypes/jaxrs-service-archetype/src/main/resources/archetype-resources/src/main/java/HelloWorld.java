import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;

/**
 * Root resource (exposed at "${artifactId}world" path)
 */
@Path("${artifactId}") public class HelloWorld {
    @Context private UriInfo context;

    /**
     * Creates a new instance of HelloWorld
     */
    public HelloWorld() {
    }

    /**
     * Retrieves representation of an instance of ${artifactId}World.HelloWorld
     *
     * @return an instance of java.lang.String
     */
    @GET @Produces("text/html") public String getHtml() {
        //TODO replace with your own HTML
        return "<html><head><title>Hello there</title></head><body><h1>Hello!</h1></body></html>";
    }

    /**
     * PUT method for updating or creating an instance of HelloWorld
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT @Consumes("text/html") public void putHtml(String content) {
    }
}
