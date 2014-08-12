package sample.spring.chapter11.domain;

import java.util.Date;

public class FixedDepositDetails {
	private long id;

	private long depositAmount;

	private String email;

	private Date maturityDate;

	public FixedDepositDetails() {
	}

	public FixedDepositDetails(long id, long depositAmount, Date maturityDate,
			String email) {
		this.id = id;
		this.depositAmount = depositAmount;
		this.maturityDate = maturityDate;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(long depositAmount) {
		System.out.println("depositAmount - setter called");
		this.depositAmount = depositAmount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String toString() {
		return "id :" + id + ", deposit amount : " + depositAmount
				+ ", email : " + email;
	}
}
