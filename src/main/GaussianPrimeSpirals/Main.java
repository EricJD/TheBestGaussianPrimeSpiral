package GaussianPrimeSpirals;

import database.GaussianPrimeDatabase;

public class Main {
    public static void main(String[] args) {
        GaussianPrimeDatabase gaussianPrimeDatabase = GaussianPrimeDatabase.getInstance();

        gaussianPrimeDatabase.startup();
        gaussianPrimeDatabase.dropTable();
        gaussianPrimeDatabase.createTable();

        GaussianPrimes gaussianPrimes = new GaussianPrimes();
        gaussianPrimes.generatePrimes();

        gaussianPrimeDatabase.init();

        GaussianPrimeSpiral gaussianPrimeSpiral = new GaussianPrimeSpiral();
        gaussianPrimeSpiral.primeSpirals();

        gaussianPrimeDatabase.shutdown();


    }
}
