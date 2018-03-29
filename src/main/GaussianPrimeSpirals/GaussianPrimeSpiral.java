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

    public int[] dir(int i) {
        int[] dir = new int[2];
        switch (i) {
            //up
            case 0:
                dir = new int[]{0, 1};
                break;
            //left
            case 1:
                dir = new int[]{-1, 0};
                break;
            //down
            case 2:
                dir = new int[]{0, -1};
                break;
            //right
            case 3:
                dir = new int[]{1, 0};
                break;
        }
        return dir;
    }

    public List<int[]> primeSpiral(ArrayList<int[]> gaussianPrimeList) {

        List<int[]> gaussianPrimeSpiral = new ArrayList<>();

        Random r = new Random();

        int[] start = new int[]{r.nextInt(100) - 50, r.nextInt(100) - 50};
        gaussianPrimeSpiral.add(start);

        int[] dir = dir(r.nextInt(4));

        int x = dir[0];
        int y = dir[1];

        int[] pos = new int[2];
        pos[0] = start[0];
        pos[1] = start[1];

        pos[0] = pos[0] + x;
        pos[1] = pos[1] + y;

        while (!Arrays.equals(pos, start)) {
            if (pos[0] > 2 * max || pos[0] < -2 * max || pos[1] > 2 * max || pos[1] < -2 * max) {
                return primeSpiral(gaussianPrimeList);
            } else if (gaussianPrimeList.stream().anyMatch(a -> Arrays.equals(a, pos))) {
                int[] point = new int[2];
                point[0] = pos[0];
                point[1]=pos[1];
                gaussianPrimeSpiral.add(point);
                switch (x) {
                    case -1:
                        x = 0;
                        y = -1;
                        break;
                    case 1:
                        x = 0;
                        y = 1;
                        break;
                    case 0:
                        switch (y) {
                            case -1:
                                x = 1;
                                y = 0;
                                break;
                            case 1:
                                x = -1;
                                y = 0;
                                break;
                        }
                        break;
                }
            }
            pos[0] = pos[0] + x;

            pos[1] = pos[1] + y;
        }
        gaussianPrimeSpiral.add(start);

        return gaussianPrimeSpiral;
    }
}
