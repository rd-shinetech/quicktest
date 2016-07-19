package co.shinetech.dto;

import java.time.LocalDateTime;
/**
 * @author Ricardo
 *
 */
public class Result implements Domain {
	
	// Attributes
	private long id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	public Result(long id) {
		this.id = id;
	}
	
	public long getPk() {
		return id;
	}	
	
	// Access methods
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
	
	// hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		return result;
	}
	
	// equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}
	
	// toString
	@Override
	public String toString() {
		return "Result [startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
	
}
