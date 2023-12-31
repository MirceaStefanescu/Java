package jakartaee.examples.servlet.httpsessionattributelistener;

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
 * The JUnit tests for the HttpSessionAttributeListener(Bean|Example) classes.
 */
@RunWith(Arquillian.class) @RunAsClient public class HttpSessionAttributeListenerIT extends ITBase {

    /**
     * Stores the base URL.
     */
    @ArquillianResource private URL baseUrl;

    /**
     * Test the HttpSessionAttributeListener.
     *
     * @throws Exception when a serious error occurs.
     */
    @RunAsClient @Test public void testHttpSessionAttributeListener() throws Exception {
        HtmlPage page = webClient.getPage(baseUrl);
        page = page.getElementById("form:submitButton").click();
        page = page.getElementById("form:submitButton").click();
        assertTrue(page.asXml().contains("Attribute added name: attr1"));
        assertTrue(page.asXml().contains("Attribute added value: attr1-value"));
        assertTrue(page.asXml().contains("Attribute removed name: attr1"));
        assertTrue(page.asXml().contains("Attribute replaced name: attr2"));
        assertTrue(page.asXml().contains("Attribute replaced value: attr2-value"));
    }
}
