package com.bank;

import java.util.List;

import com.bank.account.Account;
import com.bank.countrymoneylist.CountryMoneyList;
import com.bank.customer.Customer;

public class User {

	private static Customer user;

	public static Customer getUser() {
		return user;
	}

	public static void setUser(Customer user) {
		User.user = user;
	}
	
}
