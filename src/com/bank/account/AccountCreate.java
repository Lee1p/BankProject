package com.bank.account;

import java.util.Scanner;

import com.bank.customer.Customer;
import com.bank.repository.BankDAO;
import com.bank.util.InputValidator;

public class AccountCreate {
	 public static void createAccount() {
		 
		 
		 Customer customer = BankDAO.customertList.get(2);
		 
		 Scanner sc = new  Scanner(System.in);
		 
		 boolean loop = true;
		 
		 while(loop) {
			 
			 System.out.println("▶ 계좌 개설 ◀");
			 System.out.println();
			 
			 System.out.println("1.예금 통장 개설");
			 System.out.println("2.적금 통장 개설");
			 System.out.println("0.이전");
			 System.out.print("선택:");
			 
			 String accountInput = sc.nextLine();
			 
			 if (!InputValidator.isValidOption(accountInput, 0, 2)) { // 입력 검증
	                System.out.println("잘못된 입력입니다. 0~2 사이의 숫자를 입력해주세요.");
	                continue;
	            }
			 
			 switch (accountInput) {
             case "1":
             	 System.out.println();
                 FirstAccount.firstAccount(customer);// 예금 통장 개설 로직 호출 
                 break;
             case "2":
             	System.out.println();
             	SecondAccount.secondAccount();//적금 통장 개설 로직 호출
             	break;
             case "0":
             	System.out.println();
                 System.out.println("첫 메뉴로 돌아갑니다.");
                 loop = false; // 이전 메뉴로 돌아가기
                 break;
         }
			 
		 } 
	 }
}


