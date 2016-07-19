package co.shinetech.dto;
/**
 * @author Ricardo
 *
 */
public class ResultItem implements Domain {
	
	// Attributes
	String result;
	
	public ResultItem(String result) {
		this.result = result;
	}
	
	public long getPk() {
		// TODO Auto-generated method stub
	return 0;
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
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
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
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		return true;
	}
	
	// toString
	@Override
	public String toString() {
		return "ResultItem [result=" + result + "]";
	}	
	
}
