package com.bank.account;

import com.bank.User;
import com.bank.repository.BankDAO;
import com.bank.util.PasswordValidator;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
/**
 *  일반 계좌를 개설을 하는 클래스입니다.
 * 	<Br>
 * 	1. 일반 계좌를 개설하는 기능을 제공합니다.
 * 	<br>
 * 	- 사용자 입력을 받아 계좌 비밀번호를 입력받고 잔액을 설정하고 계좌를 개설합니다. 
 * <br>
 *  - 생성된 계좌 정보를 저장합니다.
 *  
 * 	@author 이재현 
 * 	@version 1.0
 *  @since 2025. 01. 12
 * 
 */
public class DepositAccount {

	private String accountNumber; // 계좌번호
	private int balance; // 잔액
	private String password; // 계좌 비밀번호
	private String openDate; // 계좌 개설일

	// 생성자
	public DepositAccount() {
		// 초기화
	}
/**
 * 예금 계좌를 개설하는 메소드
 * 
 * 이 메서드는 사용자로부터 필요한 정보를 입력받아 새 예금 계좌를 생성
 * <p>
 * 이를 시스템 및 파일에 저장합니다.
 * </p>
 * 
 * 1. 사용자가 계좌 비밀번호를 입력합니다.
 * 2. 고유한 계좌 번호를 생성합니다.
 * 3. 초기 입금 금액을 입력받습니다.
 * 4. 현재 날짜를 가져와 계좌 개설일로 설정합니다.
 * 5. 고유번호를 얻어 새로운 계좌 객체를 생성합니다.
 * 6. 새 계좌 객체를 계좌 리스트에 추가합니다.
 * 7. 새 계좌 정보를 파일에 저장합니다.
 * 
 * <h3> 순서 <h3>
 * 1. 계좌 비밀번호를 입력받아 저장.  
 * 2. 고유한 계좌 번호를 생성.  
 * 3. 초기 잔액을 입력받아 저장.  
 * 4. 현재 날짜를 가져와 개설일로 설정.  
 * 5. 고유번호를 얻어 새로운 계좌 객체를 생성.  
 * 6. 계좌 객체를 리스트와 파일에 저장.  
 * 7. 계좌 개설 완료 메시지 출력.  
 * 
 */
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
