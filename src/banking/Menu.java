package banking;

import java.util.Scanner;


public class Menu {
	
public void menu(User user, Scanner scanner) throws UserNotFound {
		String choice ="";
		AccountPostgres acc = new AccountPostgres(user);
		System.out.println();
		System.out.println("Welcome "+user.getUsername()+" "+acc.getTierByUsername(user.getUsername())+" level");
		System.out.println("Select an option");
		System.out.println("A: Balance");
		System.out.println("B: Deposit");
		System.out.println("C: Withdraw");
		System.out.println("D: Transactions");
		System.out.println("E: Exit");		

		do {
		choice = scanner.next();
		choice=choice.toUpperCase();
		
		switch (choice) {
		case "A": //balance
			System.out.println("Balance "+acc.getBalanceByUsername(user.getUsername()));
			break;
		case "B": //deposit
			System.out.println("Enter an amount to deposit");
			int deposit = scanner.nextInt();
			acc.deposit(deposit,user.getUsername());
			break;
		case "C": //withdraw
			System.out.println("Enter an amount to withdraw");
			int withdraw = scanner.nextInt();
			acc.withdraw(withdraw,user.getUsername());
			break;
		case "D": //transactions
			//System.out.println("Transactions: "+user.getTransactions());
			break;
		case "E": //exit
			System.out.println("Exiting");
			break;	
		default:
			System.out.println("Please type a valid input");					
		}	
		}while (!choice.equalsIgnoreCase("E"));		
	}
}
