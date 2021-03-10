package banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountPostgres {
	//Logger log = Logger.getRootLogger();
	String USERNAME = "postgres";
	String PASSWORD = "password";
	String URL = "jdbc:postgresql://localhost:5432/postgres?";
	

	public void createAccount() {		
		String USERNAME = "postgres";
		String PASSWORD = "password";
		String URL = "jdbc:postgresql://localhost:5432/postgres?";
		
		//String sql3 = "insert into accounts (email, balance) values ('bob@gmail.com',0)";
		String sql ="UPDATE accounts SET email = 'bob@gmail.com', balance= 0 WHERE user_id = 1;";
		//String sql = "select * from accounts";
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
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
				
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
			
			//conn.setSchema(schema);
			
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
}
