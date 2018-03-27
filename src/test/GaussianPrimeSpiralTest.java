import GaussianPrimeSpirals.GaussianPrimeSpiral;
import database.GaussianPrimeDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GaussianPrimeSpiralTest {

    @Test
    public void spiralTest(){
        GaussianPrimeDatabase gaussianPrimeDatabase = GaussianPrimeDatabase.getInstance();
        gaussianPrimeDatabase.startup();
        gaussianPrimeDatabase.dropTable();
        gaussianPrimeDatabase.createTable();
        gaussianPrimeDatabase.init();
        GaussianPrimeSpiral gaussianPrimeSpiral = new GaussianPrimeSpiral();
        List<int[]> spiral= gaussianPrimeSpiral.getPrimes();
        Assertions.assertNotNull(spiral);
        gaussianPrimeDatabase.shutdown();
    }
}
