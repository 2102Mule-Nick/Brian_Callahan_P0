package banking;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class LoginMenu {
	Logger log;
	public Logger getLog() {
		return log;
	}
	public void setLog(Logger log) {
		this.log = log;
	}
	public void login(Scanner scanner) {	

		log.info("Logging in user");
		System.out.println("Login");
		System.out.println("Enter a username");
		String username = scanner.next();
		System.out.println("Enter a password");
		String password = scanner.next();		
		UserKryo user = new UserKryo();		
		try {
			User use = user.getUserByUsername(username);
			if (username.equals(use.getUsername()) && password.equals(use.getPassword())){
				Menu menu = new Menu();			
				menu.menu(use,scanner);
			} else {
				System.out.println("Password or Username incorrect");
			}			
		} catch (UserNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
