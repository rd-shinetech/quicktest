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
	
	
	
}
