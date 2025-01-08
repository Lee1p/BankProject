package com.bank.account;

import com.bank.util.InputValidator;

import java.util.Scanner;

public class AccountCreate {



    // 계좌 개설 메서드
    public void createAccount() {
        Scanner sc = new Scanner(System.in);

        boolean loop = true;

        while (loop) {
            System.out.println("   ▼ 계좌 개설 ▼");
            System.out.println("1. 일반 통장 개설");
            System.out.println("2. 적금 통장 개설");
            System.out.println("0. 이전");
            System.out.println("====================");
            System.out.print("선택: ");

            String accountInput = sc.nextLine();

            // 입력 검증
            if (!InputValidator.isValidOption(accountInput, 0, 2)) {
                System.out.println("잘못된 입력입니다. 0~2 사이의 숫자를 입력해주세요.");
                continue;
            }

            // 선택에 따른 동작 수행
            switch (accountInput) {
                case "1":
                    System.out.println();
                    // DepositAccount 객체 생성 후 메서드 호출
                    DepositAccount depositAccount = new DepositAccount();
                    depositAccount.depositAccount();
                    break;
                case "2":
                    System.out.println();
                    // FixedDepositAccount 객체 생성 후 메서드 호출
                    FixedDepositAccount fixedDepositAccount = new FixedDepositAccount();
                    fixedDepositAccount.fixedDepositAccount();
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



