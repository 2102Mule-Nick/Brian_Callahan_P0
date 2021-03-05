package banking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class UserList {
	List<User> userList;
	
	public void createUser(User user) throws UserNameTaken {
		if (user.getUsername() != null && user.getPassword() != null) {			
			Iterator<User> iter = userList.iterator();			
			while (iter.hasNext()) {
				if (iter.next().getUsername().equals(user.getUsername())) {
					throw new UserNameTaken("This username is taken");
				}
			}
			userList.add(user);
			
		}
		/*
		try {
			Output output = new Output(new FileOutputStream("user/test.dat"));
			kryo.writeObject(output, user);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	public UserList() {
		super();
		userList = new ArrayList<>();
		userList.add(new User("keyur", "iamhappy"));
		userList.add(new User("justbrian", "hunter12"));
		userList.add(new User("gael", "g22"));
	}

}
