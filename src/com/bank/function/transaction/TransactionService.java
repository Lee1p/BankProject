package com.bank.function.transaction;

import java.util.Scanner;

import com.bank.User;
import com.bank.account.Account;
import com.bank.function.signin.Signin;

public class TransactionService {

	static boolean checkAccount() {
		
		if (Transaction.map.size() == 0) {
			System.out.println("보유하신 계좌가 없습니다.");
			TransactionView.pause();

			Scanner scan = new Scanner(System.in);
			Signin.customerMenu(scan);
			return false;
		} 
		return true;
	}
	
	static void pickNormalAccount() {
		int index = 1;
		for (Account a : User.getUser().getAlist()) {
			if (a.getTypeOfAccount().equals("0")) {
				Transaction.map.put(index, a);
				index++;
			}
		}
	}

	public static int checkNextInt(Scanner scan) {
		int number = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print("입력: ");
            String input = scan.nextLine();

            // 숫자만 허용하는 정규식
            if (input.matches("-?\\d+")) {
                try {
                    number = Integer.parseInt(input);
                    valid = true;  // 유효한 숫자가 입력되면 루프 종료
                } catch (NumberFormatException e) {
                    System.out.println("숫자만 입력해주세요.");
                }
            } else {
                System.out.println("숫자만 입력해주세요.");
            }
        }
        return number;  // 유효한 값을 반환
	}

	public static String checkAccountNum(Scanner scan) {
		String accountNum = null;
		
		System.out.println("송금하실 계좌번호를 입력해주세요.");
		System.out.println("\"-\"을 제외한 12자리 숫자만 입력해주세요.");
		System.out.println();
		System.out.print("계좌번호: ");
		
		accountNum = scan.nextLine();
		
		if (accountNum.length() == 12) {
			for (int i=0; i<accountNum.length(); i++) {
				if (accountNum.charAt(i) < '0' || accountNum.charAt(i) > '9') {
					System.out.println("잘못된 계좌번호입니다.");
					TransactionView.pause();
					return null;
				}
			}
		} else {
			System.out.println();
			System.out.println("잘못된 계좌번호입니다.");
			System.out.println();
			TransactionView.pause();
			return null;
		}
		
		return accountNum;
	}

	public static String checkMoney(Scanner scan) {
		
		String money = null;
		boolean condition = false;
		
		while (!condition) {
			System.out.print("금액: ");
			money = scan.nextLine();
			
			if (money.isEmpty()) {
				System.out.println();
				System.out.println("숫자만 입력해주세요.");
				continue;
			}
			
			boolean exit = false;
				for (int i = 0; i < money.length(); i++) {
					if (money.charAt(i) < '0' || money.charAt(i) > '9') {
						System.out.println();
						System.out.println("숫자만 입력해주세요.");
						exit = true;
						break;
					}
				}
			
			if (exit) continue;			
			
			if (Transaction.typeOfBank) { //당행 수수료X
				if ((Double.parseDouble(Transaction.transactionAccount.getBalance())
						- Double.parseDouble(money)) >= 0) {
					//통과
					condition = true;
				} else {
					System.out.println();
					System.out.println("계좌의 잔액이 부족합니다.");
					System.out.printf("잔액 %,.0f원 \r\n",
							Double.parseDouble(Transaction.transactionAccount.getBalance()));
					continue;
				}
			} else { //타행 수수료O
				money = String.format("%.0f", Double.parseDouble(money) + 500);
				if ((Double.parseDouble(Transaction.transactionAccount.getBalance())
						- Double.parseDouble(money)) >= 0) {
					//통과
					condition = true;
				} else {
					System.out.println();
					System.out.println("계좌의 잔액이 부족합니다.");
					System.out.printf("잔액 %,.0f원 (타행 수수료 500원)\r\n",
							Double.parseDouble(Transaction.transactionAccount.getBalance()));
					continue;
				}
			} 
		}
		return money;
	}

	public static String checkYN(Scanner scan) {
		
		String input;
		
        boolean valid = false;

        while (!valid) {
            input = scan.nextLine();

            // 입력값이 "Y", "y", "N", "n" 중 하나인지 확인
            if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N")) {
                valid = true;
                return input;
            } else {
            	 System.out.println("대소문자 상관없이 \"y\", \"n\"만 입력해주세요.");
            }
        }
        
        return null; 
	}

	public static String checkDate(Scanner scan) {
		
		String date = null;
		boolean condition = false;
		
		while (!condition) {
			System.out.println("1,10,20 중에 입력해주세요.");
			System.out.print("납부일: ");
			date = scan.nextLine();
			
			if (date.isEmpty()) {
				System.out.println("숫자만 입력해주세요.");
				continue;
			}
			
			boolean exit = false;
				for (int i = 0; i < date.length(); i++) {
					if (date.charAt(i) < '0' || date.charAt(i) > '9') {
						System.out.println();
						System.out.println("숫자만 입력해주세요.");
						exit = true;
						break;
					}
				}
			if (exit) continue;			
			// 숫자인거 확인
			// 1, 10, 20 인지 확인
			if (date.equals("1") || date.equals("10") || date.equals("20")) {
				condition = true;
			} else {
				continue;
			}
		}
		return date;
		
	}

	public static String checkName(Scanner scan) {
		
        String name;
        boolean isValid;

        do {
            System.out.print("이름을 입력하세요 (한글 1~6자): ");
            name = scan.nextLine();
            
            // 유효성 검사: 한글로만 이루어지고 1~6자인지 확인
            isValid = name.matches("^[가-힣]{1,6}$");
            
            if (!isValid) {
                System.out.println("잘못된 입력입니다. 한글로만 이루어진 1~6자의 이름을 입력해주세요.");
            }
        } while (!isValid);

		return name;
	}
	
}
