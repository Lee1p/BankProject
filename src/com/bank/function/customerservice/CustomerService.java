package com.bank.function.customerservice;

import java.util.Scanner;
import com.bank.repository.BankDAO;
import com.bank.customer.Customer;
import com.bank.function.signin.Signin;

public class CustomerService {

	  public static void showMenu(Scanner scanner) {

	        while (true) { // 반복문 추가로 사용자 입력을 처리
	        	for (int i=0; i<10; i++) {
	    			System.out.println();
	    		}
	        	System.out.println("===================================================");
	            System.out.println("주소: 서울시 강남구 역삼동 쌍용빌딩 8층 33은행");
	            System.out.println("문의전화: 02-1588-1122");
	            System.out.println("===================================================");
	            System.out.println("1. 공지사항");
	            System.out.println("0. 돌아가기");

	            System.out.print("\n선택: ");
	            String input = scanner.nextLine(); // 입력값을 문자열로 받음
	            
	         if (isValidChoice(input, 0, 1)) { 
	                int choice = Integer.parseInt(input);
	            if (choice == 1) {
	                showAnnouncements(scanner); // scanner를 넘겨서 공지사항에서도 입력을 처리
	            } else if (choice == 0) {
	            	Signin.customerMenu(scanner);
	                return; // 메서드를 종료하여 반복문을 벗어남
	            }
            } else {
	            	System.out.println("잘못 입력하셨습니다.");
	                System.out.println("Enter를 누르면 이전화면으로 돌아갑니다.");
	                scanner.nextLine();
	                return; // 이전 화면으로 돌아가기 위해 메서드 종료
	            }
	        }
	    }

	    private static void showAnnouncements(Scanner scanner) {
	        while (true) {
	        	for (int i=0; i<10; i++) {
	    			System.out.println();
	    		}
	        	System.out.println("===================================================");
	            System.out.println("[공지사항]");
	            System.out.println("1. 안녕하세요 33은행입니다.");
	            System.out.println("2025년 고객만족도 3위! 많은 거래 바랍니다.");
	            System.out.println("2. 2025년 1월 20일 시스템 점검 예정: 오전 2시 ~ 오전 5시");
	            System.out.println("===================================================");
	            System.out.println("Enter를 누르면 이전화면으로 돌아갑니다.");
	            
	            scanner.nextLine(); // Enter 키 입력 대기


	            // 이전 화면으로 돌아가기
	          
            	return;
	        }
	    }
	    private static boolean isValidChoice(String input, int min, int max) {
	        // 입력값이 숫자로 구성되어 있고 범위 내에 있는지 확인
	        return input.matches("\\d+") && Integer.parseInt(input) >= min && Integer.parseInt(input) <= max;
	    }
	}

