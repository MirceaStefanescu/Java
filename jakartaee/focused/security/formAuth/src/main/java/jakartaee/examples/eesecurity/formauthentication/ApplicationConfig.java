package jakartaee.examples.eesecurity.formauthentication;

import java.io.Serializable;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.inject.Named;
import jakarta.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

@ApplicationScoped @FormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(errorPage = "/login_error.xhtml",
                                           loginPage = "/login.xhtml"))
@DatabaseIdentityStoreDefinition(dataSourceLookup = "java:comp/DefaultDataSource",
                                 callerQuery = "select password from form_auth_user where " +
                                               "username = ?",
                                 groupsQuery = "select name from form_auth_group where username =" +
                                               " ?",
                                 hashAlgorithm = Pbkdf2PasswordHash.class,
                                 hashAlgorithmParameters = {"Pbkdf2PasswordHash.Iterations=3072",
                                         "Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512",
                                         "Pbkdf2PasswordHash.SaltSizeBytes=64"})
@FacesConfig @Named public class ApplicationConfig
        implements Serializable {}
