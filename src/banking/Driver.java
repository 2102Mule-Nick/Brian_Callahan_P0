package banking;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Driver {
	
	public static void register(Scanner scanner) {
		
		System.out.println("Register an account");
		System.out.println("Enter a username");
		String username = scanner.next();
		System.out.println("Enter a password");
		String password = scanner.next();
						
		User user = new User(username,password,0,"");
		UserKryo kryo = new UserKryo();
		kryo.createUser(user);
		Menu menu = new Menu();		
		menu.menu(user);
				
	}
	
	public static void login(Scanner scanner) {	
		//Logger log = Logger.getRootLogger();		
		System.out.println("Login");
		System.out.println("Enter a username");
		String username = scanner.next();
		System.out.println("Enter a password");
		String password = scanner.next();		
		UserKryo user = new UserKryo();		
		try {
			User use = user.getUserByUsername(username);
			if (username.equals(use.getUsername()) && password.equals(use.getPassword())){
				System.out.println(use.getBalance());
				Menu menu = new Menu();			
				menu.menu(use);
			} else {
				System.out.println("Password or Username incorrect");
			}			
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
	}
}
