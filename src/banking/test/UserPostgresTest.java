package banking.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import banking.User;
import banking.UserNameTaken;
import banking.UserPostgres;
//import org.mockito.junit.jupiter.MockitoExtension;

//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class UserPostgresTest {
	
	@Mock
	private Connection connection;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void createUsertest() throws SQLException {
	//String sql ="WITH ins1 AS (INSERT INTO users(username,pass_word) VALUES (?, ?) RETURNING user_id) INSERT INTO accounts (user_id) SELECT i.user_id FROM   ins1 i;";
	String URL = "jdbc:postgresql://" + System.getenv("DB_URL") + ":5432/" + "postgres" + "?";
	String USERNAME = System.getenv("DB_USERNAME");
	String PASSWORD = System.getenv("DB_PASSWORD");
		
	String sql = "insert into users (username, pass_word) values(?, ?)";
	
	Connection realConnection =  DriverManager.getConnection(URL, USERNAME, PASSWORD);
	
	System.out.println(realConnection);
	
	PreparedStatement realStmt = realConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	PreparedStatement spy = Mockito.spy(realStmt);
	
	System.out.println("Spy" + spy);
	
	System.out.println("Conn Mock" + connection);

	when(connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)).thenReturn(spy);
		
	User user = new User();
	
	user.setUsername("username");
	user.setPassword("password");
	
	UserPostgres usp = new UserPostgres();
	try {
		usp.createUser(user);
	} catch (UserNameTaken e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	verify(spy).setString(1, user.getUsername());
	
	verify(spy).setString(2, user.getPassword());
	
	verify(spy).executeUpdate();
	
	PreparedStatement checkStmt = null;
	try {
		checkStmt = DriverManager.getConnection(URL, USERNAME, PASSWORD).prepareStatement("select * from users where user_id = 1");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	ResultSet rs = checkStmt.executeQuery();
	
	assertTrue(rs.next());
	}

}
