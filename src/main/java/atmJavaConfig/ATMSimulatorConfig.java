package atmJavaConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ATMSimulatorConfig {
    @Bean
    public DataSource dataSource() {
        return new DataSource("customers.txt");
    }

    @Bean
    public Bank bank() {
        return new Bank(dataSource());
    }

    @Bean
    public ATM atm() {
        return new ATM(bank());
    }

    @Bean
    public ATMSimulator atmSimulator() {
        return new ATMSimulator(atm());
    }
}
