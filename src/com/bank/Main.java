package com.bank;

import com.bank.account.*;
import com.bank.repository.BankDAO;

public class Main {
	public static void main(String[] args) {
		
//		BankDAO.load();
		
		
		for (Account a : BankDAO.accountList) {
			System.out.println(a);
		}
		
		
		
		//계좌
		AccountSelect.Accountdisplay();
		
		
		BankDAO.save();
		
	}

	
		
	
}
