package com.bank.account;

import java.text.DecimalFormat;
import java.util.Scanner;

import com.bank.util.InputValidator;

public class SecondAccount {
	public static void secondAccount() {

		Scanner sc = new Scanner(System.in);

		boolean loop = true;

		while (loop) {
			System.out.println("2.적금 통장 개설");
			System.out.println("------------------");
			System.out.println("●1. 12개월 5%");
			System.out.println("●2. 24개월 10%");
			System.out.println("●3. 36개월 15%");
			System.out.print("가입 상품 선택:");

			String accountInput = sc.nextLine();

			// 유효성 검사 코드
			if (!InputValidator.isValidOption(accountInput, 0, 3)) { // 입력 검증
				System.out.println("잘못된 입력입니다. 0~3 사이의 숫자를 입력해주세요.");
				continue;
			}

			switch (accountInput) {
			case "1":
				boolean validAmount = false; // 금액이 유효한지 체크하는 변수
				while (!validAmount) {
					System.out.print("액수: ");
					String moneyInput = sc.nextLine();

					if (moneyInput.matches("^[0-9]+$")) { // 숫자만 입력되었는지 확인
						int amount = Integer.parseInt(moneyInput);
						if (amount >= 5000) {
							DecimalFormat df = new DecimalFormat("###,###");
							String formatMoney = df.format(amount);
							System.out.println("입금 금액: " + formatMoney + "원");
							// 나중에 입금처리 로직 작성
							System.out.println("납부일선택(1/10/20):");
							break;
						} else {
							System.out.println("5000원 이상부터 입금 가능합니다.");
							continue;
						}
					} else {
						System.out.println("숫자만 입력해주세요.");
						continue;
					}
				}

				break;

			}
		}

	}
}
