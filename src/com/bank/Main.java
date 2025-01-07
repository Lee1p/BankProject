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
		
		//계좌
		AccountSelect.Accountdisplay();
		
		
		BankDAO.save();
		
	}

	
		
	
}
