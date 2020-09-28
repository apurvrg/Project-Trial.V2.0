package ProjectTrial.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "java")
public class Java {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="question")
	private String question;
	
	@Column(name="optA")
	private String optA;	

	@Column(name="optB")
	private String optB;
	
	@Column(name="optC")
	private String optC;
	
	@Column(name="optD")
	private String optD;
	
	@Column(name="correct_ans")
	private String correct_ans;

	public Java() {
		
	}
	
	public Java(int id, String question, String optA, String optB, String optC, String optD, String correct_ans) {
		super();
		this.id = id;
		this.question = question;
		this.optA = optA;
		this.optB = optB;
		this.optC = optC;
		this.optD = optD;
		this.correct_ans = correct_ans;
	}

	public Java(String question, String optA, String optB, String optC, String optD, String correct_ans) {
		super();
		this.question = question;
		this.optA = optA;
		this.optB = optB;
		this.optC = optC;
		this.optD = optD;
		this.correct_ans = correct_ans;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOptA() {
		return optA;
	}

	public void setOptA(String optA) {
		this.optA = optA;
	}

	public String getOptB() {
		return optB;
	}

	public void setOptB(String optB) {
		this.optB = optB;
	}

	public String getOptC() {
		return optC;
	}

	public void setOptC(String optC) {
		this.optC = optC;
	}

	public String getOptD() {
		return optD;
	}

	public void setOptD(String optD) {
		this.optD = optD;
	}

	public String getCorrect_ans() {
		return correct_ans;
	}

	public void setCorrect_ans(String correct_ans) {
		this.correct_ans = correct_ans;
	}

	@Override
	public String toString() {
		return "Java [id=" + id + ", question=" + question + ", optA=" + optA + ", optB=" + optB + ", optC=" + optC
				+ ", optD=" + optD + ", correct_ans=" + correct_ans + "]";
	}

}
