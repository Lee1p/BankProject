package com.bank.customer;

import java.util.ArrayList;
import java.util.List;

import com.bank.account.Account;
import com.bank.countrymoneylist.CountryMoneyList;

//1●홍길동●hgd2352●rlfehd11●010-1211-2352●2023-01-01
//고유번호 ● 이름 ● 아이디 ● 비밀번호 ● 전화번호 ● 가입일

public class Customer {
	
	private String no;
	private String name;
	private String id;
	private String password;
	private String phoneNumber;
	private String openDate; 
	
	//외환 목록
	private List<CountryMoneyList> customerCountryMoneyListLlist;
	//계좌 목록
	private List<Account> customerAccountList;
	

	public Customer(String no, String name, String id, String password, String phoneNumber, String openDate,
			List<Account> customerAccountList, List<CountryMoneyList> customerCountryMoneyHisotoryLlist) {
		super();
		this.no = no;
		this.name = name;
		this.id = id;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.openDate = openDate;
		this.customerCountryMoneyListLlist = new ArrayList<CountryMoneyList>();
		this.customerAccountList = new ArrayList<Account>();
	}

	public List<Account> getAlist() {
		return customerAccountList;
	}

	public void setAlist(List<Account> alist) {
		this.customerAccountList = alist;
	}

	public List<CountryMoneyList> getClist() {
		return customerCountryMoneyListLlist;
	}

	public void setClist(List<CountryMoneyList> clist) {
		this.customerCountryMoneyListLlist = clist;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [no=");
		builder.append(no);
		builder.append(", name=");
		builder.append(name);
		builder.append(", id=");
		builder.append(id);
		builder.append(", password=");
		builder.append(password);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", openDate=");
		builder.append(openDate);
		builder.append(", alist=");
		builder.append(customerAccountList);
		builder.append(", clist=");
		builder.append(customerCountryMoneyListLlist);
		builder.append("]");
		return builder.toString();
	}

	
	
	
	
}
