
import java.util.*;

public class User {

	Scanner input = new Scanner(System.in);
	private String loginName; // IcePeak
	private String password; // pain password
	

	public User(String loginName, String password) {

		this.loginName = loginName;
		this.password = password;
	}

	public String getLoginName() {
		return loginName;

	}
	
	 public boolean checkUsername(String username) {
	        return this.loginName.equals(username);
	    }

	    public boolean checkPassword(String password) {
	        return this.password.equals(password);
	    }

	
}
