package com.bank;

import java.util.Scanner;

import com.bank.account.Account;
import com.bank.accounthistory.AccountHistory;
import com.bank.card.Card;
import com.bank.countrymoneylist.CountryMoneyList;
import com.bank.customer.Customer;
import com.bank.repository.BankDAO;
import com.bank.savingaccount.SavingAccount;
import com.bank.function.transaction.Transaction;
import com.bank.function.card.CardMain;
import com.bank.function.countrymoney.MyContryMoney;
import com.bank.function.mypage.MyPage;
import com.bank.function.signin.Signin;

public class Main {

	public static void main(String[] args) {
		
		BankDAO.load();
		
		Signin.mainMenu();
	
//		User user = new User();
//		
//		for (Customer c : BankDAO.customertList) {
//			if (c.getNo().equals("1")) {
//				user.setUser(c);
//			}
//		}
		
//		CardMain.cardHome();
		
		
		
		
		BankDAO.save();
	}
}
