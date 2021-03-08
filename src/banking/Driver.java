package banking;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Driver {
	
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		WelcomeMenu welcome = new WelcomeMenu();
		welcome.welcome(scanner);
	}
}
