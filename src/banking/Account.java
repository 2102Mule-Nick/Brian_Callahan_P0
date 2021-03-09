package banking;

public class Account {
	int balance;
	String transactions ="";
	User user;
	public Account(User user) {
		super();
		this.user = user;
	}
	
	public void deposit(int n) {
		user.setBalance(user.getBalance()+n);
		user.setTransactions(user.getTransactions()+" Deposited: " +n+" ");
		UserKryo u = new UserKryo();
		u.createUser(user);		
	}
	
	public void withdraw(int n) {
			user.setBalance(user.getBalance()-n);
			user.setTransactions(user.getTransactions()+" Withdrew: " +n+" ");
			UserKryo u = new UserKryo();
			u.createUser(user);			
	}
	
	public void interest(int year) {
		
	}
	
	
}
