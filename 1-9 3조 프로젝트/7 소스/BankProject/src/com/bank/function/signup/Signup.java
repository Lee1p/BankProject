package com.bank.function.signup;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.bank.customer.Customer;
import com.bank.function.signin.Signin;
import com.bank.repository.BankDAO;

public class Signup {
	
	private static String no;
	
	   public static void signUp(Scanner scanner) {
	      
		   for (Customer c : BankDAO.customertList) {
	    	
	    	   no = String.valueOf(Integer.parseInt(c.getNo()) + 1);
	       }
		   
		   // 아이디 입력 및 중복 확인
	        String newId = getNewId(scanner);
	        if (newId == null) return; // 잘못 입력시 바로 종료
	        
	        // 이름 입력 및 조건 체크
	        String name = getName(scanner);
	        if (name == null) return;
	        
	        // 비밀번호 입력 및 조건 체크
	        String password = getPassword(scanner);
	        if (password == null) return;
	        
	        // 전화번호 입력 및 형식 검사
	        String phoneNumber = getPhoneNumber(scanner);
	        if (phoneNumber == null) return;
	        
	        // 새로운 고객 정보 생성
	        Customer newCustomer = new Customer(
	            no, // 고유번호 자동 생성
	            name, 
	            newId, 
	            password, 
	            phoneNumber, 
	            java.time.LocalDate.now().toString(), // 가입일 현재 날짜
	            new ArrayList<>(), 
	            new ArrayList<>()
	        );

	        // 고객 정보를 리스트에 추가
	        BankDAO.customertList.add(newCustomer);

	        // 고객 정보를 텍스트 파일에 저장
	        BankDAO.save();

	        System.out.println("회원가입이 완료되었습니다.");

	        // 다음 메뉴로 이동 (예: 메인 메뉴)
	        Signin.mainMenu();
	     
	    }

	    // 아이디 입력 및 중복 체크
	    private static String getNewId(Scanner scanner) {
	        String newId;
	        while (true) {
	        	for (int i=0; i<10; i++) {
	    			System.out.println();
	    		}
	        	System.out.println("아이디는 8~12자리의 영문 대소문자 및 숫자만 입력해주세요.");
	            System.out.print("아이디: ");
	            newId = scanner.nextLine();

	            // 아이디 길이와 구성 검사 (8~12자리, 영문 대소문자, 숫자만)
	            if (!newId.matches("^[a-zA-Z0-9]{8,12}$")) {
	                System.out.println("아이디는 8~12자리의 영문 대소문자 및 숫자만 가능합니다.");
	                System.out.println("Enter를 누르면 이전화면으로 돌아갑니다.");
	                scanner.nextLine(); // Enter를 누를 때까지 대기
	                Signin.mainMenu();
	                return null; // 잘못 입력시 null 반환하여 회원가입을 다시 시작하도록 처리
	            }

	            // 아이디 중복 확인
	            boolean isDuplicate = false;
	            for (Customer c : BankDAO.customertList) {
	                if (c.getId().equals(newId)) {
	                    isDuplicate = true;
	                    break;
	                }
	            }
	            if (isDuplicate) {
	                System.out.println("아이디가 중복됩니다.");
	                System.out.println("Enter를 누르면 이전화면으로 돌아갑니다.");
	                scanner.nextLine(); // Enter를 누를 때까지 대기
	                Signin.mainMenu();
	                return null; // 중복 시 null 반환하여 회원가입을 다시 시작하도록 처리
	            } else {
	                break; // 중복되지 않으면 종료
	            }
	        }
	        return newId;
	    }

	    // 이름 입력 및 조건 체크 (한글 2~4자리)
	    private static String getName(Scanner scanner) {
	        String name;
	        while (true) {
	        	for (int i=0; i<10; i++) {
	    			System.out.println();
	    		}
	        	System.out.println("이름은 한글 2~4자리만 입력해주세요.");
	            System.out.print("이름: ");
	            name = scanner.nextLine();

	            // 이름이 한글 2~4글자만 받도록 정규식 사용
	            if (!name.matches("^[가-힣]{2,4}$")) {
	            	System.out.println("이름은 한글 2~4자리만 가능합니다.");
	                System.out.println("Enter를 누르면 이전화면으로 돌아갑니다. 처음부터 다시 입력해주세요.");
	                scanner.nextLine(); // Enter를 누를 때까지 대기
	                signUp(scanner);
	                return null; // 잘못 입력 시 null 반환하여 회원가입을 다시 시작하도록 처리
	             
	            }
	            break;
	        }
	        return name;
	    }

