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
	private String question;
	private String answer;
	
	public Question(long id) {
		this.id = id;
	}
	
	public Question(long id, String question, String answer) {
		this.id = id;
		this.question = question;
		this.answer = answer;
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
		return "Question [id=" + id + ", question=" + question + ", answer=" + answer + "]";
	}	
}
