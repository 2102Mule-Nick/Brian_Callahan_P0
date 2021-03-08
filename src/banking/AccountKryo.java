package banking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;



public class AccountKryo {
	private Kryo kryo = new Kryo();

	private Logger log = Logger.getRootLogger();
	
	private static final String FOLDER_NAME = "users\\";
	
	private static final String FILE_EXTENSION = ".dat";

public void createUser(User user){
		try(FileOutputStream outputStream = new FileOutputStream(FOLDER_NAME + user.getUsername()+"balance"+ FILE_EXTENSION)) {
			Output output = new Output(outputStream);
			kryo.writeObject(output, "sum");
			output.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

public String getUserByUsername(String username) throws UserNotFound {
	try (FileInputStream inputStream = new FileInputStream(FOLDER_NAME + username + FILE_EXTENSION)) {
		Input input = new Input(inputStream);
		String user = kryo.readObject(input, String.class);
		input.close();
		//System.out.println(user);
		return user;		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	return null;
}

public AccountKryo() {
	super();
	kryo.register(User.class);
}
}
