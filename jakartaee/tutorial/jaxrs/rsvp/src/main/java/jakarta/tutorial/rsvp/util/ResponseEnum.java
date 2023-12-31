package jakarta.tutorial.rsvp.util;

public enum ResponseEnum {
    ATTENDING("Attending"), NOT_ATTENDING("Not attending"), MAYBE_ATTENDING("Maybe"), NOT_RESPONDED(
            "No response yet");

    private final String label;

    private ResponseEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
