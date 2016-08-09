/**
 * ActivityType.java
 */
package co.shinetech.dto;

/**
 * Enum with the set for activity Type.
 * @author Robin
 * @since 2016-08
 */
public enum ActivityType {
	EXAME("Exame"), EXERCISE("Exercício");
	private String type;
	
	private ActivityType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}