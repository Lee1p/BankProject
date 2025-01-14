package com.bank.account;

import com.bank.User;


import com.bank.repository.BankDAO;
import com.bank.savingaccount.SavingAccount;
import com.bank.util.InputValidator;
import com.bank.util.PasswordValidator;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *  적금 계좌를 개설을 하는 클래스입니다.
 * 	<Br>
 * 	1. 적금 계좌를 개설하는 기능을 제공합니다.
 * 	<br>
 * 	- 사용자 입력을 받아 적금 상품을 선택하고 금액을 설정합니다.
 * <br>
 *  - 생성된 계좌 정보를 저장합니다.
 * 	@author 이재현 
 * 	@version 1.0
 *  @since 2025. 01. 12
 * 
 */


public class FixedDepositAccount {

	// 적금 상품의 기간 (12개월, 24개월, 36개월)
	private String term;

	// 해당 적금 상품의 이자율
	private String interestRate;

	// 입금 금액
	private int amount;

	// 상품별 최대 입금 한도
	private int maxAmount;

	// 납부일 선택 (1, 10, 20 중 하나)
	private String paymentDate;

	// 생성된 계좌 번호
	private String accountNumber;

	// 계좌 비밀번호
	private String passwordInput;

	// 계좌 개설일
	private String openDateString;

	// 고유 계좌 번호 (시작 번호)
	private String no;
	
	  /**
     * FixedDepositAccount 기본 생성자.
     * <br>
     * 적금 계좌 객체를 초기화합니다.
     */

	// 생성자 (초기화 작업)
	public FixedDepositAccount() {
		// 초기화 작업이 필요하면 여기에 추가
	}
	// 기존 코드 유지
	
	/**
	 * 적금 계좌 개설을 실행합니다.
	 * <p>
	 * 1. 상품 선택, 금액 입력, 납부일 선택 등 사용자 입력을 처리합니다.
	 * <p>
	 * 2. 계좌 번호를 생성하고 고유 번호를 설정합니다.
	 * <p>
	 * 3. 계좌 정보를 파일에 저장합니다.
	 */
	public void fixedDepositAccount() {
	    Scanner sc = new Scanner(System.in);

	    System.out.println("2. 적금 통장 개설");
	    System.out.println("===================================================");
	    System.out.println("●1. 12개월 5%");
	    System.out.println("●2. 24개월 10%");
	    System.out.println("●3. 36개월 15%");
	    System.out.print("가입 상품 선택: ");

	    String accountInput = sc.nextLine();

	    // 유효성 검사 코드
	    if (!InputValidator.isValidOption(accountInput, 1, 3)) {
	        System.out.println("잘못된 입력입니다. 1~3 사이의 숫자를 입력해주세요.");
	    }

	    // 상품 정보 설정
	    switch (accountInput) {
	    case "1":
	        this.term = "12"; // 12개월 상품
	        this.interestRate = "5"; // 5% 이자율
	        this.maxAmount = 1000000; // 최대 한도 100만원
	        break;
	    case "2":
	        this.term = "24"; // 24개월 상품
	        this.interestRate = "10"; // 10% 이자율
	        this.maxAmount = 3000000; // 최대 한도 300만원
	        break;
	    case "3":
	        this.term = "36"; // 36개월 상품
	        this.interestRate = "15"; // 15% 이자율
	        this.maxAmount = 5000000; // 최대 한도 500만원
	        break;
	    }

	    System.out.printf("가입 상품:%s개월 이자율:%s%%\n", term, interestRate);

	    // 금액 입력
	    getAmountInput(sc);

	    // 납부일 선택
	    getPaymentDateInput(sc);

	    // 계좌 비밀번호 입력받기
	    this.passwordInput = PasswordValidator.getPasswordInput(sc);

	    // 계좌 번호 생성
	    this.accountNumber = generateAccountNumber();
	    System.out.println("계좌번호: " + accountNumber);

	    // 현재 날짜 가져오기
	    this.openDateString = java.time.LocalDate.now().toString();

	    // 고유번호 얻기
	    this.no = getNextAccountNo();

	    // 계좌 생성 및 저장
	    createAndSaveAccount();

	    // Enter 키를 눌러 메인화면으로 돌아가기
	    System.out.println("Enter키를 눌러 메인화면으로 이동합니다.");
	    sc.nextLine(); // 사용자 입력을 기다림
	    
	}
	
