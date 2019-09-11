package atmDatabase;

import java.sql.SQLException;
import java.util.Scanner;

/**
   A text-based simulation of an automatic teller machine.
 */
public class ATMSimulator {

	private ATM atm;

	public ATMSimulator(ATM atm) {
		this.atm = atm;
	}

	public void run() {
		try {
			atm.init();
		}
		catch(SQLException | ClassNotFoundException e) {
			System.out.println("Error reading account data.");
			return;
		}

		Scanner sc = new Scanner(System.in);

		while (true) {
			int state = atm.getState();

			if (state == ATM.START) {
				System.out.print("Enter customer number: ");
				int number = sc.nextInt();
				System.out.print("Enter PIN: ");
				int pin = sc.nextInt();
				atm.validateCustomer(number, pin);
			}
			else if (state == ATM.TRANSACT) {
				System.out.println("Balance=" + atm.getBalance());
				System.out.print("A=Deposit, B=Withdrawal, C=Transfer, D=Done, E=Exit: ");
				String command = sc.next();

				if (command.equalsIgnoreCase("A")) {
					System.out.print("Amount: ");
					double amount = sc.nextDouble();
					atm.deposit(amount);
				}
				else if (command.equalsIgnoreCase("B")) {
					System.out.print("Amount: ");
					double amount = sc.nextDouble();
					atm.withdraw(amount);
				}
				else if (command.equalsIgnoreCase("C")) {
					System.out.print("Transfer To: ");
					int transferTo = sc.nextInt();
					System.out.print("Amount: ");
					double amount = sc.nextDouble();
					atm.transfer(transferTo, amount);
				}
				else if (command.equalsIgnoreCase("D")) {
					atm.reset();
				}
				else if (command.equalsIgnoreCase("E"))
					System.exit(0);
				else
					System.out.println("Illegal input!");                                    
			}         
		}
	}
}

