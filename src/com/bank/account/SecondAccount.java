package com.bank.account;

import java.util.Scanner;

public class SecondAccount {
	 public static void secondAccount() {
		 
		Scanner sc = new Scanner(System.in);
		
		 boolean loop = true;
		 
		 while (loop) {
			 System.out.println("2.적금 통장 개설");
			 System.out.println("------------------");
			 System.out.println("●1. 12개월 5%");
			 System.out.println("●2. 24개월 10%");
			 System.out.println("●3. 36개월 15%");
			 System.out.print("가입 상품 선택:");
			 
			 String accountInput = sc.nextLine();
			  // 유효성 검사 코드
			 if (!InputValidator.isValidOption(accountInput, 0, 3)) { // 입력 검증
	                System.out.println("잘못된 입력입니다. 0~3 사이의 숫자를 입력해주세요.");
	                continue;
	            }
	         
			 
			 
			 
		 }
		 
	 }
}
