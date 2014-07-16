package Data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by wateryheart on 29/Mar/2014.
 * @author MAR Chun Sum Connie (20057384)
 */
public class UserTest {
    User u1 = new User("cs","csm");
    User u2 = new User("","");

    @Before
    public void setUp() throws Exception {
        UserStore userStore = new UserStore();
        userStore.importFile("/test/db/userPasswordFile.txt");
    }

    @Test
    public void testSetName() throws Exception {
        u2.setName("3021");
        assertEquals(u2.getName(),"3021");
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(u1.getName(), "cs");
    }

    @Test
    public void testSetPassword() throws Exception {
        u2.setPassword("3021a1");
        assertEquals(u2.getPassword(),"3021a1");
    }

    @Test
    public void testGetPassword() throws Exception {
        assertEquals(u1.getPassword(), "csm");
    }
}
