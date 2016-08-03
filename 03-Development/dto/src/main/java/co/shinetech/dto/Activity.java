package co.shinetech.dto;

import java.time.LocalDateTime;

import javax.swing.JComboBox;



public class Activity implements Domain{


	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private User teacher;
	private ActivityType activityType;
	private Group group;
	
	
	public Activity(long id) {
		super();
		this.id = id;
	}
	public Activity(long id, String name, LocalDateTime startTime, LocalDateTime endTime, User teacher, ActivityType activityType,
			Group group) {
		super();
		this.id = id;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.teacher = teacher;
		this.activityType = activityType;
		this.group = group;
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
	public User getTeacher() {
		return teacher;
	}
	public void setTeacher(User teacher2) {
		this.teacher = teacher2;
	}

	public ActivityType getActivityType() {
		return activityType;
	}
	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityType == null) ? 0 : activityType.hashCode());
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
		if (activityType != other.activityType)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", teacher=" + teacher + ", activityType=" + activityType + ", group=" + group + "]";
	}
}