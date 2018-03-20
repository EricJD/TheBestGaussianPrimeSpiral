package GaussianPrimes;

public class GaussPrimeSieve {

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
