package banking;

import java.util.Scanner;

public class LoginMenu {
	public void login(Scanner scanner) {	
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

}
