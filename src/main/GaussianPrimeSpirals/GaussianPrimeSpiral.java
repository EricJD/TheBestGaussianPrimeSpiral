package GaussianPrimeSpirals;

import database.GaussianPrimeDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GaussianPrimeSpiral {
    Configuration configuration = Configuration.instance;
    int max = configuration.max;

    public List<int[]> getPrimes() {
        GaussianPrimeDatabase gaussianPrimeDatabase = GaussianPrimeDatabase.getInstance();
        ArrayList<String> gaussianPrimesString = gaussianPrimeDatabase.getGaussianPrimes();

        ArrayList<int[]> gaussianPrimeList = new ArrayList<>();

        for (int i = 0; i < gaussianPrimesString.size(); i++) {
            String a = gaussianPrimesString.get(i);
            String[] b = a.split(",");
            int[] element = new int[b.length];
            for (int x = 0; x < b.length; x++) {
                element[x] = Integer.valueOf(b[x]);
            }
            gaussianPrimeList.add(element);
        }
        return primeSpiral(gaussianPrimeList);
    }

    public List<int[]> primeSpiral(ArrayList<int[]> gaussianPrimeList) {

        List<int[]> gaussianPrimeSpiral = new ArrayList<>();

        Random r = new Random();

        int[] start = new int[]{r.nextInt(2 * max) - max, r.nextInt(2 * max) - max};
        gaussianPrimeSpiral.add(start);

        int x = -1;
        int y = 0;

        int[] pos = new int[2];
        pos[0] = start[0];
        pos[1] = start[1];

        pos[0] = pos[0] + x;
        pos[1] = pos[1] + y;

        while (!Arrays.equals(pos, start)) {
            if (pos[0] > 2 * max || pos[0] < -2 * max || pos[1] > 2 * max || pos[1] < -2 * max) {
                return primeSpiral(gaussianPrimeList);
            }else
            if (gaussianPrimeList.stream().anyMatch(a -> Arrays.equals(a, pos))) {
                gaussianPrimeSpiral.add(pos);
                if (x == -1) { //left
                    x = 0;
                    y = -1;
                } else if (x == 1) { //right
                    x = 0;
                    y = 1;
                } else if (y == -1) { //down
                    x = 1;
                    y = 0;
                } else if (y == 1) { //up
                    x = -1;
                    y = 0;
                }
            }
            pos[0] = pos[0] + x;
            pos[1] = pos[1] + y;
        }
        System.out.println("PrimeSpiral found.");
        return gaussianPrimeSpiral;
    }
}
