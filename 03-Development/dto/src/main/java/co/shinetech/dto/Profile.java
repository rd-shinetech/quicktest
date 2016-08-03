package co.shinetech.dto;

public class Profile implements Domain{

	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	
	public Profile(long id, String name){
		this.id = id;
		this.name = name;
	}
	
	public long getPk(){
		return id;
	}

	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;//"Profile [id=" + id + ", name=" + name + "]";
	}
	
	@Override
	public int hashCode() {
		return (int) id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}