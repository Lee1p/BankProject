package com.bank;

import com.bank.account.*;
import com.bank.customer.Customer;
import com.bank.repository.BankDAO;

public class Main {
	public static void main(String[] args) {
		
		BankDAO.load();
		
		
//		  // 계좌 정보 출력
//        for (Account a : BankDAO.accountList) {
//            System.out.println(a);
//        }
//
//        // 고객 정보 출력
//        for (Customer c : BankDAO.customertList) {
//            System.out.println(c);
//        }
//		
		
//		for (Customer c : BankDAO.)
		
//		Customer customer = null;
		
		//계좌
		for (Customer c : BankDAO.customertList) {
			if (c.getNo().equals("1")) {
				User.setUser(c);
			}
		}
		
		
		//계좌 목록 불러오기
		AccountSelect accountSelect = new AccountSelect();
		accountSelect.accountDisplay();
		
		
		
		
		BankDAO.save();
		
	}	//main
	
} // class


