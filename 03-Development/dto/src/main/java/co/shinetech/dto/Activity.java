package co.shinetech.dto;

import java.time.LocalDateTime;

public class Activity implements Domain{


	private static final long serialVersionUID = 1L;
	private long id;
	private int name;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String className;
	private User teacher;
	
	public Activity(long id, int name, LocalDateTime startTime, LocalDateTime endTime, String className, User teacher) {
		super();
		this.id = id;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.className = className;
		this.teacher = teacher;
	}

	public long getPk(){
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public int getName() {
		return name;
	}
	
	public void setName(int name) {
		this.name = name;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public User getTeacher() {
		return teacher;
	}
	public void setTeacher(User teacher) {
		this.teacher = teacher;
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
		Activity other = (Activity) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (id != other.id)
			return false;
		if (name != other.name)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", className=" + className + ", teacher=" + teacher + "]";
	}
	
}