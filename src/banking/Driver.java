package banking;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;

public class Driver {
	public static void main(String[] args) {
		//BasicConfigurator.configure();
		Scanner scanner = new Scanner(System.in);
		WelcomeMenu welcome = new WelcomeMenu();
		welcome.welcome(scanner);
	}
}
