package com.bank.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.bank.account.Account;
import com.bank.accounthistory.AccountHistory;
import com.bank.card.Card;
import com.bank.card.CardProduct;
import com.bank.countrymoneylist.CountryMoneyList;
import com.bank.customer.Customer;
import com.bank.savingaccount.SavingAccount;

public class BankDAO {

	//파일경로
	private final static String account;
	private final static String accounthistory;
	private final static String card;
	private final static String cardproduct;
	private final static String countrymoneyhistory;
	private final static String customer;
	private final static String savingaccount;
	
	//컬렉션(=데이터 파일 대응)
	public static ArrayList<Customer> customertList;
	public static ArrayList<Account> accountList;
	public static ArrayList<AccountHistory> accountHistoryList;
	public static ArrayList<Card> cardList;
	public static ArrayList<CardProduct> cardProductList;
	public static ArrayList<CountryMoneyList> countryMoneyListList;
	public static ArrayList<SavingAccount> savingAccountList;
	
	static {
		account = ".\\data\\account.txt";
		accounthistory = ".\\data\\accounthistory.txt";
		card = ".\\data\\card.txt";
		cardproduct = ".\\data\\cardproduct.txt";
		countrymoneyhistory = ".\\data\\countrymoneylist.txt";
		customer = ".\\data\\customer.txt";
		savingaccount = ".\\data\\savingaccount.txt";
		
		customertList = new ArrayList<Customer>();
		accountList = new ArrayList<Account>();
		accountHistoryList = new ArrayList<AccountHistory>();
		cardList = new ArrayList<Card>();
		cardProductList = new ArrayList<CardProduct>();
		countryMoneyListList = new ArrayList<CountryMoneyList>();
		savingAccountList = new ArrayList<SavingAccount>();
	}
	
	
	public static void load() {
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(account));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split("●");
				
