package jakarta.tutorial.producermethods;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Managed bean that calls a Coder implementation to perform a transformation on an input string
 */
@Named @RequestScoped public class CoderBean {

    private final static int TEST = 1;
    private final static int SHIFT = 2;
    @Inject @Chosen @RequestScoped Coder coder;
    private String inputString;
    private String codedString;
    @Max(26) @Min(0) @NotNull private int transVal;
    private int coderType = SHIFT; // default value

    /**
     * Producer method that chooses between two beans based on the coderType value.
     *
     * @return Chosen coder implementation
     */
    @Produces @Chosen @RequestScoped public Coder getCoder() {

        switch (coderType) {
            case TEST:
                return new TestCoderImpl();
            case SHIFT:
                return new CoderImpl();
            default:
                return null;
        }
    }

    public void encodeString() {
        setCodedString(coder.codeString(inputString, transVal));
    }

    public void reset() {
        setInputString("");
        setTransVal(0);
    }

    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inString) {
        inputString = inString;
    }

    public String getCodedString() {
        return codedString;
    }

    public void setCodedString(String str) {
        codedString = str;
    }

    public int getTransVal() {
        return transVal;
    }

    public void setTransVal(int tval) {
        transVal = tval;
    }

    public int getCoderType() {
        return coderType;
    }

    public void setCoderType(int coderType) {
        this.coderType = coderType;
    }
}
