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
	            if (!accountInput.matches("[1-3]")) { // 입력이 1~3 또는 0인 경우
	                System.out.println("잘못된 입력입니다. 1~3 사이의 숫자를 입력해주세요.");
	                continue; // 다시 메뉴를 출력하고 입력 받음
	            }
	         
			 
			 
			 
		 }
		 
	 }
}
