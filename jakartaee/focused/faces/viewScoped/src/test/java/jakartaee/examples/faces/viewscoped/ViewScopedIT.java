package jakartaee.examples.faces.viewscoped;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.net.URL;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import jakartaee.examples.utils.ITBase;

/**
 * The JUnit tests for the ViewScoped bean example.
 */
@RunWith(Arquillian.class) @RunAsClient public class ViewScopedIT extends ITBase {

    /**
     * Stores the base URL.
     */
    @ArquillianResource private URL baseUrl;

    /**
     * Test the view scoped bean.
     *
     * @throws Exception when a serious error occurs.
     */
    @RunAsClient @Test public void testViewScopedBean() throws Exception {
        HtmlPage page = webClient.getPage(baseUrl);
        String content1 = page.asXml();
        content1 = content1.substring(content1.indexOf("'") + 1);
        content1 = content1.substring(0, content1.indexOf("'"));

        page = page.getElementById("form:sameview").click();
        String content2 = page.asXml();
        content2 = content2.substring(content2.indexOf("'") + 1);
        content2 = content2.substring(0, content2.indexOf("'"));

        assertEquals(content1, content2);

        page = page.getElementById("form:otherview").click();
        String content3 = page.asXml();
        content3 = content3.substring(content3.indexOf("'") + 1);
        content3 = content3.substring(0, content3.indexOf("'"));

        assertNotEquals(content2, content3);
    }
}
