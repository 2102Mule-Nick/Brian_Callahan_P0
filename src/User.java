import java.util.List;

public class User {
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", accountId=" + accountId + "]";
	}
	private String username;
	private String password;
	private int accountId;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	

}
