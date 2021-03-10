package banking;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class RegisterMenu {
		Logger log;
public Logger getLog() {
			return log;
		}
		public void setLog(Logger log) {
			this.log = log;
		}
public void register(Scanner scanner) {
	
		log.info("Registering user");
		System.out.println("Register an account");
		System.out.println("Enter a username");
		String username = scanner.next();
		System.out.println("Enter a password");
		String password = scanner.next();
						
		User user = new User(username,password);
		UserKryo kryo = new UserKryo();
		kryo.createUser(user);
		Menu menu = new Menu();		
		menu.menu(user,scanner);
				
	}

}
