package banking;
import java.util.Scanner;

public class Account {
	int balance;
	String transactions ="";
	User user;
	int pid;
	public Account(User user) {
		super();
		this.user = user;
	}
	
	public void deposit(int n) {
		user.setBalance(user.getBalance()+n);
		UserKryo u = new UserKryo();
		u.createUser(user);
		balance +=n;
		transactions+= "Deposited: " +n+" ";
	}
	
	public void withdraw(int n) {
		if (balance>n) {
			user.setBalance(user.getBalance()-n);
			UserKryo u = new UserKryo();
			u.createUser(user);
			balance -=n;
			transactions+= "Withdrew: " +n+" ";
		}
	}
	
	public void interest(int year) {
		
	}
	
	
}
