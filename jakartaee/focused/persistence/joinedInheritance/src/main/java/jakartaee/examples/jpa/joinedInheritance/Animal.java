package jakartaee.examples.jpa.joinedInheritance;

import jakarta.persistence.*;



@Entity @Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DISC", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Animal {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
