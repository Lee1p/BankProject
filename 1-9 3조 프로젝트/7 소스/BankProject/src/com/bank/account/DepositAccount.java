package com.bank.account;

import com.bank.User;
import com.bank.repository.BankDAO;
import com.bank.util.PasswordValidator;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class DepositAccount {

	private String accountNumber; // 계좌번호
	private int balance; // 잔액
	private String password; // 계좌 비밀번호
	private String openDate; // 계좌 개설일

	// 생성자
	public DepositAccount() {
		// 초기화
	}

	// 계좌 개설 메서드
	public void depositAccount() {
		Scanner sc = new Scanner(System.in);

		// 계좌 비밀번호 입력 받기
		this.password = PasswordValidator.getPasswordInput(sc);

		// 계좌번호 생성
		this.accountNumber = generateAccountNumber();
		System.out.println("계좌번호: " + this.accountNumber);

		// 잔액 입력 받기
		this.balance = getBalanceInput(sc);

		// 현재 날짜 가져오기
		LocalDate openDate = LocalDate.now();
		this.openDate = openDate.toString();

		// 고유번호 얻기
		String no = getNextAccountNo();

		// 예금 계좌 생성 후 저장
		Account newAccount = new Account(no, this.accountNumber, this.password, User.getUser().getNo(), this.openDate,
				String.valueOf(this.balance), "0", null, null, null);

		// 계좌를 파일에 저장
		BankDAO.accountList.add(newAccount);
		try {
			BankDAO.save(); // 새로 추가된 계좌를 파일에 저장
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("계좌 개설이 완료되었습니다. 계좌번호: " + this.accountNumber + ", 개설일: " + this.openDate);
		
		// Enter 키를 눌러 메인화면으로 돌아가기
	    System.out.println("Enter키를 눌러 메인화면으로 이동합니다.");
	    sc.nextLine(); // 사용자 입력을 기다림

	}

	// 잔액 입력받기 (숫자만 입력받고, 최대 1000만 원까지 유효성 검사)
	private int getBalanceInput(Scanner sc) {
		int balance = 0;
		while (true) {
			System.out.print("초기 잔액을 입력해주세요 (숫자만, 최대 10,000,000): ");
			String balanceInput = sc.nextLine();

			// 잔액 유효성 검사 (숫자만 입력)
			if (balanceInput.matches("^\\d+$")) {
				balance = Integer.parseInt(balanceInput);

				// 최대 금액 확인
				if (balance > 10_000_000) {
					System.out.println("잔액은 최대 10,000,000원까지만 입력 가능합니다. 다시 입력해주세요.");
				} else {
					break; // 유효한 값일 경우 반복문 탈출
				}
			} else {
				System.out.println("잘못된 입력입니다. 숫자만 입력해주세요.");
			}
		}
		return balance;
	}

	// 계좌 번호 생성 (Set을 사용하여 중복 확인 최적화)
	private String generateAccountNumber() {
	    String accountNumber;
	    Set<String> existingAccountNumbers = BankDAO.accountList.stream()
	            .map(Account::getAccountNumber)
	            .collect(Collectors.toSet());

	    do {
	        accountNumber = "111-431-" + String.format("%06d", (int) (Math.random() * 1000000));
	    } while (existingAccountNumbers.contains(accountNumber));

	    return accountNumber;
	}
	// 고유번호 생성 (추후에 실제 회원으로 바뀔예정)
	private String getNextAccountNo() {
		String no2 = "1"; // 예시로 고정값 사용
		for (Account a : BankDAO.accountList) {
			no2 = Integer.parseInt(a.getNo()) + 1 + "";
		}
		return no2;
	}
}
