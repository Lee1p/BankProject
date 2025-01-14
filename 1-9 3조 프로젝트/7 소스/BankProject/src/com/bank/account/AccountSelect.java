package com.bank.account;

import java.util.Scanner;

import com.bank.function.signin.Signin;
import com.bank.util.InputValidator;

public class AccountSelect {

    // 계좌 관리 메뉴 화면
    public void accountDisplay() {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
        	System.out.println("===================================================");
            System.out.println("1. 일반 통장 개설");
            System.out.println("2. 적금 통장 개설");
            System.out.println("3. 계좌 조회");
            System.out.println("4. 계좌 해지");
            System.out.println("0. 메뉴");
            System.out.println("===================================================");
            System.out.print("선택:");
            

            String accountInput = sc.nextLine();
            

            // 유효성 검사 코드
            if (!InputValidator.isValidOption(accountInput, 0, 4)) { // 입력 검증
                System.out.println("잘못된 입력입니다. 0~4 사이의 숫자를 입력해주세요.");
                continue;
            }

            switch (accountInput) {
                case "1":
                    // 계좌 개설
                    DepositAccount depositAccount =  new DepositAccount();
                    depositAccount.depositAccount();
                    break;
                    
                case "2":
                    // 계좌 조회
                    FixedDepositAccount fixedDepositaccount = new FixedDepositAccount();
                    fixedDepositaccount.fixedDepositAccount();
   
                    break;
                case "3":
                	// 계좌 조회
                	 AccountView accountView = new AccountView(); // 계좌 조회 클래스를 호출
                     accountView.showAccountList(); // 계좌 목록 출력 메서드 호출
                    break;
                case "4":
                    // 계좌 해지
                	AccountDelete accountDelete = new AccountDelete();
                    accountDelete.accountDelete();
                    break;
                   
                case "0":
                	//이전 메뉴로 돌아가기
                	 System.out.println("첫 메뉴로 돌아갑니다.");
                	 Signin.mainMenu();//여기에 메인페이지를 호출하는 클래스를 작성
                     loop = false;
                     break;
            }
        }

        
    }  // accountDisplay

}  // AccountSelect



