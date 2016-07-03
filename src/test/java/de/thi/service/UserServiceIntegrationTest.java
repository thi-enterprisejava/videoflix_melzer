package de.thi.service;

import de.thi.videoflix.domain.User;
import de.thi.videoflix.services.UserService;
import de.thi.videoflix.util.Resources;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class UserServiceIntegrationTest {

    @EJB
    UserService userService;

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(User.class)
                .addClass(UserService.class)
                .addClass(Resources.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("test-ds.xml")
                ;
        return webArchive;
    }

    @Test
    @Cleanup
    public void thatPasswordCanBeEncrypted() throws Exception {
        String password = "test1234";
        String encryption = "k36NX7tIvUlJU2zWW401xCa4DS+DDFwwjizexCKuIkQ=";

        String encryptedPassword = userService.encryptPassword(password);
        assertEquals(encryption, encryptedPassword);
    }

    @Test
    @Cleanup
    public void thatUserCanBeAdded() throws Exception {
        String username = "Test";
        String password = "1234";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(User.UserRole.USER);

        userService.addUser(user);

        User addedUser = userService.findUsername(username);
        assertNotNull(addedUser);
        String encryptedPassword = userService.encryptPassword(password);
        assertEquals(encryptedPassword, addedUser.getPassword());
        assertEquals(User.UserRole.USER, addedUser.getRole());
    }
}
