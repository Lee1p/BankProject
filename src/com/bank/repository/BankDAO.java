package com.bank.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.bank.account.Account;
//import com.bank.accounthistory.AccountHistory;
//import com.bank.card.Card;
//import com.bank.countrymoneylist.CountryMoneyHistory;
//import com.bank.customer.Customer;
//import com.bank.savingaccount.SavingAccount;

public class BankDAO {

	//파일경로
	private final static String account;
	private final static String accounthistory;
	private final static String card;
	private final static String countrymoneyhistory;
	private final static String customer;
	private final static String savingaccount;
	
	//컬렉션(=데이터 파일 대응)
//	public static ArrayList<Customer> customertList;
	public static ArrayList<Account> accountList;
//	public static ArrayList<AccountHistory> accountHistoryList;
//	public static ArrayList<Card> cardList;
//	public static ArrayList<CountryMoneyHistory> countryMoneyHistoryList;
//	public static ArrayList<SavingAccount> savingAccountList;
//	
	static {
		account = ".\\data\\account.txt";
		accounthistory = ".\\data\\accounthistory.txt";
		card = ".\\data\\card.txt";
		countrymoneyhistory = ".\\data\\countrymoneyhistory.txt";
		customer = ".\\data\\customer.txt";
		savingaccount = ".\\data\\savingaccoun.txt";
		
//		customertList = new ArrayList<Customer>();
		accountList = new ArrayList<Account>();
//		accountHistoryList = new ArrayList<AccountHistory>();
//		cardList = new ArrayList<Card>();
//		countryMoneyHistoryList = new ArrayList<CountryMoneyHistory>();
//		savingAccountList = new ArrayList<SavingAccount>();
	}
	
	
	public static void load() {
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(account));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split("●");
				
				Account account = new Account(
								temp[0]
								, temp[1]
								, temp[2]
								, temp[3]
								, temp[4]
								, temp[5]
								, temp[6]
								);
				
				accountList.add(account);
			}
			reader.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void save() {
		
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(account));
			
			//1●110-431-121445●1234●1●2025-01-01●10000000●0
			//식별자 ● 계좌번호 ● 비밀번호 ● 고객고유번호 ● 개설일 ● 잔액 ● 통장종류(0=저축, 1적금) 
			
			for (Account account : accountList) {
				writer.write(String.format("%s●%s●%s●%s\r\n"
								, account.getNo()
								, account.getAccountNumber()
								, account.getPassword()
								, account.getCusutomerNo()
								, account.getOpenDate()
								, account.getBalance()
								, account.getTypeOfAccount()));
			}
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}






















