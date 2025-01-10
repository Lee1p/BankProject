package com.bank.function.transaction;

import java.util.Scanner;

public class AutoTransfer {
	
	public static void autoTransfer() {
		
		TransactionView.showAutoTransferList();
		selectAutoTransferList();
		
	}
	
	public static void selectAutoTransferList() {
		int num = -1;
		
		Scanner scan = new Scanner(System.in);
		num = TransactionService.checkNextInt(scan);
		
		if (num <= 3 && num >= 0) {
			
			if (num == 0) {
				TransactionView.showServiceList();
				Transaction.selectServiceList();
				return;
			}
			if (num == 1) {
				setAutoTranfer();
				
			}
			if (num == 2) {
				System.out.println("점검 중입니다.");
				TransactionView.pause();
				autoTransfer();
			}
			if (num == 3) {
				System.out.println("점검 중입니다.");
				TransactionView.pause();
				autoTransfer();
			}
			
		} else {
			System.out.println("잘못된 번호를 입력하였습니다.");
			TransactionView.pause();
			TransactionView.showAutoTransferList();
			selectAutoTransferList();
			return;
		}
	}

	private static void setAutoTranfer() {
		TransactionView.setAutoTransferView();
		
		Scanner scan = new Scanner(System.in);
		String accountNum = null;
		String money = null;
		String date = null;
		String name = null;
		
		accountNum = TransactionService.checkAccountNum(scan);
		
		if (accountNum != null) {
			Transaction.searchAccount(accountNum);
			money = TransactionService.checkMoney(scan);
			date = TransactionService.checkDate(scan);
			name = TransactionService.checkName(scan);
			
			TransactionView.setAutoTransferCompleteView(accountNum, money, date, name);
			TransactionView.pause();
			autoTransfer();
			
		} else {
			autoTransfer();
		}
	}

}
