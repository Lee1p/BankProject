package com.bank.account;

import java.util.ArrayList;
import java.util.List;

import com.bank.accounthistory.AccountHistory;
import com.bank.card.Card;
import com.bank.savingaccount.SavingAccount;

//1●110-431-121445●1234●1●2025-01-01●10000000●0
//식별자 ● 계좌번호 ● 비밀번호 ● 고객고유번호 ● 개설일 ● 잔액 ● 통장종류(0=저축, 1적금) 

public class Account {

	private String no; // 계좌 고유번호
	private String accountNumber; // 계좌번호
	private String password; // 비밀번호
	private String cusutomerNo; // 회원고유번호
	private String openDate; // 날짜 
	private String balance; // 잔액
	private String typeOfAccount; // 타입 일반 (0) , 적금(1)
	
	
	//거래내역
	private List<AccountHistory> accountAccountHistoryList;
	//적금통장 구분
	private List<SavingAccount> accountSavingAccountList;
	//카드목록
	private List<Card> accountCardList;
	
	public Account(String no, String accountNumber, String password, String cusutomerNo, String openDate,
			String balance, String typeOfAccount, List<AccountHistory> accountAccountHistoryList,
			List<SavingAccount> accountSavingAccountList, List<Card> accountCardList) {
		super();
		this.no = no;
		this.accountNumber = accountNumber;
		this.password = password;
		this.cusutomerNo = cusutomerNo;
		this.openDate = openDate;
		this.balance = balance;
		this.typeOfAccount = typeOfAccount;
		this.accountAccountHistoryList = new ArrayList<AccountHistory>();
		this.accountSavingAccountList = new ArrayList<SavingAccount>();
		this.accountCardList = new ArrayList<Card>();
	}

	public List<AccountHistory> getAccountAccountHistoryList() {
		return accountAccountHistoryList;
	}

	public void setAccountAccountHistoryList(List<AccountHistory> accountAccountHistoryList) {
		this.accountAccountHistoryList = accountAccountHistoryList;
	}

	public List<SavingAccount> getAccountSavingAccountList() {
		return accountSavingAccountList;
	}

	public void setAccountSavingAccountList(List<SavingAccount> accountSavingAccountList) {
		this.accountSavingAccountList = accountSavingAccountList;
	}

	public List<Card> getAccountCardList() {
		return accountCardList;
	}

	public void setAccountCardList(List<Card> accountCardList) {
		this.accountCardList = accountCardList;
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
		builder.append(", accountAccountHistoryList=");
		builder.append(accountAccountHistoryList);
		builder.append(", accountSavingAccountList=");
		builder.append(accountSavingAccountList);
		builder.append(", accountCardList=");
		builder.append(accountCardList);
		builder.append("]");
		return builder.toString();
	}


	
	
}
