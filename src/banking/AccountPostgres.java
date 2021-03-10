package banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountPostgres {
	public AccountPostgres(User user) {
		super();
		this.user = user;
	}

	//Logger log = Logger.getRootLogger();
	String USERNAME = "postgres";
	String PASSWORD = "password";
	String URL = "jdbc:postgresql://localhost:5432/postgres?";
	User user;
	

	public void setEmailByUsername(String email, String username) {		
		String USERNAME = "postgres";
		String PASSWORD = "password";
		String URL = "jdbc:postgresql://localhost:5432/postgres?";

		String sql ="UPDATE accounts SET email = '"+email+"' WHERE user_id = (select user_id from users where username = '" + username + "')";
		String sql2 = "select user_id from users where username = '" + username + "'";

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
	
	public void setBalanceByUsername(int balance, String username) {
		String USERNAME = "postgres";
		String PASSWORD = "password";
		String URL = "jdbc:postgresql://localhost:5432/postgres?";
		
		String sql ="UPDATE accounts SET balance = '"+balance+"' WHERE user_id = (select user_id from users where username = '" + username + "')";

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
	
	public int getBalanceByUsername(String username) {
		String USERNAME = "postgres";
		String PASSWORD = "password";
		String URL = "jdbc:postgresql://localhost:5432/postgres?";
		
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);){			
			String sql ="select balance from accounts WHERE user_id = (select user_id from users where username = '" + username + "')";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
			        String columnValue = rs.getString(i);
			        if (columnValue != null) {
			        	return Integer.valueOf(columnValue);
			        }					        
			    }					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public void deposit(int balance, String username) {
		String USERNAME = "postgres";
		String PASSWORD = "password";
		String URL = "jdbc:postgresql://localhost:5432/postgres?";
		
		AccountPostgres acc = new AccountPostgres(user);
		balance+=acc.getBalanceByUsername(user.getUsername());

		String sql ="UPDATE accounts SET balance = '"+balance+"' WHERE user_id = (select user_id from users where username = '" + username + "')";

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
	
	public void withdraw(int balance, String username) {
		String USERNAME = "postgres";
		String PASSWORD = "password";
		String URL = "jdbc:postgresql://localhost:5432/postgres?";
		
		AccountPostgres acc = new AccountPostgres(user);
		balance=acc.getBalanceByUsername(user.getUsername())-balance;

		String sql ="UPDATE accounts SET balance = '"+balance+"' WHERE user_id = (select user_id from users where username = '" + username + "')";

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
