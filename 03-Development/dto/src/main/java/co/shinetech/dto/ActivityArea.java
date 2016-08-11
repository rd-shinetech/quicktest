/**
 * 
 */
package co.shinetech.dto;

/**
 * Enum with the set for activity area.
 * @author Ricardo
 * @since 2016-08
 */
public enum ActivityArea {
	JAVAOCA("Java OCA"), JAVAOCP("Java OCP"), LINUX("Linux");
	private String area;

	private ActivityArea(String area) {
		this.area = area;
	}
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}
