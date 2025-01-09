package com.bank.account;

import com.bank.User;
import com.bank.repository.BankDAO;

import java.text.DecimalFormat;
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

		displayAccounts(userAccounts);
		handleAccountDeletion(userAccounts);
	}

	// 로그인된 사용자 번호에 해당하는 계좌 반환
	private List<Account> getAccountsByUserNo(String userNo) {
		List<Account> userAccounts = new ArrayList<>();
		for (Account account : BankDAO.accountList) {
			if (account.getCusutomerNo().equals(userNo)) {
				userAccounts.add(account);
			}
		}
		return userAccounts;
	}

	// 계좌 목록 출력
	private void displayAccounts(List<Account> accounts) {
		System.out.println("==============계좌 목록==============");
		int index = 1;
		for (Account account : accounts) {
			String balance = String.format("%,d", Integer.parseInt(account.getBalance()));
			System.out.printf("●%d. %s %s %s 원\n", index++, account.getTypeOfAccount().equals("0") ? "예금" : "적금",
					account.getAccountNumber(), balance);
		}
	}

	// 계좌 해지 처리 (잔액 0일 경우 해지 여부 묻기)
	private void processAccountDeletion(Account account) {
		// 잔액이 0인 경우 계좌 해지 여부 묻기
		if (isBalanceZero(account)) {
			if (confirmAccountDeletion()) {
				deleteAccount(account);
				System.out.println("계좌가 정상 해지되었습니다.");
			} else {
				System.out.println("계좌 해지가 취소되었습니다.");
			}
		} else {
			// 잔액이 남아있으면 송금 처리를 진행
			handleRemainingBalance(account);
		}
	}

	// 계좌 삭제 로직
	private void deleteAccount(Account account) {
		// accountList에서 해당 계좌를 삭제
		BankDAO.accountList.remove(account);

		// savingAccountList에서 해당 계좌를 삭제 (예: 적금 계좌인 경우)
		BankDAO.savingAccountList.removeIf(savingAccount -> savingAccount.getNo().equals(account.getNo()));

		// 변경된 정보를 저장
		BankDAO.save();
	}

	// 계좌 잔액 확인
	private boolean isBalanceZero(Account account) {
		return Integer.parseInt(account.getBalance()) == 0;
	}

	// 계좌 해지 여부 확인
	private boolean confirmAccountDeletion() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("계좌를 해지하시겠습니까? (Y/N): ");
			String input = sc.nextLine().trim().toUpperCase();
			if (input.equals("Y")) {
				return true;
			} else if (input.equals("N")) {
				return false;
			} else {
				System.out.println("잘못된 입력입니다. 'Y' 또는 'N'을 입력해주세요.");
			}
		}
	}

	// 계좌 해지 메뉴 선택 처리
	private void handleAccountDeletion(List<Account> userAccounts) {
		Scanner sc = new Scanner(System.in);
		System.out.print("해지할 계좌 선택(0.이전): ");

		int accountIndex = -1;
		while (accountIndex < 0 || accountIndex > userAccounts.size()) {
			try {
				accountIndex = Integer.parseInt(sc.nextLine());
				if (accountIndex < 0 || accountIndex > userAccounts.size()) {
					System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 입력입니다. 번호를 입력해주세요.");
			}
		}

		// 0을 입력하면 이전 메뉴로 돌아가기
		if (accountIndex == 0) {
			System.out.println("이전 메뉴로 돌아갑니다.");
			return; // 이전 메뉴로 돌아가도록 리턴
		}

		Account selectedAccount = userAccounts.get(accountIndex - 1);
		processAccountDeletion(selectedAccount);
	}

	// 잔액이 남아있는 경우 송금 처리
	private void handleRemainingBalance(Account selectedAccount) {
		Scanner sc = new Scanner(System.in);

		Account transferAccount = null;

		// 계좌번호가 유효할 때까지 반복
		while (true) {
			System.out.print("남은 금액을 송금하실 계좌를 입력해주세요.\n계좌번호(0.이전): ");
			String transferAccountNumber = sc.nextLine(); // 사용자 입력 계좌번호 그대로 사용
			
			if(transferAccountNumber.equals("0")) {
				return;
			}

			// 계좌번호를 통해 계좌 찾기
			transferAccount = findAccountByAccountNumber(transferAccountNumber);

			// 계좌가 없으면 계속해서 입력 받기
			if (transferAccount == null) {
				System.out.println("잘못된 계좌번호입니다. 다시 입력해주세요.");
				continue; // 유효한 계좌가 입력될 때까지 반복
			}

			// 자신에게 송금하려는 경우
			if (transferAccount.getAccountNumber().equals(selectedAccount.getAccountNumber())) {
				System.out.println("=====자신의 계좌로는 송금 못합니다.======");
				return;
			}

			// 송금 금액 입력 반복 처리
			while (true) {
				System.out.print("송금하실 금액을 입력해주세요: ");
				String transferAmount = sc.nextLine();

				try {
					int amount = Integer.parseInt(transferAmount);
					int balance = Integer.parseInt(selectedAccount.getBalance());

					if (amount <= 0) {
						System.out.println("송금 금액은 0보다 커야 합니다.");
						continue;
					}

					if (amount > balance) {
						System.out.println("잔액이 부족합니다. 잔액을 확인해주세요.");
						continue;
					}

					// 송금 처리
					selectedAccount.setBalance(String.valueOf(balance - amount));
					transferAccount.setBalance(String.valueOf(Integer.parseInt(transferAccount.getBalance()) + amount));

					// 파일 저장
					BankDAO.save();
					System.out.printf("○ 송금 완료 %d원 송금 ●", amount);

					// 송금 후 남은 금액 출력
					int remainingBalance = Integer.parseInt(selectedAccount.getBalance());
					DecimalFormat formatter = new DecimalFormat("#,###");
					System.out.printf("(남은 잔액: %s원)\n", formatter.format(remainingBalance));

					// 잔액이 0이면 계좌 해지 처리로 넘어가기
					if (remainingBalance == 0) {
						System.out.println("잔액이 0원이므로 계좌 해지로 넘어갑니다.");
						processAccountDeletion(selectedAccount); // 계좌 해지로 바로 넘어가기
						return; // 더 이상 송금하지 않음
					}

					// 남은 금액이 있는 경우, 추가 송금 여부 묻기
					handlePostTransferMenu(selectedAccount, transferAccount); // 추가 송금 메뉴로 넘어감
					return; // 송금 후 더 이상 진행하지 않음

				} catch (NumberFormatException e) {
					System.out.println("잘못된 금액 입력입니다. 다시 시도해주세요.");
				}
			}
		}
	}

	// 송금 후 추가 송금 메뉴 처리
	private void handlePostTransferMenu(Account selectedAccount, Account transferAccount) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("1. 계속 송금");
			System.out.println("2. 종료");
			System.out.print("선택: ");
			String postTransferChoice = sc.nextLine().trim();

			switch (postTransferChoice) {
			case "1":
				// 계속 송금: 추가로 송금할 금액을 입력받기
				handleRemainingBalance(selectedAccount); // 송금 계속하기
				return; // 계속해서 송금 메뉴를 계속 할 수 있게 return
			case "2":
				// 종료
				System.out.println("송금이 종료되었습니다.");
				return; // 송금을 마치고 종료
			default:
				System.out.println("잘못된 입력입니다. 1 또는 2를 입력해주세요.");
			}
		}
	}

	// 계좌 번호로 계좌 찾기
	private Account findAccountByAccountNumber(String accountNumber) {
		for (Account account : BankDAO.accountList) {
			if (account.getAccountNumber().equals(accountNumber)) {
				return account;
			}
		}
		return null;
	}
}
