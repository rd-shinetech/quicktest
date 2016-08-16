/**
 * ActivityArea.java
 */
package co.shinetech.dto;

/**
 * DTO class for activity area.
 * @author Ricardo
 * @since 2016-08
 */
@SuppressWarnings("serial")
public class ActivityArea implements Domain {
	private long id;
	private String name;

	public ActivityArea(long id) {
		this.id = id;
	}
	
	/**
	 * @param id
	 * @param name
	 */
	public ActivityArea(long id, String name) {
		this(id);
		this.name = name;
	}

	public long getPk() {
		return id;
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
		ActivityArea other = (ActivityArea) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ActivityArea [id=" + id + ", name=" + name + "]";
	}
}
