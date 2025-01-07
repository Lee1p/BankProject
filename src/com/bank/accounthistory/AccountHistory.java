package com.bank.accounthistory;

//1●2025-01-01●10000●1●1●김개똥
//고유번호 ● 거래일 ● 액수 ●  입출금(입금=0,출금=1) ● 계좌고유번호 ● 거래자명(대상)

public class AccountHistory {
	
	private String no;
	private String transactionDate;
	private String value;
	private String typeOfInOUt;
	private String accountNo;
	private String name;
	
	public AccountHistory(String no, String transactionDate, String value, String typeOfInOUt, String accountNo,
			String name) {
		super();
		this.no = no;
		this.transactionDate = transactionDate;
		this.value = value;
		this.typeOfInOUt = typeOfInOUt;
		this.accountNo = accountNo;
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTypeOfInOUt() {
		return typeOfInOUt;
	}

	public void setTypeOfInOUt(String typeOfInOUt) {
		this.typeOfInOUt = typeOfInOUt;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountHistory [no=");
		builder.append(no);
		builder.append(", transactionDate=");
		builder.append(transactionDate);
		builder.append(", value=");
		builder.append(value);
		builder.append(", typeOfInOUt=");
		builder.append(typeOfInOUt);
		builder.append(", accountNo=");
		builder.append(accountNo);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
	
}
