package banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

public class UserPostgres {
	
	//Logger log = Logger.getRootLogger();
	//String USERNAME = "postgres";
	//String PASSWORD = "password";
	//String URL = "jdbc:postgresql://localhost:5432/postgres?";
	
	String URL = "jdbc:postgresql://" + System.getenv("DB_URL") + ":5432/" + "postgres" + "?";
	
	String USERNAME = System.getenv("DB_USERNAME");
	
	String PASSWORD = System.getenv("DB_PASSWORD");
	
	
	
	
	
	public void createUser(User user) throws UserNameTaken {
		
		//log.trace("UserDaoPostgres.createUser method called");
				
		String sql ="WITH ins1 AS (INSERT INTO users(username,pass_word) VALUES (?, ?) RETURNING user_id) INSERT INTO accounts (user_id) SELECT i.user_id FROM   ins1 i;";
		
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, user.getUsername());
				stmt.setString(2, user.getPassword());
				stmt.executeUpdate();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public User getUserByUsername(String username) throws UserNotFound {		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
		}

		User user = null;
		
		//String url = "jdbc:postgresql://" + System.getenv("POS_DB_URL") + ":5432/" + "postgres" + "?";
		//String usr = System.getenv("POS_DB_USERNAME");
		//String password = System.getenv("POS_DB_PASSWORD");		
		//log.info("usr : " + usr);
		//log.info("password : " + password);
		
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
			String sql = "select * from users where username = '" + username + "'";			
			Statement stmt = conn.createStatement();			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				//log.info("User found in DB");
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("pass_word"));
			}			
		} catch (SQLException e) {
			//log.error("Failure to connect to DB", e);
		}		
		return user;
	}
public User getIDByUsername(String username) throws UserNotFound {		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
		}

		User user = null;		
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
			String sql = "select user_id from users where username = '" + username + "'";
			Statement stmt = conn.createStatement();			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				//log.info("User found in DB");
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("pass_word"));
			}			
		} catch (SQLException e) {
			//log.error("Failure to connect to DB", e);
		}		
		return user;
	}
}
