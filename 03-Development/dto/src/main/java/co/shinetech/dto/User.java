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
	
	public User(long id) {
		this.id = id;
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
			+ Arrays.toString(password) + "]";
	}
	
}
