package jakartaee.examples.jaxrs.getclient;


import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakartaee.examples.utils.ITBase;

/**
 * The JUnit tests for the GET Client example.
 */
@RunWith(Arquillian.class) @RunAsClient public class GetClientIT extends ITBase {

    /**
     * Stores the base URL.
     */
    @ArquillianResource private URL baseUrl;

    /**
     * Stores the JAX-RS client.
     */
    private Client client;

    /**
     * Setup before testing.
     */
    @Before public void before() {
        client = ClientBuilder.newClient();
    }

    /**
     * Tear down after testing.
     */
    @After public void after() {
        client.close();
    }

    /**
     * Test issuing a GET request using the Client.
     *
     * @throws Exception when a serious error occurs.
     */
    @RunAsClient @Test public void testGet() throws Exception {
        WebTarget target = client.target(baseUrl.toURI());
        String text = target.path("rest/getClient").request().get(String.class);
        assertEquals("And we used @GET", text);
    }
}
