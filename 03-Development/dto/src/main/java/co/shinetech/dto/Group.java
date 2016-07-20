package co.shinetech.dto;

public class Group implements Domain{
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	
	
	
	public Group(long id, String name) {
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
		return true;
	}
	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + "]";
	}
}
