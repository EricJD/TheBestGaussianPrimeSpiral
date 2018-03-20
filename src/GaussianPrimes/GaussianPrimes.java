package GaussianPrimes;

import java.util.ArrayList;
import java.util.List;

public class GaussianPrimes {

    public List<int[]> generatePrimes(){

        GaussPrimeSieve primeSieve = new GaussPrimeSieve();

        List<int[]> gaussianPrimes = new ArrayList<>();
        int[] n;

        for (int real=-120; real<=120; real++){
            for (int ima=-120;ima<=120;ima++){
                n = new int[]{real,ima};
                if (primeSieve.isGaussPrime(n)){
                    gaussianPrimes.add(n);
                }
            }
        }

        return gaussianPrimes;
    }

}
