package banking;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
	
	public static void register(Scanner scanner) {
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
		Menu menu = new Menu();		
		menu.menu(user);
		
		
	}
	
	public static void login(Scanner scanner) {
		System.out.println("Login");
		System.out.println("Enter a username");
		String username = scanner.next();
		System.out.println("Enter a password");
		String password = scanner.next();
						
		UserList userlist = new UserList();
		try {
			userlist.getUser(username);
			User user = new User(username,password);
			Menu menu = new Menu();			
			menu.menu(user);
		} catch (UserNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the personal banking app");
		System.out.println("Would you like to login or register?");
		String welcome = scanner.nextLine();
		if (welcome.equals("login")) {
			Driver.login(scanner);
			
		} else if (welcome.equals("register")) {
			Driver.register(scanner);
			
		}
		System.out.println("would you like to login? y or n");
		String login = scanner.nextLine();
		if (login.equalsIgnoreCase("y")) {
			Driver.login(scanner);
			
		} else if (login.equalsIgnoreCase("n")) {
			System.out.println("Good Bye");
			
		} 
		
		
		
	}
}
