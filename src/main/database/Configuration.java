package database;

public enum Configuration {
    instance;

    boolean isDebug = true;

    public String fileSeparator = System.getProperty("file.separator");
    public String userDirectory = System.getProperty("user.dir");
    public String dataDirectory = userDirectory + fileSeparator + "data" + fileSeparator;
    public String databaseFile = dataDirectory + "gaussianPrimes.db";
    public String gaussianPrimesArchive = dataDirectory + "gaussian_primes.csv";
}
