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
		balance +=n;
		transactions+= "Deposited: " +n+" ";
	}
	
	public void withdraw(int n) {
		if (balance>n) {
			balance -=n;
			transactions+= "Withdrew: " +n+" ";
		}
	}
	
	public void interest(int year) {
		
	}
	
	
}
