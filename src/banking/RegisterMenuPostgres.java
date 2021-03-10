package banking;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class RegisterMenuPostgres {
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
	//UserPos kryo = new UserKryo();
	
	UserPostgres userPost = new UserPostgres();	
	
	try {
		userPost.createUser(user);
	} catch (UserNameTaken e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Menu menu = new Menu();		
	menu.menu(user,scanner);
			
}

}
