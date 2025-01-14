package com.bank.account;

import java.util.Scanner;

import com.bank.function.signin.Signin;
import com.bank.util.InputValidator;


/**
 * 계좌 관리 메뉴를 출력하고, 사용자가 선택한 옵션에 따라 적절한 기능을 호출하는 메서드입니다.
 * <p>
 * 이 메서드는 사용자에게 계좌 관련 여러 기능을 선택할 수 있는 메뉴를 제공하고,
 * 사용자가 선택한 옵션에 맞춰 다음 기능을 처리합니다
 * </p>
 * 
 * <p>
 * 선택된 메뉴에 맞는 클래스를 호출하여 기능을 수행합니다.
 * 또한 사용자가 올바른 입력을 하지 않으면 다시 입력을 유도하는 유효성 검사 기능도 포함되어 있습니다.
 * </p>
 * 
 */

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



