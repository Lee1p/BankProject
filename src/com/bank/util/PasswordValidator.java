package com.bank.util;

import java.util.Scanner;

import com.bank.account.Account;
import com.bank.repository.BankDAO;

public class PasswordValidator {
		
	 // 계좌 비밀번호 입력받기
    public static String getPasswordInput(Scanner sc) {
        String passwordInput;
        while (true) {
            System.out.println("계좌에 사용하실 비밀번호 숫자 4자리를 입력해주세요.");
            System.out.print("비밀번호: ");
            passwordInput = sc.nextLine();

            // 비밀번호 유효성 검사 (4자리 숫자)
            if (passwordInput.matches("^\\d{4}$")) {
                break;
            } else {
                System.out.println("잘못된 입력입니다. 4자리 숫자만 입력해주세요.");
            }
        }
        return passwordInput;
    
    }//main
    
}//class
	