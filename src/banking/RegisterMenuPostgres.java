package banking;

import java.util.Scanner;

import org.apache.log4j.Logger;


public class RegisterMenuPostgres {
	Logger log;
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
	System.out.println("Enter your email");
	String email = scanner.next();
	System.out.println("Please enter an initial deposit");
	String deposit = scanner.next();
					
	User user = new User(username,password);	
	UserPostgres userPost = new UserPostgres();	
	userPost.setLog(log);
	AccountPostgres acc = new AccountPostgres(user);
	
	try {
		if (acc.getUserByUsername(username)==null) {
			
			try {
				userPost.createUser(user);
				acc.setEmailByUsername(email,username);
				acc.setBalanceByUsername(Float.valueOf(deposit),username);
				if(Float.valueOf(deposit)<1000) {
					acc.setTierByUsername("bronze", username);
				} else if (Float.valueOf(deposit)<1000) {
					acc.setTierByUsername("gold", username);
				}
			} catch (UserNameTaken e) {
				// TODO Auto-generated catch block
				log.warn("User Taken",e);
			}
			Menu menu = new Menu();		
			menu.menu(user,scanner);
		}else {
			log.info("User not available");
			System.out.println("That Username is unavailable please enter a different one");
			RegisterMenuPostgres reg = new RegisterMenuPostgres();
			reg.setLog(log);
			reg.register(scanner);	
		}
	} catch (UserNotFound e1) {
		// TODO Auto-generated catch block
		log.warn("User not found",e1);
	}
				
	}
}
