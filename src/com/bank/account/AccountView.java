package com.bank.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bank.User;
import com.bank.accounthistory.AccountHistory;
import com.bank.repository.BankDAO;
import com.bank.util.InputValidator;

/**
 * 계좌 관련  조회 기능을 처리하는 클래스입니다.
 * <p>
 * 사용자가 가진 전체계좌를 조회하고 선택한 후 해당 계좌의 내역을 확인하거나,
 * 입금 내역을 확인할 수 있습니다.
 * </p>
 *  
 * 
 *  @author 이재현 
 * 	@version 1.0
 *  @since 2025. 01. 12
 */
public class AccountView {

    /**
     * 로그인된 사용자의 계좌 목록을 출력하고, 선택할 수 있도록 합니다.
     * <p>
     * 사용자가 계좌를 개설하지않았다면 "계좌가 없습니다."라는 메시지를 출력합니다.
     * </p>
     * 
     *
     * 
     */
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
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
        System.out.println("===================================================");
        System.out.println("            계좌 조회");
        System.out.println("===================================================");
        // 계좌 목록 출력
        int accountNumber = 1;
        for (Account account : userAccounts) {
            String balance = String.format("%,d", Integer.parseInt(account.getBalance())); // 잔액 자릿수 맞추기
            System.out.printf("●%d. %s %s %s Won\n", accountNumber++,
                    account.getTypeOfAccount().equals("0") ? "예금" : "적금", account.getAccountNumber(), balance);
        }

        // 선택 화면
        selectAccount(userAccounts);
    }

    /**
     * 사용자가 선택한 계좌(예금/적금)와 잔액을 보여줍니다. 
     * <p>
     * 그 이후 계좌의 입출금 내역을 보여줍니다.
     * </p>
     * <p>
     * 사용자가 선택한 계좌에 대한 상세 정보를 출력한 후, 해당 계좌의 내역을 보여줍니다.
     * </p>
     * 
     * @param userAccounts 사용자가 보유한 계좌 목록
     */
    public void selectAccount(List<Account> userAccounts) {
        boolean loop = true;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }

        System.out.println("===================================================");
        while (loop) {
            System.out.print("◆계좌 내역 선택(0.이전): ");
            String viewSelect = sc.nextLine();

            if ("0".equals(viewSelect)) {
                loop = false;
                return;
            }

            if (!InputValidator.isValidOption(viewSelect, 1, userAccounts.size())) {
                System.out.println("잘못된 선택입니다. 보유하신 계좌에서 선택해주세요.");
                continue;
            }

            // 선택된 계좌 정보 표시
            Account selectedAccount = userAccounts.get(Integer.parseInt(viewSelect) - 1);
            String balance = String.format("%,d", Integer.parseInt(selectedAccount.getBalance()));
            System.out.printf("선택하신 계좌: %s %s %s Won\n", selectedAccount.getTypeOfAccount().equals("0") ? "일반" : "적금",
                    selectedAccount.getAccountNumber(), balance);
            
            System.out.println("===================================================");
            showAccountHistoryList();
            System.out.println("===================================================");
        }
    }

    /**
     * 전체 계좌의 입출금 내역을 출력합니다.
     * <p>
     * BankDAO에서 관리하는 모든 계좌 내역을 출력합니다.
     * </p>
     */
    public void showAccountHistoryList() {

        int accountNumber = 1;
        for (AccountHistory accounthistory : BankDAO.accountHistoryList) {
            String balance = String.format("%,d", Integer.parseInt(accounthistory.getValue()));
            System.out.printf("●%d. %s Won %s %s %s\n", accountNumber++, balance, accounthistory.getName(),
                    accounthistory.getTypeOfInOUt().equals("0") ? "입금" : "출금",  accounthistory.getTransactionDate());
        }

    }

    /**
     * 입금 내역만 필터링하여 출력합니다.
     * <p>
     * BankDAO에서 관리하는 계좌 내역 중, 입금 내역만 출력합니다.
     * </p>
     */
    public void showAccountDeposit() {
        
        int accountNumber = 1;
        for (AccountHistory accounthistory : BankDAO.accountHistoryList) {
            String balance = String.format("%,d", Integer.parseInt(accounthistory.getValue()));
            String typeOfTransaction = accounthistory.getTypeOfInOUt().equals("0") ? "입금" : accounthistory.getTypeOfInOUt();
            if(accounthistory.getTypeOfInOUt().equals("0")) {
                System.out.printf("●%d. %s Won %s %s %s\n", accountNumber++, balance , accounthistory.getName(),
                        typeOfTransaction, accounthistory.getTransactionDate());
            }
        }
        
    }

    /**
     * 로그인된 사용자의 계좌 목록을 반환합니다.
     * <p>
     * BankDAO의 계좌 목록에서 로그인된 사용자의 계좌만 필터링하여 반환합니다.
     * </p>
     * 
     * @param userNo 로그인된 사용자의 번호
     * @return 해당 사용자가 보유한 계좌 목록
     */
    public List<Account> getAccountsByUserNo(String userNo) {
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
