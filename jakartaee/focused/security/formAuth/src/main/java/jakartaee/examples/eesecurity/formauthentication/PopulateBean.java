package jakartaee.examples.eesecurity.formauthentication;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 * The bean to populate the database with a user.
 */
@Stateless @Named("populateBean") public class PopulateBean {

    /**
     * Persistence context.
     */
    @PersistenceContext(unitName = "default") private EntityManager em;

    /**
     * Stores the password hash.
     */
    @Inject private Pbkdf2PasswordHash passwordHash;

    /**
     * Populate the tables.
     *
     * @return ""
     */
    public String populate() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iterations", "3072");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
        passwordHash.initialize(parameters);
        User user = em.find(User.class, new BigInteger("1"));
        if (user == null) {
            user = new User();
            user.setId(BigInteger.ONE);
            user.setUsername("admin");
            user.setPassword(passwordHash.generate("admin".toCharArray()));
            em.persist(user);
        }
        Group group = em.find(Group.class, new BigInteger("1"));
        if (group == null) {
            group = new Group();
            group.setId(BigInteger.ONE);
            group.setName("user");
            group.setUsername("admin");
            em.persist(group);
        }
        return "";
    }
}
