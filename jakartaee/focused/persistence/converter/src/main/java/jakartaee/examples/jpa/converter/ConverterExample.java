package jakartaee.examples.jpa.converter;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * The JPA class used to demonstrate usage of @Converter.
 */
@Entity @Table(name = "jpa_converter") public class ConverterExample implements Serializable {

    /**
     * Stores the column.
     */
    @Convert(converter = ConverterConverter.class) @Column(name = "your_column_name")
    private ConverterAttribute column;

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
    public ConverterAttribute getColumn() {
        return column;
    }

    /**
     * Set the column.
     *
     * @param column the column.
     */
    public void setColumn(ConverterAttribute column) {
        this.column = column;
    }
}
