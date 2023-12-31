package jakartaee.examples.jsonb.jsonbdateformat;

import jakarta.json.bind.annotation.JsonbDateFormat;

import java.util.Date;

/**
 * The model object for the JSON-B @JsonbDateFormat example.
 */
public class JsonbDateFormatExample {

    /**
     * Stores the date.
     */
    @JsonbDateFormat(value = "MM/dd/yyyy") private Date date;

    /**
     * Get the date.
     *
     * @return the date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the date.
     *
     * @param date the date.
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
