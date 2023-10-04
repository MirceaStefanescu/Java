package jakartaee.examples.utils;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static java.lang.System.getProperty;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;


@RunWith(Arquillian.class) @RunAsClient public abstract class ITBase {

    @ArquillianResource protected URL webUrl;
    protected WebClient webClient;

    @Deployment(testable = false) public static WebArchive createDeployment() {
        return create(ZipImporter.class, getProperty("finalName") + ".war").importFrom(
                new File("target/" + getProperty("finalName") + ".war")).as(WebArchive.class);
    }

    @Before public void setUp() {
        webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.setJavaScriptTimeout(120000);
        webClient.setIncorrectnessListener(new IgnoringIncorrectnessListener());
        DebugOptions.setDebugOptions(webClient);
    }

    protected HtmlPage getPage(String viewId) throws IOException {
        return webClient.getPage(webUrl + viewId);
    }

    @After public void tearDown() {
        webClient.close();
    }

}