				Account account = new Account(
								temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], null, null, null);
				
				accountList.add(account);
			}//while
			reader.close();
			// 계좌 객체에 기본 정보 담기
			// 거래내역, 연동카드, 적금통장 목록은 추가적으로 담아야 함
			
			reader = new BufferedReader(new FileReader(customer));
			
			while ((line = reader.readLine()) != null) {
				//1●홍길동●hgd2352●rlfehd11●010-1211-2352●2023-01-01
				//고유번호 ● 이름 ● 아이디 ● 비밀번호 ● 전화번호 ● 가입일
				
				String[] temp = line.split("●");
				
				Customer customer = new Customer(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], null, null);
				
				customertList.add(customer);
			}//while
			reader.close();
			// 고객 객체에 기본 정보 담기
			// 계좌 정보와 외환 보유 목록은 추가적으로 담아야 함
			
			reader = new BufferedReader(new FileReader(countrymoneyhistory));
			while ((line = reader.readLine()) != null) {
				//1●2000●50000●3000●3000
				//고유번호 ● 달러 ● 유로 ● 엔화 ● 위안
				
				String[] temp = line.split("●");
				
				CountryMoneyList countryMoneyHistory = new CountryMoneyList(temp[0], temp[1], temp[2], temp[3], temp[4]);

				countryMoneyListList.add(countryMoneyHistory);
			}//while
			reader.close();
			//외화 보유목록 담기
			//고객에게 외화보유목록 넣어야함
			
			
			reader = new BufferedReader(new FileReader(accounthistory));
			while ((line = reader.readLine()) != null) {
				//1●2025-01-01●10000●1●1●김개똥
				//고유번호 ● 거래일 ● 액수 ●  입출금(입금=0,출금=1) ● 계좌고유번호 ● 거래자명(대상)
				
				String[] temp = line.split("●");
				
				AccountHistory accountHistory = new AccountHistory(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
				
				accountHistoryList.add(accountHistory);
			}//while
			reader.close();
			//계좌거래내역 담기
			//계좌에 거래내역 담아야함
			
			
			reader = new BufferedReader(new FileReader(card));
			while ((line = reader.readLine()) != null) {
				
				//1			● 1234-4356-1234-1234	●1234		●1				●0		● 3000000	●2024-01-01
				//고유번호	● 카드번호				● 비밀번호	● 계좌고유번호	● 연회비	● 월한도		● 개설날자
				
				String[] temp = line.split("●");
				
				
				Card card = new Card(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
				
				cardList.add(card);
			}//while
			reader.close();
			//카드목록 담기
			//계좌에 카드목록 담아야함
			
			reader = new BufferedReader(new FileReader(cardproduct));
			while ((line = reader.readLine()) != null) {
				
				//2●S-클래스 카드●7771●200000●100000000
				//고유번호●카드명●카드번호첫자리●연회비●결제한도
				
				String[] temp = line.split("●");
				
				
				CardProduct cardProduct = new CardProduct(temp[0], temp[1], temp[2], temp[3], temp[4]);
				
				cardProductList.add(cardProduct);
			}//while
			reader.close();
			//카드목록 담기
			//계좌에 카드목록 담아야함
			
			reader = new BufferedReader(new FileReader(savingaccount));
			while ((line = reader.readLine()) != null) {
				
				//11●12●5
				//계좌고유번호 ● 가입개월수 ●  이자율
				
				String[] temp = line.split("●");
				
				SavingAccount savingAccount = new SavingAccount(temp[0], temp[1], temp[2]);
				savingAccountList.add(savingAccount);
			}//while
			reader.close();
			//적금통장 목록 담기
			//계좌에 담아야함
			
			for (Customer customer : customertList) {
				String str = customer.getNo();
				for (CountryMoneyList country : countryMoneyListList ) {
					if (country.getNo().equals(str)) {
					}
				}
				for (Account account : accountList ) {
					if (account.getCusutomerNo().equals(str)) {
						customer.getAlist().add(account);
					}
				}
			}
			
			for (Account account : accountList) {
				String str = account.getNo();
				for (AccountHistory ah : accountHistoryList) {
					if (ah.getAccountNo().equals(str)) {
						account.getAccountAccountHistoryList().add(ah);
					}
				}
				
				for (SavingAccount sa : savingAccountList) {
					if (sa.getNo().equals(str)) {
						account.getAccountSavingAccountList().add(sa);
					}
				}
				
				for (Card c : cardList) {
					if (c.getAccountNo().equals(str)) {
						account.getAccountCardList().add(c);
					}
				}

			}
			
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
				writer.write(String.format("%s●%s●%s●%s●%s●%s●%s\r\n"
								, account.getNo()
								, account.getAccountNumber()
								, account.getPassword()
								, account.getCusutomerNo()
								, account.getOpenDate()
								, account.getBalance()
								, account.getTypeOfAccount()));
			}
			writer.close();
			
			//1●홍길동●hgd2352●rlfehd11●010-1211-2352●2023-01-01
			//고유번호 ● 이름 ● 아이디 ● 비밀번호 ● 전화번호 ● 가입일
			
			writer = new BufferedWriter(new FileWriter(customer));
			for (Customer customer : customertList) {
				writer.write(String.format("%s●%s●%s●%s●%s●%s\r\n"
									, customer.getNo()
									, customer.getName()
									, customer.getId()
									, customer.getPassword()
									, customer.getPhoneNumber()
									, customer.getOpenDate()));
			}
			writer.close();
			
			//1●2025-01-01●10000●1●1●김개똥
			//고유번호 ● 거래일 ● 액수 ●  입출금(입금=0,출금=1) ● 계좌고유번호 ● 거래자명(대상)
			writer = new BufferedWriter(new FileWriter(accounthistory));
			for (AccountHistory ac : accountHistoryList) {
				writer.write(String.format("%s●%s●%s●%s●%s●%s\r\n"
								, ac.getNo()
								, ac.getTransactionDate()
								, ac.getValue()
								, ac.getTypeOfInOUt()
								, ac.getAccountNo()
								, ac.getName()));
			}
			writer.close();
			
			//1			● 1234-4356-1234-1234	●1234		●1				●0		● 3000000	●2024-01-01
			//고유번호	● 카드번호				● 비밀번호	● 계좌고유번호	● 연회비	● 월한도		● 개설날자
			writer = new BufferedWriter(new FileWriter(card));
			for (Card card : cardList) {
				writer.write(String.format("%s●%s●%s●%s●%s●%s●%s\r\n"
						, card.getNo()
						, card.getCardNumber()
						, card.getPassword()
						, card.getAccountNo()
						, card.getAnnualFee()
						, card.getLimit()
						, card.getCreateDay()));
			}
			writer.close();
			
			//2●S-클래스 카드●7771●200000●100000000
			//고유번호●카드명●카드번호첫자리●연회비●결제한도
			writer = new BufferedWriter(new FileWriter(cardproduct));
			for (CardProduct cardproduct : cardProductList) {
				writer.write(String.format("%s●%s●%s●%s●%s\r\n"
						, cardproduct.getNo()
						, cardproduct.getCardName()       
						, cardproduct.getFirstCardNumber()
						, cardproduct.getAnnualFee()  
						, cardproduct.getLimit()));
			}
			writer.close();
			
			//1●2000●50000●3000●3000
			//고유번호 ● 달러 ● 유로 ● 엔화 ● 위안
			writer = new BufferedWriter(new FileWriter(countrymoneyhistory));
			for (CountryMoneyList cm : countryMoneyListList) {
				writer.write(String.format("%s●%s●%s●%s●%s\r\n"
							, cm.getNo()
							, cm.getDollar()
							, cm.getEuro()
							, cm.getYen()
							, cm.getYuan()));
			}
			writer.close();
			
			//11●12●5
			//계좌고유번호 ● 가입개월수 ●  이자율
			writer = new BufferedWriter(new FileWriter(savingaccount));
			for (SavingAccount sa : savingAccountList) {
				writer.write(String.format("%s●%s●%s\r\n"
						, sa.getNo()
						, sa.getMonth()
						, sa.getInterest()));
			}
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
