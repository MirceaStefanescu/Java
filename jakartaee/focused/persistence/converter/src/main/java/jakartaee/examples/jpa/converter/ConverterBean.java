package jakartaee.examples.jpa.converter;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

/**
 * The bean used to demonstrate usage of @Converter.
 */
@Named @RequestScoped public class ConverterBean implements Serializable {

    /**
     * Stores the EJB.
     */
    @Inject private ConverterEjb ejb;

    /**
     * Get the output.
     *
     * @return the output.
     */
    public ConverterExample getOutput() {
        return ejb.getConverterExample();
    }
}
