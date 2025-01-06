package com.bank.account;

import java.util.Scanner;

public class AccountCreate {
	 public static void createAccount() {
		 
		 Scanner sc = new  Scanner(System.in);
		 
		 boolean loop = true;
		 
		 while(loop) {
			 
			 System.out.println("1.저축 통장 개설");
			 System.out.println("2.적금 통장 개설");
			 System.out.println("0.이전");
			 System.out.print("선택:");
			 
			 String accountInput = sc.nextLine();
			 
			 if (!accountInput.matches("[0-2]")) { // 입력이 1~2 또는 0인 경우
	                System.out.println("잘못된 입력입니다. 0~2 사이의 숫자를 입력해주세요.");
	                continue; // 다시 메뉴를 출력하고 입력 받음
	            }
			 
			 switch (accountInput) {
             case "1":
             	 System.out.println();
                 SavingAccount.savingAccount();// 저축 통장 개설 로직 호출 
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


