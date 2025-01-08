package com.bank.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bank.User;
import com.bank.repository.BankDAO;
import com.bank.util.InputValidator;

public class AccountView {

    // 계좌 목록 출력 및 선택
    public void showAccountList() {
        // 로그인된 회원의 번호
        String loggedInUserNo = User.getUser().getNo();

        // 계좌 목록 가져오기
        List<Account> userAccounts = getAccountsByUserNo(loggedInUserNo);

        // 계좌가 없다면
        if (userAccounts.isEmpty()) {
            System.out.println("계좌가 없습니다.");
            return;
        }
        
        System.out.println("===============계좌 조회===========");
        // 계좌 목록 출력
        int accountNumber = 1;
        for (Account account : userAccounts) {
            String balance = String.format("%,d", Integer.parseInt(account.getBalance())); // 잔액 자릿수 맞추기
            System.out.printf("●%d. %s %s %s Won\n", accountNumber++, account.getTypeOfAccount().equals("0") ? "예금" : "저축", account.getAccountNumber(), balance);
        }

        // 선택 화면
        selectAccount(userAccounts);
    }

    // 계좌 선택 후 처리
    private void selectAccount(List<Account> userAccounts) {
        boolean loop = true;
        Scanner sc = new Scanner(System.in);

        while (loop) {
            System.out.println("================================");
            System.out.print("1. 선택(0.이전): ");
            String viewSelect = sc.nextLine();

            if (!InputValidator.isValidOption(viewSelect, 0, 1)) {
                System.out.println("잘못된 입력입니다. 1.선택 또는 0.이전을 입력해주세요.");
                continue;
            }

            if (viewSelect.equals("1")) {
                System.out.println("계좌 선택 완료");
                // 이후 추가 기능 처리 (입출금 등)
                loop = false;
            } else if (viewSelect.equals("0")) {
                loop = false;
            }
        }
    }

    // 로그인된 사용자 번호에 해당하는 계좌만 반환하는 메서드
    private List<Account> getAccountsByUserNo(String userNo) {
        List<Account> userAccounts = new ArrayList<>();
        
        // BankDAO의 accountList를 순회하며 로그인된 회원의 계좌만 필터링
        for (Account account : BankDAO.accountList) {
            if (account.getCusutomerNo().equals(userNo)) {
                userAccounts.add(account);
            }
        }

        return userAccounts;
    }
}
