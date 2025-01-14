package com.bank.function.transaction;

import java.util.Scanner;

public class TransactionView {
	
	public static void pause() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter를 입력하면 이전 메뉴로 돌아갑니다.");
		String enter = scan.nextLine();
	}

	static void showNormalAccountList() {
		for (int i=0; i<30; i++) {
			System.out.println();
		}
		
		System.out.println("===================================================");
		System.out.println("			이체");
		System.out.println("===================================================");
		
		for (int i=1; i<=Transaction.map.size(); i++) {
			System.out.printf("%d.\t%s\t저축\t잔액 %,.0f원\r\n"
					, i
					, Transaction.map.get(i).getAccountNumber()
					, Double.parseDouble(Transaction.map.get(i).getBalance()));
		}
		System.out.println("0.\t이전 메뉴로");
		System.out.println();
		System.out.println("서비스를 이용하실 통장을 골라주세요.");
		System.out.println();
		
	}
	
	public static void showServiceList() {
		for (int i=0; i<30; i++) {
			System.out.println();
		}
		System.out.println("===================================================");
		System.out.println("		    서비스 선택");
		System.out.println("===================================================");
		System.out.println("1.	계좌이체");
		System.out.println("2.	자동이체");
		System.out.println("0.\t이전 메뉴로");
		System.out.println();
		System.out.println("이용하실 서비스를 선택해주세요.");
		System.out.println();
		
	}

	public static void showTransferInfo(String money) {
		for (int i=0; i<30; i++) {
			System.out.println();
		}
		System.out.println("===================================================");
		System.out.println("		    계좌 확인");
		System.out.println("===================================================");
		if (Transaction.typeOfBank) {
			System.out.println();
			System.out.println("송금하실 계좌의 정보입니다.");
			System.out.printf("%s\t%s\t금액: %,.0f원\r\n"
					, Transaction.toAccount.getAccountNumber()
					, Transaction.toCustomer.getName()
					, Double.parseDouble(money));
			System.out.println("송금하시 겠습니까? (Y/N)");
			
		} else {
			String otherAccountNumForView = Transaction.otherAccountNum.substring(0,3) + "-" + 											Transaction.otherAccountNum.substring(3,6) + "-" +
											Transaction.otherAccountNum.substring(6);
			System.out.println();
			System.out.println("송금하실 계좌의 정보입니다.");
			System.out.printf("%s\t타행\t금액: %,.0f원 (수수료 500원 포함)\r\n"
					, otherAccountNumForView
					, Double.parseDouble(money));
			System.out.println("송금하시 겠습니까? (Y/N)");
		}
	}
	
	public static void showAutoTransferList() {
		for (int i=0; i<30; i++) {
			System.out.println();
		}
		System.out.println("===================================================");
		System.out.println("		     자동 이체");
		System.out.println("===================================================");
		System.out.println("1.	자동이체 설정");
		System.out.println("2.	자동이체 목록");
		System.out.println("3.	자동이체 취소");
		System.out.println("0.	이전 메뉴");
		System.out.println();
		System.out.println("이용하실 서비스를 선택해주세요.");
	}
	
	public static void setAutoTransferView() {
		for (int i=0; i<10; i++) {
			System.out.println();
		}
		System.out.println("===================================================");
		System.out.println("		     자동이체 설정");
		System.out.println("===================================================");
		System.out.println("계좌번호, 납부일, 액수, 이름을 입력해주세요.");
		System.out.println("Enter를 입력하여 계속");
		Scanner scan = new Scanner(System.in);
		String enter = scan.nextLine();
	}

	public static void setAutoTransferCompleteView(String accountNum, String money, String date, String name) {
		for (int i=0; i<10; i++) {
			System.out.println();
		}
		System.out.println("===================================================");
		System.out.println("		     자동이체 설정");
		System.out.println("===================================================");
		System.out.println("성공적으로 등록 완료하였습니다.");
		System.out.printf("%s\t%s\r\n"
				, accountNum.substring(0,3) + "-" + accountNum.substring(3,6) + "-" + accountNum.substring(6)
				, name);
		if (Transaction.typeOfBank) {
			System.out.printf("매월 %s일\t%,.0f원이 이체됩니다.\r\n", date, Double.parseDouble(money));
		} else {
			System.out.printf("매월 %s일\t%,.0f원이 이체됩니다.(타행 수수료 500원)\r\n", date, Double.parseDouble(money));
		}
	
	}

}
