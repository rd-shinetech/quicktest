package co.shinetech.dto;
/**
 * @author Ricardo
 *
 */
public class ResultItem implements Domain {
	
	// Attributes
	private long id;
	private String result;
	
	public ResultItem(long id, String result) {
		this.id = id;
		this.result = result;
	}
	
	public ResultItem(long id) {
		this.id = id;
	}
	
	public long getPk() {
		return id;
	}
	
	// Access methods
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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
		ResultItem other = (ResultItem) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	// toString
	@Override
	public String toString() {
		return "ResultItem [id=" + id + ", result=" + result + "]";
	}		
	
}
