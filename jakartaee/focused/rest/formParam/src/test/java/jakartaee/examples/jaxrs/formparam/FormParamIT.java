package jakartaee.examples.jaxrs.formparam;

import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import jakartaee.examples.utils.ITBase;

/**
 * The JUnit tests for the @FormParam example.
 */
@RunWith(Arquillian.class) @RunAsClient public class FormParamIT extends ITBase {

    /**
     * Stores the base URL.
     */
    @ArquillianResource private URL baseUrl;

    /**
     * Test @FormParam.
     *
     * @throws Exception when a serious error occurs.
     */
    @RunAsClient @Test public void testFormParam() throws Exception {
        HtmlPage page = webClient.getPage(baseUrl + "index.html");
        TextPage textPage = page.getElementByName("submitButton").click();
        assertTrue(textPage.getContent().contains("1234"));
    }
}
