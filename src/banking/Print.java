package banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Print {
	String URL = "jdbc:postgresql://localhost:5432/postgres?";
	String USERNAME = "postgres";
	String PASSWORD = "password";
	
	public void printUsers() {
		//prints table contents
				try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);){			
					String sql = "select * from users";
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnsNumber = rsmd.getColumnCount();
					
					while (rs.next()) {
						for (int i = 1; i <= columnsNumber; i++) {
					        if (i > 1) System.out.print(",  ");
					        String columnValue = rs.getString(i);
					        System.out.print(columnValue + " " + rsmd.getColumnName(i));
					    }
					    System.out.println("");
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	public void printAccounts() {
		//prints table contents
				try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);){			
					String sql = "select * from accounts";
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnsNumber = rsmd.getColumnCount();
					
					while (rs.next()) {
						for (int i = 1; i <= columnsNumber; i++) {
					        if (i > 1) System.out.print(",  ");
					        String columnValue = rs.getString(i);
					        System.out.print(columnValue + " " + rsmd.getColumnName(i));
					    }
					    System.out.println("");
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public void printBalance() {
		//prints table contents
				try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);){			
					String sql = "select balance from accounts";
					//String sql ="select balance accounts WHERE user_id = (select user_id from users where username = '" + username + "')";
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnsNumber = rsmd.getColumnCount();
					
					while (rs.next()) {
						for (int i = 1; i <= columnsNumber; i++) {
					        String columnValue = rs.getString(i);
					        if (columnValue != null) {
					        	System.out.println(Integer.valueOf(columnValue));
					        }					        
					    }					
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

}
