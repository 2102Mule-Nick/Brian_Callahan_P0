package banking;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class WelcomeMenu {
	Logger logger = Logger.getRootLogger();
	
	public void welcome(Scanner scanner) {
		System.out.println("Welcome to the personal banking app");
		System.out.println("Would you like to login or register?");
		String welcome = scanner.nextLine();
		LoginMenuPostgres log = new LoginMenuPostgres();
		RegisterMenuPostgres reg = new RegisterMenuPostgres();
		reg.setLog(logger);
		log.setLog(logger);
		if (welcome.equals("login")) {
			log.login(scanner);			
		} else if (welcome.equals("register")) {
			reg.register(scanner);			
		} else {
			System.out.println("We didn't quite get that please try again.");
			WelcomeMenu wel = new WelcomeMenu();
			wel.welcome(scanner);
		}			
	}
}
