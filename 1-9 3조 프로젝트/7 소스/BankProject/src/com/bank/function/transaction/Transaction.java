package com.bank.function.transaction;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.bank.account.Account;
import com.bank.accounthistory.AccountHistory;
import com.bank.customer.Customer;
import com.bank.function.signin.Signin;
import com.bank.repository.BankDAO;
import com.bank.User;

public class Transaction {
	public static Map<Integer, Account> map = new HashMap<Integer, Account>();
	
	public static boolean typeOfBank;
	public static Account toAccount;				//보낼 "계좌"
	public static Customer toCustomer;				//보낼 "고객"
	public static Account transactionAccount;		//사용할 "계좌"
	public static String otherAccountNum;			//타행일 때 계좌
	private static String money;					//입력 받은 금액
	
	/*
		1. (저축)계좌 목록을 보여준다									// transaction();
			1-1. "Account"or메뉴 입력받는다.
				1-1-A. "Account"를 transactionAccount에 초기화한다. // selectAccountList();
				1-1-B. 메뉴(로그인 화면)으로 						//
				
		2. 서비스 목록을 보여준다.			//showServiceList();
			2-1. 서비스or메뉴 입력받는다.		//selectServiceList();
				2-1-A. 계좌이체		// transfer();
				2-1-B. 자동이체		// autotransfer();
				2-1-C. 이전 메뉴 > 	1. transaction();
		
		3A. transfer();
			-1. "AccountNumber" 입력받는다.
				-1. 12자리 숫자 검사 후 당행/타행 구분	//typeOfBank 초기화
			-2. 
		
	*/
	
	public static void transaction() {
		
		TransactionService.pickNormalAccount();
		
		boolean check = false; 
		check =	TransactionService.checkAccount();
		
		if (check) {
			TransactionView.showNormalAccountList();
			selectAccountList();
			return;
		}
		
	}
	
	static void selectAccountList() {
		int num = -1;
		
		Scanner scan = new Scanner(System.in);
		num = TransactionService.checkNextInt(scan);
		
		if (num <= map.size() && num > 0) {
			transactionAccount = map.get(num);
			TransactionView.showServiceList();
			selectServiceList();
		} else if(num == 0) {
			Signin.customerMenu(scan);
		} else {
			System.out.println("잘못된 번호를 입력하였습니다.");
			TransactionView.pause();
			transaction();
		}
	}
	
	public static void selectServiceList() {
		int num = -1;
		
		Scanner scan = new Scanner(System.in);
		num = TransactionService.checkNextInt(scan);
		
		if (num <= 2 && num >= 0) {
			
			if (num == 0) {
				transactionAccount = null;
				transaction();
				return;
			}
			if (num == 1) {
				transfer();
				
			}
			if (num == 2) {
				AutoTransfer.autoTransfer();
				
			}
			
		} else {
			System.out.println("잘못된 번호를 입력하였습니다.");
			TransactionView.pause();
			TransactionView.showServiceList();
			selectServiceList();
			return;
		}
		
	}

	public static void transfer() {
		
		Scanner scan = new Scanner(System.in);
		String accountNum = null;
		String money = null;
		
		accountNum = TransactionService.checkAccountNum(scan);
		
		if (accountNum != null) {
			
			searchAccount(accountNum); 	// 당행&타행 결정 
										// 당행일 때 정보들 얻어오기
			
			money = TransactionService.checkMoney(scan);	//숫자 받고 & 잔액확인
			
		} else {
			TransactionView.showServiceList();
			selectServiceList();
		}
		
		if (money != null) {
			moveMoney(money);
			
		} 
		
	}

	private static void moveMoney(String inputMoney) {
		
		TransactionView.showTransferInfo(inputMoney);
		
		Scanner scan = new Scanner(System.in);
		
		String input = TransactionService.checkYN(scan);
		
		if (input.toLowerCase().equals("y")) {
			money = inputMoney;
			
			if (typeOfBank) {	//당행이체 출금,입금 동시 발생
				moneyOut();
				moneyIn();
				addAccountHistoryOut();
				addAccountHistoryIn();
				BankDAO.save();
				TransactionView.pause();
				transaction();
				
			} else {	//타행이체 출금만 발생
				moneyOut();
				addAccountHistoryOut();
				BankDAO.save();
				TransactionView.pause();
				transaction();
			}
			
		} else if (input.toLowerCase().equals("n")){
			System.out.println("이체를 취소하였습니다.");
			TransactionView.pause();
			TransactionView.showServiceList();
			selectServiceList();
		}
	}

	public static void searchAccount(String accountNum) {
		for (Account a : BankDAO.accountList) {
			if (a.getAccountNumber().replace("-", "").equals(accountNum)) { // 당행
				typeOfBank = true;
				toAccount = a; 	// 당행일 때 계좌 정보 얻어오기
					for (Customer c : BankDAO.customertList) {
						if (c.getNo().equals(a.getCusutomerNo())) {
							toCustomer = c;	//당행일 때 고객정보 얻어오기
						}
					}
				return;
			} 
		} 			
		//타행
		typeOfBank = false;
		toAccount = null;
		otherAccountNum = accountNum;
	}

	private static void moneyIn() {
		toAccount.setBalance(String.format("%.0f", Double.parseDouble(toAccount.getBalance()) + Double.parseDouble(money)));
		
	}

	private static void moneyOut() {
		
		transactionAccount.setBalance(String.format("%.0f", Double.parseDouble(transactionAccount.getBalance()) - Double.parseDouble(money)));
		
		System.out.println("송금이 정상적으로 완료되었습니다.");
		System.out.printf("거래 후 잔액 %,.0f원\r\n"
							, Double.parseDouble(transactionAccount.getBalance()));
	}

	private static void addAccountHistoryIn() {
		
		String ahNo = "";
		
		for (AccountHistory ahno : BankDAO.accountHistoryList) {
			ahNo = Integer.parseInt(ahno.getNo()) + 1 + "";
		}
		
		Calendar now = Calendar.getInstance();
		String date = String.format("%tF %tT", now, now);
		
		AccountHistory ahIn = new AccountHistory(
				ahNo
				, date
				, money
				, "0"
				, toAccount.getNo()
				, User.getUser().getName());
				
		BankDAO.accountHistoryList.add(ahIn);
		
	}

	private static void addAccountHistoryOut() {
		
		String ahNo = "";
		
		for (AccountHistory ahno : BankDAO.accountHistoryList) {
			ahNo = Integer.parseInt(ahno.getNo()) + 1 + "";
		}
		
		Calendar now = Calendar.getInstance();
		String date = String.format("%tF %tT", now, now);
		
		AccountHistory ahOut = new AccountHistory(
						ahNo
						, date
						, money
						, "1"
						, transactionAccount.getNo()
						, typeOfBank ? toCustomer.getName() : "타행" );
		
		BankDAO.accountHistoryList.add(ahOut);
	}
}
