/**
 * 
 */
package co.shinetech.dto;

import java.util.Arrays;

/**
 * @author Robin
 *
 */
public class User implements Domain {
	private long id;
	private String login;
	private char[] password;
	private Profile profile;
	
	public User(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	
	public void setLogin(String login) {
		this.login = login;
	}

	
	public char[] getPassword() {
		return password;
	}

	
	public void setPassword(char[] password) {
		this.password = password;
	}

	
	public Profile getProfile() {
		return profile;
	}

	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public long getPk() {
		return this.id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password="
			+ Arrays.toString(password) + ", profile=" + profile + "]";
	}
}
