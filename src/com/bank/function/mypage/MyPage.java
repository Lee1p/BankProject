package com.bank.function.mypage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import com.bank.customer.Customer;
import com.bank.function.signin.Signin;
import com.bank.repository.BankDAO;

public class MyPage { // 마이페이지 view
	
	private String id;
	
	public MyPage(String id) {
		this.id = id;
	}
	
	Scanner scan = new Scanner(System.in);
	
	public void myPageSelect() {

		Scanner scan = new Scanner(System.in);
		for (int i=0; i<30; i++) {
			System.out.println();
		}
		System.out.println("===================================================");
		for(Customer c : BankDAO.customertList) {
			if(c.getId().equals(id)) {
				System.out.println(c.getName() + "님 마이페이지 ");
			}
		}
		System.out.println("===================================================");
		System.out.println("1. 정보수정");
		System.out.println("2. 회원탈퇴");
		System.out.println("0. 메뉴");
		System.out.println("===================================================");
		int num = scan.nextInt();
		if(num == 1) {
			myPage_Edit();
		}else if (num == 2) {
			myPage_Delete();
		}else if (num == 0) {
			scan.nextLine();
			Signin signin = new Signin();
			signin.customerMenu(scan);
			return;
		} else {
			System.out.println("1. 정보수정이나 2. 회원탈퇴를 선택해주세요.");
		}
	}
	
	//로그인_고객_마이페이지_정보수정();
	void myPage_Edit(){
		Scanner scan = new Scanner(System.in);
		for (int i=0; i<30; i++) {
			System.out.println();
		}
		System.out.println("===================================================");
		System.out.println(" 수정하실 항목을 선택해주세요. ");
		System.out.println(" 1. 비밀번호 변경 ");
		System.out.println(" 2. 전화번호 변경 ");
		System.out.println(" 0. 뒤로 가기 ");
		System.out.println("===================================================");
		
		int num = scan.nextInt();
		if(num == 1) {
			pw_Edit();
		}else if (num == 2) {
			phon_Edit();
		}else if (num == 0) {
			pause();
		} else {
			System.out.println("올바른 번호를 선택해주세요.");
		}
	}
	
	private void pw_Edit() {
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("변경하실 비밀번호를 입력해주세요.");
			String pw = scan.nextLine();
			boolean result = false;
			//정규표현식 숫자최소1개,대소문자 최소1개,길이8~12
			String regExp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,12}$";
	        //System.out.println("s 정규표현식 적용 결과 ==> "+s.matches(regExp));
	        result=pw.matches(regExp);
			
			if(result==true) {
				for(Customer c : BankDAO.customertList) {
					if(c.getId().equals(id)) { 
						c.setPassword(pw);
						BankDAO.save();
						System.out.println("비밀번호가 수정되었습니다.");
					}
				}
			} else {
				System.out.println("올바른 비밀번호를 입력해주세요.");
				pw_Edit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private void phon_Edit() {
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("변경하실 전화번호를 입력해주세요.");
			System.out.println("-(하이픈)도 같이 입력해주세요.");
			System.out.println("		ex) 010-1234-1234");
			String ph = scan.nextLine();
			
			boolean result = false;
			String regExp = "^\\d{3}-\\d{3,4}-\\d{4}$";
	        result=ph.matches(regExp);
			
			if(result==true) {
				for(Customer c : BankDAO.customertList) {
					if(c.getId().equals(id)) { 
						c.setPhoneNumber(ph);
						BankDAO.save();
						System.out.println("전화번호가 수정되었습니다.");
					}
				}
			} else {
				System.out.println("올바른 전화번호를 입력해주세요.");
				pw_Edit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//TODO 로그인_고객_마이페이지_회원탈퇴();
	void myPage_Delete(){
		
	}
	
	private static void pause() {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		
		System.out.println("계속하려면 엔터를 입력하세요.");
		scan.nextLine();
	}
}
