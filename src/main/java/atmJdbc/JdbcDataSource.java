package atmJdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcDataSource implements DataSource {
    private JdbcTemplate jdbcTemplate;

    public JdbcDataSource(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<Integer, Customer> readCustomers() {
        String query = "SELECT * FROM customers";
        List<Customer> customerList = jdbcTemplate.query(query, new CustomerRowMapper());

        Map<Integer, Customer> customers = new HashMap<Integer, Customer>();

        for (Customer c : customerList) {
            customers.put(c.getCustomerNumber(), c);
        }

        return customers;
    }
}
