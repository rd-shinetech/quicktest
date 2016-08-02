package co.shinetech.dto;

import java.util.ArrayList;

public class Group implements Domain{
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private ArrayList<User> users;	
	
	public Group(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getPk() {
		return id;
	}
	public void setPk(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
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
		Group other = (Group) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", users=" + users + "]";
	}
	
}
