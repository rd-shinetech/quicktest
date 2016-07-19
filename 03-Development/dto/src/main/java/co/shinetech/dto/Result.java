package co.shinetech.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * @author Ricardo
 *
 */
public class Result implements Domain {
	
	// Attributes
	private long id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private ArrayList<ResultItem> items = new ArrayList<ResultItem>(); 
	
	public Result(long id) {
		this.id = id;
	}
	
	public long getPk() {
		return id;
	}	
	
	// Access methods
	
	/** 
	 * adding elements to the list
	 * @param rItem - ResultItem
	 */
	public void addItems(ResultItem rItem) {
		items.add(rItem);
	}
	
	/**
	 * removing elements from the list
	 * @param itemRemove
	 */
	public void removeItems(ResultItem itemRemove) {
		items.remove(itemRemove);
	}
	
	/**
	 * Iterate through the list (items)
	 * @return
	 */
	public Iterator<ResultItem> iterateItems() {
		return items.iterator();
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
	
	// hashCode	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (id != other.id)
			return false;
		return true;
	}
	// toString

	@Override
	public String toString() {
		return "Result [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
		
	
}
