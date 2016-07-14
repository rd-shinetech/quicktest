package co.shinetech.dto;

public class Profile implements Domain{
	private long id;
	private String name;
	
	public Profile(long id, String name){
		this.id = id;
		this.name = name;
	}
	
	public long getPk(){
		return id;
	}
	
	public void setPk(long pk){
		id = pk;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return "id: " + id + ", Name: " + name;
	}
	
	@Override
	public boolean equals(Object other){
		if(other instanceof Profile){
			Profile p = (Profile) other;
			return (this.id == p.getPk()) && this.name.equals(p.getName());
		}
		return false;
		
	}
	
}