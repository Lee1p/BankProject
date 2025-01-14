package com.bank.function.signin;

import java.util.Scanner;

import com.bank.function.card.CardMain;
import com.bank.function.countrymoney.MyContryMoney;
import com.bank.function.customerservice.CustomerService;
import com.bank.function.findid.FindID;
import com.bank.function.mypage.MyPage;
import com.bank.User;
import com.bank.account.AccountSelect;
import com.bank.customer.Customer;
import com.bank.function.signup.Signup;
import com.bank.function.transaction.Transaction;
import com.bank.function.transaction.TransactionView;
import com.bank.repository.BankDAO;

public class Signin {
//	private static Customer loggedInCustomer = null; // 로그인 상태 관리
    
    public static void mainMenu() {
    	Scanner scan = new Scanner(System.in);
    	
    	while (true) {
	    	if (User.getUser() != null) { // 로그인된 상태면 고객 서비스로 이동
	             customerMenu(scan);
	             return;
	         }
	    	for (int i=0; i<10; i++) {
				System.out.println();
			}
	    	System.out.println("===================================================");
	        System.out.println("		     쌍용은행");
	        System.out.println("===================================================");
	        System.out.println("1.로그인");
	        System.out.println("2.회원가입");
	        System.out.println("3.계정찾기");
	        System.out.println("4.종료");
	        System.out.println();
	        System.out.print("선택: ");
	        String input = scan.nextLine();
	        
	        if (isValidChoice(input)) {
	            int choice = Integer.parseInt(input);
	        switch (choice) {
	            case 1:
	            	login(scan);
	            	return;
	            case 2:
	            	Signup.signUp(scan); 
	            	return;
	            case 3:
	            	FindID.accountRecovery(scan);
	            	return;
	            case 4:
	                System.out.println("프로그램이 정상적으로 종료되었습니다.");
	                return;
	        	}
	        } else {
	        
	        	 System.out.println("잘못 입력하셨습니다.");
	             System.out.println("Enter를 누르면 이전화면으로 돌아갑니다.");
	             scan.nextLine();
	        }
    	} 
	}
   
    private static void login(Scanner scan) {
        System.out.print("\nID: ");
        String id = scan.nextLine();
        
     // 아이디 길이와 구성 검사 (8~12자리, 영문 대소문자, 숫자만)
        if (!id.matches("^[a-zA-Z0-9]{6,14}$")) {
            System.out.println("아이디는 6~12자리의 영문 대소문자 및 숫자만 가능합니다.");
            System.out.println("Enter를 누르면 이전화면으로 돌아갑니다. 처음부터 다시 입력해주세요.");
            scan.nextLine(); // Enter를 누를 때까지 대기
            return; // 잘못 입력시 null 반환하여 회원가입을 다시 시작하도록 처리
        }

        System.out.print("Password: ");
        String password = scan.nextLine();
        
     // 비밀번호 형식 검사 (8~12자리, 영어 대소문자, 숫자 포함)
        if (!password.matches("^[a-zA-Z0-9]{6,14}$")) {
            System.out.println("비밀번호는 8~12자리의 영문 대소문자 및 숫자만 가능합니다.");
            System.out.println("Enter를 누르면 이전화면으로 돌아갑니다. 처음부터 다시 입력해주세요.");
            scan.nextLine(); // Enter를 누를 때까지 대기
            return; // 잘못 입력 시 이전 화면으로 돌아감
        }

        for (Customer customer : BankDAO.customertList) {
            if (customer.getId().equals(id) && customer.getPassword().equals(password)) {
            	User.setUser(customer); // 로그인 성공 시 저장
            	
            	customerMenu(scan);
            	return;  
             
            }
        }
        
        System.out.println("ID 또는 Password가 일치하지 않습니다.");
        mainMenu();
    }
    
    public static void logout() {
        User.setUser(null); // 로그아웃 처리
        TransactionView.pause();
        mainMenu();
    }
    
    public static void customerMenu(Scanner scanner) {
        while (true) {
        	for (int i=0; i<30; i++) {
    			System.out.println();
    		}
        	System.out.println("===================================================");
        	System.out.printf("		   %s님 반갑습니다.\n", User.getUser().getName());
        	System.out.println("===================================================");
            System.out.println("1.이체");
            System.out.println("2.계좌 조회");
            System.out.println("3.카드");
            System.out.println("4.외환거래");
            System.out.println("5.마이페이지");
            System.out.println("6.고객센터");
            System.out.println("7.로그아웃");

            System.out.print("\n선택: ");
            String input = scanner.nextLine();

            if (isValidChoice(input, 1, 7)) {
                int menuChoice = Integer.parseInt(input);

            switch (menuChoice) {
                case 1:
                	Transaction.transaction();
                    return;
                case 2:
                	AccountSelect accountSelect = new AccountSelect();
                	accountSelect.accountDisplay();
                    return;
                case 3:
                	CardMain.cardHome();
                	return;
                case 4:
                	MyContryMoney myCM = new MyContryMoney(User.getUser().getNo());
                	myCM.countryMoneyOption();
                	return;
                case 5:
                	MyPage mypage = new MyPage(User.getUser().getId());
                	mypage.myPageSelect();
                	return;
                case 6:
                    CustomerService.showMenu(scanner);
                    return;
                case 7:
                    System.out.println("정상적으로 로그아웃 되었습니다.");
                    logout();
                   
                    return;
            	}
            }else {
                System.out.println("잘못 입력하셨습니다.");
                System.out.println("Enter를 누르면 이전화면으로 돌아갑니다.");
                scanner.nextLine();
            }

        }
    

}
    private static boolean isValidChoice(String input) {
        return input.matches("[1-4]");
    }

    private static boolean isValidChoice(String input, int min, int max) {
        return input.matches("\\d+") && Integer.parseInt(input) >= min && Integer.parseInt(input) <= max;
    }
}