package banking;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
	
	public static void main(String[] args) {
		/*
		
		System.out.println("Would you like to login/register: Y or N?");
		Scanner scanner = new Scanner(System.in);			
		String welcome = scanner.nextLine();
		*/
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the personal banking app");
		System.out.println("Would you like to login or register?");
		String welcome = scanner.nextLine();
		if (welcome == "login") {
			
		} else if (welcome.equals("register")) {
			System.out.println("Register an account");
			System.out.println("Enter a username");
			String username = scanner.next();
			System.out.println("Enter a password");
			String password = scanner.next();
				
			
			User user = new User(username,password);
			UserList userlist = new UserList();
					
			try {
				userlist.createUser(user);
			} catch (UserNameTaken e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Account acc = new Account(user);
			Menu menu = new Menu();
			
			menu.menu(user);
			
		}
		
		System.out.println("Ended");
		
	}
}
