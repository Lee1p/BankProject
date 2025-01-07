package com.bank.savingaccount;

//11●12●5
//계좌고유번호 ● 가입개월수 ●  이자율

public class SavingAccount {
	
	private String no;
	private String month;
	private String interest;
	
	public SavingAccount(String no, String month, String interest) {
		super();
		this.no = no;
		this.month = month;
		this.interest = interest;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SavingAccount [no=");
		builder.append(no);
		builder.append(", month=");
		builder.append(month);
		builder.append(", interest=");
		builder.append(interest);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
}
