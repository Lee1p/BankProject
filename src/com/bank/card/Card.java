package com.bank.card;

//1●1234-4356-1234-1234●1234●1●0
//고유번호 ● 카드번호 ● 비밀번호 ● 계좌고유번호 ● 연회비

public class Card {
	
	private String no;
	private String cardNumber;
	private String password;
	private String accountNo;
	private String annualFee;
	
	public Card(String no, String cardNumber, String password, String accountNo, String annualFee) {
		super();
		this.no = no;
		this.cardNumber = cardNumber;
		this.password = password;
		this.accountNo = accountNo;
		this.annualFee = annualFee;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAnnualFee() {
		return annualFee;
	}

	public void setAnnualFee(String annualFee) {
		this.annualFee = annualFee;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Card [no=");
		builder.append(no);
		builder.append(", cardNumber=");
		builder.append(cardNumber);
		builder.append(", password=");
		builder.append(password);
		builder.append(", accountNo=");
		builder.append(accountNo);
		builder.append(", annualFee=");
		builder.append(annualFee);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
