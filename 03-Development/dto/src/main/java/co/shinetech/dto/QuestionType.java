/*
 * QuestionType.java
 */
package co.shinetech.dto;

/** 
 * Enum with the set of valid question types.
 * @author rdinis
 * @since 2016-09-08
 */
public enum QuestionType {
	MULTIPLE_CHOICE("Múltipla Escolha"), TEXTUAL("Textual");
	private String name;

	/**
	 * @param name
	 */
	QuestionType(String name) {
		this.name = name;
	}
	
	public QuestionType fromValue(int value) {
		QuestionType[] ar = QuestionType.values();
		
		if( value > -1 && value < ar.length )
			return ar[value];
		else
			return QuestionType.MULTIPLE_CHOICE;
	}
	
	public String getName() {
		return this.name;
	}
}