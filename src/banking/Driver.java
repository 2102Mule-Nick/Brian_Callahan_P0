package banking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;

public class Driver {
	public static String URL;

	public static String USERNAME;

	public static String PASSWORD;
	
	private static ConnectionPostgres connectionFactory = null;
	
	public static void main(String[] args) {
		/*
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
		}

		//URL = "jdbc:postgresql://" + System.getenv("POS_DB_URL") + ":5432/" + "postgres" + "?";
			
		//USERNAME = System.getenv("POS_DB_USERNAME");
		//PASSWORD = System.getenv("POS_DB_PASSWORD");
		URL = "jdbc:postgresql://localhost:5432/postgres?";
		USERNAME = "postgres";
		PASSWORD = "password";
		
		User user = new User();
		user.setPassword("password");
		user.setUsername("user2");
		
		UserPostgres usep = new UserPostgres();
		
		try {
			User test=usep.getUserByUsername("user2");
			System.out.println(test.getPassword());
		} catch (UserNotFound e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Print pr = new Print();
		//pr.printUsers();
		System.out.println();
		pr.printBalance();;
		
		
		System.out.println("done");
		*/
		
		
		BasicConfigurator.configure();
		Scanner scanner = new Scanner(System.in);
		WelcomeMenu welcome = new WelcomeMenu();
		welcome.welcome(scanner);
		
	
		
	}
}
