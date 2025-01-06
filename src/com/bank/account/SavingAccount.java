package com.bank.account;

import java.util.Scanner;

public class SavingAccount {

    public static void savingAccount() {
        Scanner sc = new Scanner(System.in);

        boolean loop = true;

        while (loop) {
            System.out.println("1.저축 통장 개설");
            System.out.println("------------------");
            System.out.println("계좌에 사용하실 비밀번호 숫자 4자리를 입력해주세요.(로그인 시 비밀번호와 다릅니다.)");
            System.out.print("비밀번호:");

            String passwordInput = sc.nextLine();

            // 비밀번호 유효성검사 정규식표현 숫자4자리인지 확인
            if (passwordInput.matches("^\\d{4}$")) {
                while (true) {  // 생성 여부 입력을 반복하는 새로운 루프 추가
                    System.out.println("==================생성(Y/N)");
                    System.out.print("선택:");
                    String makeAccount = sc.nextLine();

                    // Y 또는 N만 입력 받음
                    if (makeAccount.equalsIgnoreCase("Y")) {
                    	System.out.println();
                        System.out.println("%s님의 계좌가 개설이 완료되었습니다.");
                        System.out.println();
                        System.out.println("계좌번호는 땡떙땡-입니다.");
                        System.out.println();
                        break;  // 계좌 개설 완료 후 루프 종료
                    } else if (makeAccount.equalsIgnoreCase("N")) {
                        System.out.println("계좌 개설을 취소합니다.");
                        break;  // 계좌 개설 취소 후 루프 종료
                    } else {
                        System.out.println("잘못된 입력입니다. Y 또는 N만 입력해주세요.");
                        // 선택을 잘못 입력했을 때만 다시 "생성(Y/N)" 창을 반복하도록
                        continue;  // 비밀번호 입력으로 돌아가지 않도록 continue 추가
                    }
                }
                break;  // 비밀번호가 유효하면 전체 루프 종료
                
            } else {
                System.out.println("잘못된 입력입니다. 4자리 숫자만 입력해주세요.");
                // 비밀번호가 잘못되었을 경우 다시 비밀번호 입력을 받음
                continue;
            }
        }
    }
}