	/**
	 * 금액 입력을 받는 메서드입니다.
	 * <p>
	 * 사용자가 입력한 금액이 유효한지 검사하고, 유효하지 않으면 재입력을 받습니다.
	 * @param sc 사용자 입력을 받는 Scanner 객체
	 */

	// 금액 입력 받는 메서드
	private void getAmountInput(Scanner sc) {
		String moneyInput;
		boolean validAmount = false;
		while (!validAmount) {
			System.out.print("액수: ");
			moneyInput = sc.nextLine();
			if (moneyInput.matches("^[0-9]+$")) {
				this.amount = Integer.parseInt(moneyInput); // 금액을 정수로 변환
				if (this.amount >= 5000 && this.amount <= this.maxAmount) {
					// 금액이 유효하면 3자리마다 쉼표를 넣어 출력
					DecimalFormat df = new DecimalFormat("###,###");
					String formatMoney = df.format(this.amount);
					System.out.println("입금 금액: " + formatMoney + "원");
					validAmount = true;
				} else {
					DecimalFormat df = new DecimalFormat("###,###");
					String MaxformatMoney = df.format(this.maxAmount);
					System.out.println("최소입금액: 5,000원 최대한도액: " + MaxformatMoney + "원");
				}
			} else {
				System.out.println("숫자만 입력해주세요.");
			}
		}
	}

	// 납부일 선택 메서드
	private void getPaymentDateInput(Scanner sc) {
		boolean validPaymentDate = false;
		while (!validPaymentDate) {
			System.out.print("납부일 선택(1/10/20): ");
			this.paymentDate = sc.nextLine();
			if (this.paymentDate.equals("1") || this.paymentDate.equals("10") || this.paymentDate.equals("20")) {
				validPaymentDate = true;
			} else {
				System.out.println("잘못된 납부일입니다. 1, 10, 20 중 하나를 입력해주세요.");
			}
		}
	}

	// 계좌 번호 생성 메서드
	private String generateAccountNumber() {
		String accountNumber;
		boolean isDuplicate;
		do {
			accountNumber = "111-660-" + String.format("%06d", (int) (Math.random() * 1000000)); // 계좌 번호 형식 생성
			isDuplicate = false;
			for (Account account : BankDAO.accountList) {
				if (account.getAccountNumber().equals(accountNumber)) {
					isDuplicate = true; // 중복 계좌 번호 확인
					break;
				}
			}
		} while (isDuplicate);
		return accountNumber; // 중복되지 않는 계좌 번호 반환
	}

	// 고유 계좌 번호를 얻는 메서드
	private String getNextAccountNo() {
		for (Account a : BankDAO.accountList) {
			this.no = Integer.parseInt(a.getNo()) + 1 + ""; // 가장 높은 번호에서 1을 더함
		}
		return this.no;
	}

	// 계좌를 생성하고 저장하는 메서드
	private void createAndSaveAccount() {
		// 적금 계좌 생성
		Account newAccount = new Account(this.no, this.accountNumber, this.passwordInput, User.getUser().getNo(),
				this.openDateString, String.valueOf(this.amount), "1",null ,null ,null);

		// 적금 상품 저장 (회원, 상품선택, 이자율)
		SavingAccount savingAccount = new SavingAccount(this.no, this.term, this.interestRate);
		BankDAO.savingAccountList.add(savingAccount);

		BankDAO.save(); // 계좌 및 상품 정보 파일에 저장

		// 계좌를 파일에 저장
		BankDAO.accountList.add(newAccount);
		BankDAO.save(); // 새로 추가된 계좌 파일에 저장

		// 성공 메시지 출력
		System.out.println("적금 통장 개설이 완료되었습니다. 계좌번호: " + this.accountNumber + " 개설일: " + this.openDateString);
		System.out.println("상품: " + this.term + "개월" + " " + this.interestRate + "%, 납부일: " + this.paymentDate
				+ ", 입금액: " + this.amount + "원");
	}
}
