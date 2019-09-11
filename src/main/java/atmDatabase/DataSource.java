package atmDatabase;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DataSource {

    private String filename;

    /**
     * @param filename the name of the customer file
     */
    public DataSource(String filename) {
        this.filename = filename;
    }

    /**
     * Reads the customer numbers and pins
     * and initializes the bank accounts.
     */
    public Map<Integer, Customer> readCustomers() throws ClassNotFoundException, SQLException {
        Map<Integer, Customer> customers = new HashMap<Integer, Customer>();

        Class.forName("org.sqlite.JDBC");

        String dbURL = "jdbc:sqlite:" + filename;
        Connection connection = DriverManager.getConnection(dbURL);

        if (connection != null) {
            String query = "select * from customers";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int number = resultSet.getInt(1);
                int pin = resultSet.getInt(2);
                int balance = resultSet.getInt(3);
                Customer c = new Customer(number, pin, balance);
                customers.put(c.getCustomerNumber(), c);
            }

            connection.close();
        }
        return customers;
    }
}
