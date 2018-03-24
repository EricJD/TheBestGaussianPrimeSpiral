import database.Configuration;
import database.GaussianPrimeDatabase;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GaussianPrimeDatabaseTest {
    GaussianPrimeDatabase database;

    @Before
    public void init() {
        database = GaussianPrimeDatabase.getInstance();
    }

    @Test
    public void isConnectionOpen() {
        try {
            database.startup();
            assertFalse(database.getConnection().isClosed());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void isConnectionClosed() {
        try {
            database.startup();
            database.shutdown();
            assertTrue(database.getConnection().isClosed());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void isGaussianPrimeDatabaseEmpty(){
        List<String> output = new ArrayList<>();
        database.startup();
        database.dropTable();
        database.createTable();
        database.importCSVData(Configuration.instance.gaussianPrimesArchive);
        output = database.getGaussianPrimes();
        database.shutdown();
        System.out.println(output.toString());
        assertNotNull(output);

    }

}
