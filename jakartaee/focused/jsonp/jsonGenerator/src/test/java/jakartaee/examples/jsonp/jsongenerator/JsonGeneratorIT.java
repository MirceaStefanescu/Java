package jakartaee.examples.jsonp.jsongenerator;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import jakartaee.examples.utils.ITBase;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;

import static org.junit.Assert.assertTrue;

/**
 * The JUnit tests for the JsonGeneratorBean class.
 */
@RunWith(Arquillian.class) @RunAsClient public class JsonGeneratorIT extends ITBase {

    /**
     * Stores the base URL.
     */
    @ArquillianResource private URL baseUrl;

    /**
     * Test the JsonGeneratorBean.
     *
     * @throws Exception when a serious error occurs.
     */
    @RunAsClient @Test public void testJsonGeneratorBean() throws Exception {
        HtmlPage page = webClient.getPage(baseUrl + "index.xhtml");
        page = page.getElementByName("form:submitButton").click();
        assertTrue(page.asXml().contains("{\"property\":\"propertyValue\"}"));
    }
}
