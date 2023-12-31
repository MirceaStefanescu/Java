package jakartaee.examples.jpa.column;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

/**
 * The JPA class for the @Table example.
 */
@Entity @Table(name = "jpa_column") public class ColumnRow implements Serializable {

    /**
     * Stores the column.
     */
    @Column(name = "your_column_name") private String column;

    /**
     * Stores the id.
     */
    @Id private Long id;

    /**
     * Get the id.
     *
     * @return the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id.
     *
     * @param id the id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the column.
     *
     * @return the column.
     */
    public String getColumn() {
        return column;
    }

    /**
     * Set the column.
     *
     * @param column the column.
     */
    public void setColumn(String column) {
        this.column = column;
    }
}
