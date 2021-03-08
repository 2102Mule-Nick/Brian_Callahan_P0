package banking;

import java.util.Scanner;

public class WelcomeMenu {
	public void welcome(Scanner scanner) {
		System.out.println("Welcome to the personal banking app");
		System.out.println("Would you like to login or register?");
		String welcome = scanner.nextLine();
		LoginMenu log = new LoginMenu();
		RegisterMenu reg = new RegisterMenu();
		if (welcome.equals("login")) {
			log.login(scanner);			
		} else if (welcome.equals("register")) {
			reg.register(scanner);			
		}	
		
		
	}

}
