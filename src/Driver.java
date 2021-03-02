import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
	
	public static void main(String[] args) {
		/*
		System.out.println("Welcome to the personal banking app");
		System.out.println("Would you like to login/register: Y or N?");
		Scanner scanner = new Scanner(System.in);			
		String welcome = scanner.nextLine();
		
		if (welcome.equalsIgnoreCase("y")) {
			System.out.println("Do you have an account with us?: Y or N?");
			String account = scanner.nextLine();
			if (welcome.equalsIgnoreCase("y")) {
				System.out.println("Please enter your details");
			}
			if (welcome.equalsIgnoreCase("n")) {
				System.out.println("click here to register");
			}
		}
		if (welcome.equalsIgnoreCase("n")) {
			System.out.println("See you later");
		}
		//System.out.println(user);
		 
		 */
		Scanner scanner = new Scanner(System.in);	
		User use = new User();
		AccountBalance acc = new AccountBalance();
		List<User> userList = new ArrayList<>();
		System.out.println("Create an account");
		System.out.println("Please enter a username");
		String username = scanner.nextLine();
		
		use.setUsername(username);
		
		System.out.println("Please enter a password");
		String password = scanner.nextLine();
		
		use.setPassword(password);
		use.setAccountId(1001);
		acc.setAccountId(1001);
		System.out.println("You have been assigned Account Id: "+ use.getAccountId());
		System.out.println("Initial balance: set an amount to deposit");
		String amount = scanner.nextLine();
		acc.setBalance(Integer.valueOf(amount));
		
		userList.add(use);
		
		System.out.println(use.getUsername()+" "+use.getPassword()+" "+acc.getBalance());
		
		/*
		for(User x : userList) {
			System.out.println(x.toString());
		}
		*/
		
	}
}
