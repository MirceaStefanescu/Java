package jakartaee.examples.servlet.explainingHttpServlet;

import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import jakartaee.examples.utils.ITBase;

/**
 * The JUnit tests for the Servlet API @ExplainingHttpServlet example.
 */
@RunWith(Arquillian.class) @RunAsClient public class ExplainingHttpServletIT extends ITBase {

    /**
     * Stores the base URL.
     */
    @ArquillianResource private URL baseUrl;

    /**
     * Test the @ExplainingHttpServlet GET call.
     *
     * @throws Exception when a serious error occurs.
     */
    @RunAsClient @Test public void testGetPage() throws Exception {
        HtmlPage page = webClient.getPage(baseUrl);
        // The id belongs to the submit button
        page = page.getElementById("form:getsubmit").click();
        assertTrue(page.asXml().contains("GET"));
    }

    /**
     * Test the @ExplainingHttpServlet POST call.
     *
     * @throws Exception when a serious error occurs.
     */
    @RunAsClient @Test public void testPostPage() throws Exception {
        HtmlPage page = webClient.getPage(baseUrl);
        // The id belongs to the submit button
        page = page.getElementById("form:postsubmit").click();
        assertTrue(page.asXml().contains("POST"));
    }
}
