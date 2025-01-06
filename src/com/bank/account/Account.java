package com.bank.account;

//1●110-431-121445●1234●1●2025-01-01●10000000●0
//식별자 ● 계좌번호 ● 비밀번호 ● 고객고유번호 ● 개설일 ● 잔액 ● 통장종류(0=저축, 1적금) 

public class Account {

	private String no;
	private String accountNumber;
	private String password;
	private String cusutomerNo;
	private String openDate;
	private String balance; // 잔액
	private String typeOfAccount;
	
	public Account(String no, String accountNumber, String password, String cusutomerNo, String openDate,
			String balance, String typeOfAccount) {
		super();
		this.no = no;
		this.accountNumber = accountNumber;
		this.password = password;
		this.cusutomerNo = cusutomerNo;
		this.openDate = openDate;
		this.balance = balance;
		this.typeOfAccount = typeOfAccount;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCusutomerNo() {
		return cusutomerNo;
	}

	public void setCusutomerNo(String cusutomerNo) {
		this.cusutomerNo = cusutomerNo;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getTypeOfAccount() {
		return typeOfAccount;
	}

	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [no=");
		builder.append(no);
		builder.append(", accountNumber=");
		builder.append(accountNumber);
		builder.append(", password=");
		builder.append(password);
		builder.append(", cusutomerNo=");
		builder.append(cusutomerNo);
		builder.append(", openDate=");
		builder.append(openDate);
		builder.append(", balance=");
		builder.append(balance);
		builder.append(", typeOfAccount=");
		builder.append(typeOfAccount);
		builder.append("]");
		return builder.toString();
	}
	
	
}
