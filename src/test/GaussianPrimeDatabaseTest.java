import database.Configuration;
import database.GaussianPrimeDatabase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GaussianPrimeDatabaseTest {
    private GaussianPrimeDatabase database = GaussianPrimeDatabase.getInstance();

    @Test
    void isConnectionOpen() {
        try {
            database.startup();
            assertFalse(database.getConnection().isClosed());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void isConnectionClosed() {
        try {
            database.startup();
            database.shutdown();
            assertTrue(database.getConnection().isClosed());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void isGaussianPrimeDatabaseEmpty(){
        database.startup();
        database.dropTable();
        database.createTable();
        database.importCSVData(Configuration.instance.gaussianPrimesArchive);
        List<String> output = database.getGaussianPrimes();
        database.shutdown();
        System.out.println(output.toString());
        assertNotNull(output);

    }

}
