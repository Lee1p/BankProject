package com.bank.function.findid;

import com.bank.repository.BankDAO;
import com.bank.customer.Customer;
import com.bank.function.signin.Signin;


import java.util.InputMismatchException;
import java.util.Scanner;

public class FindID {

    public static void accountRecovery(Scanner scanner) {
    	for (int i=0; i<10; i++) {
			System.out.println();
		}
    	System.out.println("===================================================");
        System.out.println("		     계정 찾기");
        System.out.println("===================================================");
        System.out.println("1. ID 찾기");
        System.out.println("2. Password 찾기");
        System.out.println("0. 초기화면");

        System.out.print("\n선택: ");
        
       try {
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                findId(scanner);
                break;
            case 2:
                findPassword(scanner);
                break;
            case 0:
                Signin.mainMenu();
                break;
            default:
            	handleInvalidInput(scanner);
                
        }
    }		catch (InputMismatchException e) {
        // 숫자가 아닌 값이 입력된 경우 처리
    	scanner.nextLine(); // 잘못된 입력값 제거
        handleInvalidInput(scanner);
    }
}

    private static void handleInvalidInput(Scanner scanner) {
    	System.out.println("잘못된 입력입니다.");
        System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
        scanner.nextLine(); // 엔터 대기
        Signin.mainMenu(); // 초기화면으로 이동
		
	}

	private static void findId(Scanner scanner) {
    	String name = "";
    	
    	// 한글 이름 길이 검증
        while (true) {
        	for (int i=0; i<10; i++) {
    			System.out.println();
    		}
        	System.out.println("이름은 한글 2~4자리만 입력해주세요.");
        	System.out.print("이름: ");
        	name = scanner.nextLine();

     // 한글만 허용 (길이는 2~4자)
        if (name.matches("[가-힣]{2,4}")) {
            break;  // 유효한 이름이면 반복 종료
        } else {
            System.out.println("이름은 한글 2~4자만 입력 가능합니다.");
            System.out.println("Enter를 누르면 이전화면으로 돌아갑니다.");
            scanner.nextLine(); // Enter를 누르면 이전 화면으로 돌아갑니다.
            return; 
        }
    }
        String phoneNumber = "";
        
        while (true) {
        	for (int i=0; i<10; i++) {
    			System.out.println();
    		}
        	System.out.println("전화번호는 11자리 숫자로 입력해주세요.");
        	System.out.print("전화번호: ");
        	phoneNumber = scanner.nextLine();
        
        // 전화번호에서 하이픈 제거 후, 숫자만 11자리인지 확인
        	phoneNumber = phoneNumber.replaceAll("[^\\d]", "");  // 하이픈 등 비숫자 문자 제거
        	if (phoneNumber.matches("\\d{11}")) {
        		phoneNumber = formatPhoneNumber(phoneNumber); // 하이픈 형식으로 변환
        		break;  // 유효한 전화번호이면 반복 종료
        } else {
            System.out.println("전화번호는 11자리 숫자만 입력 가능합니다.");
            System.out.println("Enter를 누르면 이전화면으로 돌아갑니다.");
            scanner.nextLine(); // Enter를 누르면 이전 화면으로 돌아갑니다.
            return;
        }
    }
        
        boolean idFound = false;

        for (Customer customer : BankDAO.customertList) {
            if (customer.getName().equals(name) && customer.getPhoneNumber().equals(phoneNumber)) {
                System.out.println("찾으신 ID는: " + customer.getId());
                idFound = true;
                break;
            }
        }
        if (!idFound) {
        System.out.println("해당 정보와 일치하는 ID가 없습니다.");
    }
        System.out.println(" ===================================");
        System.out.println("Enter를 누르면 첫화면으로 돌아갑니다.");        
        System.out.println();
        scanner.nextLine(); // Enter 입력 대기

        // 메인 메뉴로 돌아가기
        Signin.mainMenu();
    }
        
        
    private static String formatPhoneNumber(String phoneNumber) {
    	 if (phoneNumber != null && phoneNumber.length() == 11) {
    	        // 하이픈을 추가하여 "010-1111-1111" 형식으로 변환
    	        return phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 7) + "-" + phoneNumber.substring(7, 11);
    	    }
    	    return phoneNumber; // 형식에 맞지 않으면 원본 반환
    	}
		
	

	private static void findPassword(Scanner scanner) {
        String id = ""; 	
    	
     // ID 문자 검증 (영어 소문자, 대문자, 숫자)
     while (true) {  
    	 for (int i=0; i<10; i++) {
 			System.out.println();
 		}
    	System.out.println("ID는 8~12자리의 영문 대소문자 및 숫자만 입력해주세요.");
        System.out.print("ID: ");
        id = scanner.nextLine();
        
        if (id.matches("[a-zA-Z0-9]{6,14}")) {
            break;  // 유효한 ID이면 반복 종료
        } else {
            System.out.println("ID는 영어 대소문자와 숫자만 포함합니다.");
            System.out.println("Enter를 누르면 이전화면으로 돌아갑니다.");
            scanner.nextLine(); // Enter를 누르면 이전 화면으로 돌아갑니다.
            return;
        }
    }

     	String name = "";
     
     while (true) {
    	 for (int i=0; i<10; i++) {
 			System.out.println();
 		}
    	System.out.println("이름은 한글 2~4자리만 입력해주세요.");
        System.out.print("이름: ");
        name = scanner.nextLine();
        
        if (name.matches("[가-힣]{2,4}")) {
            break;  // 유효한 이름이면 반복 종료
        } else {
            System.out.println("이름은 한글 2~4자만 입력 가능합니다.");
            System.out.println("Enter를 누르면 이전화면으로 돌아갑니다.");
            scanner.nextLine(); // Enter를 누르면 이전 화면으로 돌아갑니다.
            return;
        }
    }

     	String phoneNumber = "";
     
     while (true) {
    	 for (int i=0; i<10; i++) {
 			System.out.println();
 		}
    	System.out.println("전화번호는 11자리 숫자로 입력해주세요.");
        System.out.print("전화번호: ");
        phoneNumber = scanner.nextLine();
        
     // 전화번호에서 하이픈 제거 후, 숫자만 11자리인지 확인
        phoneNumber = phoneNumber.replaceAll("[^\\d]", "");  // 하이픈 등 비숫자 문자 제거
        if (phoneNumber.matches("\\d{11}")) {
        	phoneNumber = formatPhoneNumber(phoneNumber); // 하이픈 형식으로 변환
            break;  // 유효한 전화번호이면 반복 종료
        } else {
            System.out.println("전화번호는 11자리 숫자만 입력 가능합니다.");
            System.out.println("Enter를 누르면 이전화면으로 돌아갑니다.");
            scanner.nextLine(); // Enter를 누르면 이전 화면으로 돌아갑니다.
            return;
        }
    }
        
        boolean passwordFound = false;

        for (Customer customer : BankDAO.customertList) {
            if (customer.getId().equals(id) &&
                customer.getName().equals(name) &&
                customer.getPhoneNumber().equals(phoneNumber)) {
                System.out.println("비밀번호는: " + customer.getPassword());
                passwordFound = true;
                
                break;
            }
        }
        if (!passwordFound) {
        System.out.println("입력한 정보와 일치하는 계정이 없습니다.");
    }
        System.out.println("===================================================");
        System.out.println("Enter를 누르면 첫화면으로 돌아갑니다.");        
        System.out.println();
        scanner.nextLine(); // Enter 입력 대기

        // 메인 메뉴로 돌아가기
        Signin.mainMenu();
}
}
