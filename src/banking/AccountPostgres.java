package banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		String sql ="UPDATE accounts SET email = ? WHERE user_id = (select user_id from users where username = '" + username + "')";
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, username);
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
	
	public void setBalanceByUsername(int balance, String username) {
		
		
		String sql ="UPDATE accounts SET balance = ? WHERE user_id = (select user_id from users where username = '" + username + "')";

		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, String.valueOf(balance));
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
	
	public int getBalanceByUsername(String username) {		
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
		AccountPostgres acc = new AccountPostgres(user);
		balance=acc.getBalanceByUsername(user.getUsername())-balance;

		String sql ="UPDATE accounts SET balance = ? WHERE user_id = (select user_id from users where username = '" + username + "')";

		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, balance);
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
	
public String getTierByUsername(String username) throws UserNotFound {
		
	try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);){			
		String sql ="select tier from accounts WHERE user_id = (select user_id from users where username = '" + username + "')";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		
		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
		        String columnValue = rs.getString(i);
		        if (columnValue != null) {
		        	return columnValue;
		        }					        
		    }					
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "";
	}

public void setTierByUsername(String tier, String username) throws UserNotFound {
	
	String sql ="UPDATE accounts SET tier = '"+tier+"' WHERE user_id = (select user_id from users where username = '" + username + "')";

	Connection conn;
	try {
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, String.valueOf(tier));
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
}
