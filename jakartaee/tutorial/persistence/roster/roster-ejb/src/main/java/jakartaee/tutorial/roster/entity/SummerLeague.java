package jakartaee.tutorial.roster.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.tutorial.roster.util.IncorrectSportException;

@Entity public class SummerLeague extends League implements Serializable {
    private static final long serialVersionUID = 4846138039113922695L;

    /**
     * Creates a new instance of SummerLeague
     */
    public SummerLeague() {
    }

    public SummerLeague(String id, String name, String sport) throws IncorrectSportException {
        this.id = id;
        this.name = name;
        if (sport.equalsIgnoreCase("swimming") || sport.equalsIgnoreCase("soccer") ||
            sport.equalsIgnoreCase("basketball") || sport.equalsIgnoreCase("baseball")) {
            this.sport = sport;
        } else {
            throw new IncorrectSportException("Sport is not a summer sport.");
        }
    }
}
