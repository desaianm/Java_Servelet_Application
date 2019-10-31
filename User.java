package item_manager;

public class User {

	private String username = "";
	private String loginname = "";
	private String password = "";
	private int uid = 0;
	
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public User(String username, String loginname, String password) {
		super();
		this.username = username;
		this.loginname = loginname;
		this.password = password;
		this.uid = uid;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
