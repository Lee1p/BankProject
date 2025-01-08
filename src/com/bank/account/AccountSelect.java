package com.bank.account;

import java.util.Scanner;

import com.bank.util.InputValidator;

public class AccountSelect {

    // 계좌 관리 메뉴 화면
    public void accountDisplay() {
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
                    // 계좌 개설
                    AccountCreate accountCreate = new AccountCreate();  // 객체 생성
                    accountCreate.createAccount();  // 객체 메서드 호출
                    break;
                case "2":
                    // 계좌 조회
                    System.out.println("계좌 조회");
                    // 계좌 조회 로직을 추가 해야함
   
                    break;
                case "3":
                    // 계좌 해지
                    System.out.println("계좌 해지");
                    // 계좌 해지 로직을 추가하려면 객체를 생성하여 메서드를 호출
                    break;
                case "0":
                    // 이전 메뉴로 돌아가기
                    System.out.println("첫 메뉴로 돌아갑니다.");
                    loop = false;
                    break;
            }
        }

        sc.close();
    }  // accountDisplay

}  // AccountSelect



