package GaussianPrimeSpirals;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GaussianPrimes {

    public void generatePrimes(){
        Configuration configuration = Configuration.instance;
        int max = configuration.max;

        List<int[]> gaussianPrimes = new ArrayList<>();
        int[] n;

        for (int real=-max; real<=max; real++){
            for (int ima=-max;ima<=max;ima++){
                n = new int[]{real,ima};
                if (isGaussPrime(n)){
                    gaussianPrimes.add(n);
                }
            }
        }
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("data\\gaussian_primes.csv"), "utf-8"));

            for (int i = 0; i<gaussianPrimes.size();i++){
                writer.write(Integer.toString(gaussianPrimes.get(i)[0]));
                writer.write(",");
                writer.write(Integer.toString(gaussianPrimes.get(i)[1]));
                if (i!=gaussianPrimes.size()-1) {
                    writer.write(";");
                }
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isPrime(int n) {
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

    public boolean isGaussPrime(int[] n){
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
