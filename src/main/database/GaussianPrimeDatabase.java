package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;

public class GaussianPrimeDatabase {
    private static GaussianPrimeDatabase instance = new GaussianPrimeDatabase();

    public static GaussianPrimeDatabase getInstance() {return instance;}

    private GaussianPrimeDatabase(){}

    private Connection connection;
    private String driverName = "jdbc:hsqldb:";
    private String username = "sa";
    private String password = "";

    public void startup() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            String databaseURL = driverName + Configuration.instance.databaseFile;
            connection = DriverManager.getConnection(databaseURL,username,password);
            if (Configuration.instance.isDebug)
                System.out.println("GaussianPrimeDatabase - connection : " + connection);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void init() {
        importCSVData(Configuration.instance.gaussianPrimesArchive);
    }

    public Connection getConnection() {
        return connection;
    }

    public synchronized void update(String sqlStatement) {
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sqlStatement);
            if (result == -1)
                System.out.println("error executing " + sqlStatement);
            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void dropTable() {
        System.out.println("--- dropTable");

        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("DROP TABLE gaussianprimes");
        System.out.println("sqlStringBuilder : " + sqlStringBuilder.toString());
        update(sqlStringBuilder.toString());
    }

    public void createTable() {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE gaussianprimes ").append(" ( ");
        sqlStringBuilder.append("id VARCHAR(10) NOT NULL").append(",");
        sqlStringBuilder.append("PRIMARY KEY (id)");
        sqlStringBuilder.append(" )");
        update(sqlStringBuilder.toString());
    }

    public String buildSQLStatement(String id) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO gaussianprimes (id) VALUES ('");
        stringBuilder.append(id).append("')");
        return stringBuilder.toString();
    }


    public void shutdown() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            connection.close();
            if (Configuration.instance.isDebug)
                System.out.println("GaussianPrimeDatabase - isClosed : " + connection.isClosed());
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }



    public void importCSVData(String dataPath) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dataPath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(";");
                for(String s : strings){
                    update(buildSQLStatement(s));
                }
            }
            if (Configuration.instance.isDebug)
                System.out.println("GaussianPrimeDatabase - CSV imported ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ArrayList<String> getGaussianPrimes() {
        String query = "SELECT id FROM gaussianprimes;";
        ArrayList<String> output = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                output.add(result.getString("id"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static void main(String[] args) {
        GaussianPrimeDatabase.getInstance().startup();
        GaussianPrimeDatabase.getInstance().dropTable();
        GaussianPrimeDatabase.getInstance().createTable();
        GaussianPrimeDatabase.getInstance().init();
        GaussianPrimeDatabase.getInstance().shutdown();
    }

}