	    // 비밀번호 입력 및 조건 체크
	    private static String getPassword(Scanner scanner) {
	        String password;
	        while (true) {
	        	for (int i=0; i<10; i++) {
	    			System.out.println();
	    		}
	        	System.out.println("비밀번호는 8~12자리, 숫자, 영어 대문자, 소문자만 입력해주세요.");
	            System.out.print("비밀번호: ");
	            password = scanner.nextLine();

	            if (!passwordCheck(password)) {
	            	 System.out.println("비밀번호는 8~12자리이며 숫자, 영어 대문자, 소문자를 포함해야 합니다.");
	                 System.out.println("Enter를 누르면 이전화면으로 돌아갑니다.");
	                 scanner.nextLine(); // Enter를 누를 때까지 대기
	                 Signin.mainMenu();
	                 return null; // 잘못 입력 시 null 반환하여 회원가입을 다시 시작하도록 처리
	             }

	            System.out.print("비밀번호 재확인: ");
	            String password1 = scanner.nextLine();

	            if (!password.equals(password1)) {
	            	System.out.println("입력하신 비밀번호가 일치하지 않습니다.");
	                System.out.println("Enter를 누르면 이전화면으로 돌아갑니다.");
	                scanner.nextLine(); // Enter를 누를 때까지 대기
	                signUp(scanner);
	                return null; // 비밀번호 불일치 시 null 반환하여 회원가입을 다시 시작하도록 처리
	            } else {
	                break;
	            }
	        }
	        return password;
	    }

	    // 비밀번호 유효성 검사
	    private static boolean passwordCheck(String password) {
	        // 비밀번호는 8~12자리, 숫자, 영어 대문자, 소문자를 포함해야 하고, 특수문자는 허용되지 않음
	        String regExp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,12}$";
	        return password.matches(regExp);
	    }

	    // 전화번호 입력 및 형식 검사 (xxx-xxxx-xxxx)
	    private static String getPhoneNumber(Scanner scanner) {
	        String phoneNumber;
	        while (true) {
	        	for (int i=0; i<10; i++) {
	    			System.out.println();
	    		}
	        	System.out.println("전화번호는 11자리 숫자로 입력해주세요.");
	            System.out.print("전화번호: ");
	            phoneNumber = scanner.nextLine();

	            // 전화번호 형식 검사 (하이픈 포함 가능, 숫자 11자리)
	            if (phoneNumber.matches("^\\d{11}$")) {
	                phoneNumber = formatPhoneNumber(phoneNumber); // 하이픈 형식으로 변환
	                break;  // 숫자 11자리만 입력되면 반복 종료
	            } else if (phoneNumber.matches("^\\d{3}-\\d{4}-\\d{4}$")) {
	                // 이미 하이픈 형식이면 그대로 사용
	                break;
	            } else {
	                System.out.println("전화번호는 11자리 숫자 또는 xxx-xxxx-xxxx 형식이어야 합니다.");
	                System.out.println("Enter를 누르면 이전화면으로 돌아갑니다.");
	                scanner.nextLine(); // Enter를 누를 때까지 대기
	                Signin.mainMenu();
	                return null; // 잘못 입력 시 null 반환하여 회원가입을 다시 시작하도록 처리
	            }
	            }
	        
	        return phoneNumber;
	    }

	    // 고객 정보를 텍스트 파일에 저장
//	    private static void saveCustomerToFile(Customer newCustomer) {
//	        String fileName = "customer.txt"; // 저장할 파일명
//	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
//	            // 고객 정보를 "고유번호●이름●아이디●비밀번호●전화번호●가입일" 형식으로 저장
//	            String customerData = String.format("%s●%s●%s●%s●%s●%s",
//	                    newCustomer.getNo(),
//	                    newCustomer.getName(),
//	                    newCustomer.getId(),
//	                    newCustomer.getPassword(),
//	                    formatPhoneNumber(newCustomer.getPhoneNumber()),
//	                    newCustomer.getOpenDate());
//	            
//	            writer.write(customerData);
//	            writer.newLine(); // 한 줄 추가
//	            System.out.println("고객 정보가 파일에 저장되었습니다.");
//	        } catch (IOException e) {
//	            System.out.println("파일 저장 중 오류가 발생했습니다: " + e.getMessage());
//	        }
//	    }

	 // 전화번호 형식 처리 (하이픈 포함)
	    private static String formatPhoneNumber(String phoneNumber) {
	        if (phoneNumber != null && phoneNumber.length() == 11) {
	            return phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 7) + "-" + phoneNumber.substring(7, 11);
	        }
	        return phoneNumber; // 형식에 맞지 않으면 원본 반환
	    }
}
