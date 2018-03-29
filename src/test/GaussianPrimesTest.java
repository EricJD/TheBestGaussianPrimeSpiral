import GaussianPrimeSpirals.GaussianPrimes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GaussianPrimesTest {

    @Test
    void isRightPrime(){
        GaussianPrimes gaussianPrimes = new GaussianPrimes();
        boolean value = gaussianPrimes.isPrime(5);
        Assertions.assertEquals(true,value);
        value = gaussianPrimes.isPrime(43);
        Assertions.assertEquals(true,value);
        value = gaussianPrimes.isPrime(97);
        Assertions.assertEquals(true,value);
    }

    @Test
    void isFalsePrime(){
        GaussianPrimes gaussianPrimes = new GaussianPrimes();
        boolean value = gaussianPrimes.isPrime(6);
        Assertions.assertEquals(false,value);
        value = gaussianPrimes.isPrime(45);
        Assertions.assertEquals(false,value);
        value = gaussianPrimes.isPrime(93);
        Assertions.assertEquals(false,value);
    }

    @Test
    void isRightGaussPrime(){
        GaussianPrimes gaussianPrimes = new GaussianPrimes();
        int[] p = new int[]{11,20};
        boolean value = gaussianPrimes.isGaussPrime(p);
        Assertions.assertEquals(true,value);
        p = new int[]{53,68};
        value = gaussianPrimes.isGaussPrime(p);
        Assertions.assertEquals(true,value);
        p = new int[]{84,101};
        value = gaussianPrimes.isGaussPrime(p);
        Assertions.assertEquals(true,value);
    }

    @Test
    void isFalseGaussPrime(){
        GaussianPrimes gaussianPrimes = new GaussianPrimes();
        int[] p = new int[]{14,17};
        boolean value = gaussianPrimes.isGaussPrime(p);
        Assertions.assertEquals(false,value);
        p = new int[]{55,63};
        value = gaussianPrimes.isGaussPrime(p);
        Assertions.assertEquals(false,value);
        p = new int[]{83,101};
        value = gaussianPrimes.isGaussPrime(p);
        Assertions.assertEquals(false,value);
    }
}
