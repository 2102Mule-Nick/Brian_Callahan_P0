package banking;

import java.util.Scanner;

public class RegisterMenu {
public void register(Scanner scanner) {
		
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

}
