/**
 * 
 */
package co.shinetech.dto;

/**
 * DTO class for question.
 * @author Ricardo
 * @since 2016-08
 */
@SuppressWarnings("serial")
public class Question implements Domain {
	private long id;
	private QuestionType type;
	private ActivityArea activityArea;
	private String question;
	private String answer;
	
	public Question(long id) {
		this.id = id;
	}
	
	// getters and setters
	public long getPk() {
		return id;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;		
	}
	
	/**
	 * @return the type
	 */
	public QuestionType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(QuestionType type) {
		this.type = type;
	}

	/**
	 * @return the activityArea
	 */
	public ActivityArea getActivityArea() {
		return activityArea;
	}

	/**
	 * @param activityArea the activityArea to set
	 */
	public void setActivityArea(ActivityArea activityArea) {
		this.activityArea = activityArea;
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
		Question other = (Question) obj;
		if (id != other.id)
			return false;
		return true;
	}
	// toString

	@Override
	public String toString() {
		return "Question [id=" + id + ", question=" + question + ", answer=" + answer + ", type=" + type
				+ ", activityArea=" + activityArea + "]";
	}
}
