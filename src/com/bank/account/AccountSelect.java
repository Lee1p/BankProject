package com.bank.account;

import java.util.Scanner;

import com.bank.util.InputValidator;

public class AccountSelect {
    public static void Accountdisplay() {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("1. 계좌 개설");
            System.out.println("2. 계좌 조회");
            System.out.println("3. 계좌 해지");
            System.out.println("0. 이전으로");
            System.out.print("선택:");

            String accountInput = sc.nextLine();

            // 유효성 검사 코드
            if (!InputValidator.isValidOption(accountInput, 0, 3)) { // 입력 검증
                System.out.println("잘못된 입력입니다. 0~3 사이의 숫자를 입력해주세요.");
                continue;
            }
            switch (accountInput) {
                case "1":
                	 
                	AccountCreate.createAccount(); // 계좌 개설 로직 호출
                    // 계좌 조회 로직 호출
                    break;
                case "2":
                    System.out.println("계좌 조회");
                    break;
                case "3":
                	System.out.println();
                    System.out.println("계좌 해지");
                    // 계좌 해지 로직 호출
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

