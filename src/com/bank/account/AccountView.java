package com.bank.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bank.User;
import com.bank.accounthistory.AccountHistory;
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
			System.out.printf("●%d. %s %s %s Won\n", accountNumber++,
					account.getTypeOfAccount().equals("0") ? "예금" : "적금", account.getAccountNumber(), balance);
		}

		// 선택 화면
		selectAccount(userAccounts);
	}


	// 계좌 선택 후 처리
	private void selectAccount(List<Account> userAccounts) {
		boolean loop = true;
		Scanner sc = new Scanner(System.in);

		System.out.println("================================");
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

			System.out.println("---------------------------------------------");
			showAccountHistoryList();

		}
	}
	
	
	// AccountHistory 클래스 보여줌 
	public void showAccountHistoryList() {

		int accountNumber = 1;
		for (AccountHistory accounthistory : BankDAO.accountHistoryList) {
			String balnace = String.format("%,d", Integer.parseInt(accounthistory.getValue()));
			System.out.printf("●%d. Won %s %s %s %s\n", accountNumber++, balnace, accounthistory.getName(),
					accounthistory.getTypeOfInOUt().equals("0") ? "입금" : "출금",  accounthistory.getTransactionDate());
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
