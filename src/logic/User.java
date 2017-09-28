package logic;

import java.util.ArrayList;

public class User {

	private String name;
	private String password;
	private String email;
	private ArrayList<Team> teams;

	public User() {
		this.teams = new ArrayList<Team>();
	}

	public String getName() {
		return this.name;
	}

	public String getPassword() {
		return this.password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setName(String setName) {
		this.name = setName;
	}

	public void setPassword(String setPassword) {
		this.password = setPassword;
	}

	public void setEmail(String setEmail) {
		this.email = setEmail;
	}

}
