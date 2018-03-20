package GaussianPrimeSpirals;

import java.util.ArrayList;
import java.util.List;

public class GaussianPrimes {

    public List<int[]> generatePrimes(){

        List<int[]> gaussianPrimes = new ArrayList<>();
        int[] n;

        for (int real=-120; real<=120; real++){
            for (int ima=-120;ima<=120;ima++){
                n = new int[]{real,ima};
                if (isGaussPrime(n)){
                    gaussianPrimes.add(n);
                }
            }
        }
        return gaussianPrimes;
    }

    private boolean isPrime(int n) {
        if(n==2) return true;
        //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }

    private boolean isGaussPrime(int[] n){
        if (isPrime((n[0]*n[0])+(n[1]*n[1]))){
            return true;
        }else if (n[0]==0&&isPrime(n[1])&&n[1]%4==3){
            return true;
        }else if (n[1]==0&&isPrime(n[0])&&n[0]%4==3){
            return true;
        }
        return false;
    }

}
