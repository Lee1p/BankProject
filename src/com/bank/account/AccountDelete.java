package com.bank.account;

import com.bank.User;
import com.bank.repository.BankDAO;
import com.bank.util.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountDelete {

    // 계좌 해지 메뉴
    public void accountDelete() {
        String loggedInUserNo = User.getUser().getNo();
        List<Account> userAccounts = getAccountsByUserNo(loggedInUserNo);

        if (userAccounts.isEmpty()) {
            System.out.println("계좌가 없습니다.");
            return;
        }
        System.out.println("==============계좌 목록==============");
        // 계좌 목록 출력
        int accountNumber = 1;
        for (Account account : userAccounts) {
            String balance = String.format("%,d", Integer.parseInt(account.getBalance()));
            System.out.printf("●%d. %s %s %s Won\n", accountNumber++, 
                account.getTypeOfAccount().equals("0") ? "예금" : "저축", 
                account.getAccountNumber(), balance);
        }

        // 계좌 선택 후 해지 여부 확인
        selectAccountForDelete(userAccounts);
    }

    // 계좌 해지 선택 처리
    private void selectAccountForDelete(List<Account> userAccounts) {
        boolean loop = true;
        Scanner sc = new Scanner(System.in);

        while (loop) {
        	System.out.println("=====================================");
            System.out.print("해지할 계좌를 선택(0.이전):");
            String accountChoice = sc.nextLine();
            
            if(accountChoice.equals("0")) {
            	System.out.println("계좌 해지 취소");
            	System.out.println();
            	loop = false;
            	break;
            }

            // 계좌 번호가 유효한지 확인
            if (!InputValidator.isValidOption(accountChoice, 1, userAccounts.size())) {
                System.out.println("잘못된 입력입니다. 계좌 번호를 다시 입력해주세요.");
                continue; // 잘못된 입력이면 계좌 번호를 다시 묻기
            }

            // 선택된 계좌 정보 표시
            Account selectedAccount = userAccounts.get(Integer.parseInt(accountChoice) - 1);
            String balance = String.format("%,d", Integer.parseInt(selectedAccount.getBalance()));
            System.out.printf("선택하신 계좌: %s %s %s Won\n", 
                selectedAccount.getTypeOfAccount().equals("0") ? "일반" : "저축", 
                selectedAccount.getAccountNumber(), balance);
            

            // 해지 여부 묻기
            boolean confirmLoop = true;  // 해지 여부에 대한 질문은 별도의 루프에서 처리
            while (confirmLoop) {
                System.out.print("해지하시겠습니까? (Y/N): ");
                String confirmDelete = sc.nextLine();

                if (confirmDelete.equalsIgnoreCase("Y")) {
                    // 계좌 해지 처리
                    BankDAO.accountList.remove(selectedAccount);  // 계좌 삭제
                    boolean savingAccountDeleted = BankDAO.savingAccountList.removeIf(
                    	    savingAccount -> savingAccount.getNo().equals(selectedAccount.getCusutomerNo())
                    	);	

                    	try {
                    	    BankDAO.save();  // 변경된 내용을 파일에 저장
                    	    if (savingAccountDeleted) {
                    	        System.out.println("계좌와 연관된 저축 계좌가 함께 해지되었습니다.");
                    	    } else {
                    	        System.out.println("계좌가 해지되었습니다.");
                    	    }
                    	} catch (Exception e) {
                    	    e.printStackTrace();
                    	    System.out.println("계좌 해지 처리 중 오류가 발생했습니다.");
                    	}
                    loop = false;
                    confirmLoop = false;  // 계좌 해지 완료 후 루프 종료
                } else if (confirmDelete.equalsIgnoreCase("N")) {
                    System.out.println("계좌 해지가 취소되었습니다.");
                    loop = false;
                    confirmLoop = false;  // 계좌 해지 취소 후 루프 종료
                } else {
                    System.out.println("잘못된 입력입니다. Y 또는 N을 입력해주세요.");
                    continue;  // 잘못된 입력이면 해지 여부 질문을 다시 묻기
                }
            }
        }
    }

    // 로그인된 사용자 번호에 해당하는 계좌만 반환하는 메서드
    private List<Account> getAccountsByUserNo(String userNo) {
        List<Account> userAccounts = new ArrayList<>();

        for (Account account : BankDAO.accountList) {
            if (account.getCusutomerNo().equals(userNo)) {
                userAccounts.add(account);
            }
        }

        return userAccounts;
    }
}
