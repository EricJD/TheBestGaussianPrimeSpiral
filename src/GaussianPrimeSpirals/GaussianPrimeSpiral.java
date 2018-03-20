package GaussianPrimeSpirals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GaussianPrimeSpiral {

    public void primeSpirals() {

        GaussianPrimes gaussianPrimes = new GaussianPrimes();
        List<int[]> gaussianPrimeList = gaussianPrimes.generatePrimes();

        List<int[]> gaussianPrimeSpiral = new ArrayList<>();

        Random r = new Random();

        int[] start = new int[]{r.nextInt(241)-120,r.nextInt(241)-120};
        gaussianPrimeSpiral.add(start);

        int x = -1;
        int y = 0;

        int[] pos = new int[2];
        pos[0] = start[0];
        pos[1] = start [1];

        pos[0]=pos[0]+x;
        pos[1]=pos[1]+y;

        while (!Arrays.equals(pos,start)){
            if (pos[0]>120||pos[0]<-120||pos[1]>120||pos[1]<-120){
                System.out.println("Out of bounds.");
                System.exit(1);
            }
            if (gaussianPrimeList.stream().anyMatch(a -> Arrays.equals(a,pos))){
                gaussianPrimeSpiral.add(pos);
                if (x==-1){ //left
                    x=0;
                    y=-1;
                }else if (x==1){ //right
                    x=0;
                    y=1;
                }else if (y==-1){ //down
                    x=1;
                    y=0;
                }else if (y==1){ //up
                    x=-1;
                    y=0;
                }
            }
            pos[0]=pos[0]+x;
            pos[1]=pos[1]+y;
        }
        System.out.println("PrimeSpiral found.");
    }
}
