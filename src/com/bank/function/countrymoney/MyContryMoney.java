package com.bank.function.countrymoney;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bank.account.Account;
import com.bank.countrymoneylist.CountryMoneyList;
import com.bank.customer.Customer;
import com.bank.function.signin.Signin;
import com.bank.repository.BankDAO;

public class MyContryMoney {

	private String no;
	
//	String dollar, String euro, String yen, String yuan
	private List<Account> accounts = new ArrayList<>();
//	private Scanner scan = new Scanner(System.in);
//	private Scanner scan2 = new Scanner(System.in);
	private int num;
	private int count;
	private String str;
	private String don;
	
	public MyContryMoney(String no) {
		this.no = no;
	}
	// 환율 메뉴조회
	public void countryMoneyOption() {
		Scanner scan = new Scanner(System.in);
		for (int i=0; i<30; i++) {
			System.out.println();
		}
		System.out.println("===================================================");
		System.out.println("1. 환율 조회");
		System.out.println("2. 외환 잔고 조회");
		System.out.println("3. 외환 구매");
		System.out.println("4. 외환 판매");
		System.out.println("0. 취소");
		System.out.println();
		System.out.println("번호를 선택해주세요: ");
		
		num = scan.nextInt();
		scan.nextLine();
		
		if (num == 1) {
			exchangeSelect();
			return;
		} else if (num == 2) {
			myExchangeSelect();
			return;
		} else if (num == 3) {
			myExchangeBuy(true);
			return;
		} else if (num == 4) {
			myExchangeBuy(false);
			return;
		} else if (num == 0) {
			Signin s = new Signin();
			s.customerMenu(scan);
			return;
		} else {
			System.out.println();
		}
	}

	private void exchangeSelect() {
		Scanner scan = new Scanner(System.in);
		for (int i=0; i<30; i++) {
			System.out.println();
		}
		System.out.println("===================================================");
		System.out.println("		    현재 환율 ");
		System.out.println("===================================================");
		System.out.println();
		System.out.println("1달러	: 1,473원");
		System.out.println("1유로	: 1,518원");
		System.out.println("100엔	: 935원");
		System.out.println("1위안	: 200원");
		System.out.println();
		System.out.println("메뉴로 돌아가려면 Enter를 눌러주세요.");
		
		scan.nextLine();
		countryMoneyOption();
	}

	// 내 외환 잔고 조회
	private void myExchangeSelect() {
		Scanner scan = new Scanner(System.in);
		for (int i=0; i<30; i++) {
			System.out.println();
		}
		for (Customer c : BankDAO.customertList) {
			// 내외환 조회
			myExMoneyCheck(c.getNo(), c.getName());
		}
		System.out.println("외환 메뉴로 돌아가려면 Enter를 눌러주세요.");
		scan.nextLine();
		countryMoneyOption();
	}
	
	// 보유한 외화 전체 확인
	private void myExMoneyCheck(String cno, String cname) {
		if (cno.equals(no)) {
			System.out.println(cname + "님이 보유하신 외환입니다.");
			System.out.println();
			for(CountryMoneyList m : BankDAO.countryMoneyListList) {
				if(cno.equals(m.getNo())) {
					System.out.println("달러: " + String.format("%,.0f", Double.parseDouble(m.getDollar())) + " 달러\n" + 
										"유로 " + String.format("%,.0f", Double.parseDouble(m.getEuro())) + " 유로\n"+ 
										"엔: " + String.format("%,.0f", Double.parseDouble(m.getYen())) + " 엔\n" + 
										"위안: " + String.format("%,.0f", Double.parseDouble(m.getYuan())) + " 위안\n");
				}
			}
		}
	}
	
	// 내 외환 종류별로 확인 
	private String myExMoneyCheck(int don) {
		for (Customer c : BankDAO.customertList) {
			if (c.getNo().equals(no)) {
				System.out.println(c.getName() + "님이 보유하신 외환입니다.");
				System.out.println();
				for(CountryMoneyList m : BankDAO.countryMoneyListList) {
					if(c.getNo().equals(m.getNo())) {
						if(don == 1) {
							return m.getDollar();
						}else if(don == 2) {
							return m.getEuro();
						}else if(don == 3) {
							return m.getYen();
						}else if(don == 4) {
							return m.getYuan();
						}else {
							System.out.println("올바른 값이 없습니다.");
						}
					}
				}
			}
		}
		return null;
	}
	
	// 원하는 외환 고르기
	private void myExchangeBuy(boolean flag) {
		for (int i=0; i<30; i++) {
			System.out.println();
		}
		if(flag) {
			System.out.println("구매하시려는 외화를 선택해주세요");
		}else {
			System.out.println("판매하시려는 외화를 선택해주세요");
		}
		
		System.out.println("");
		System.out.println("1. 1달러	: 1,473원");
		System.out.println("2. 1유로	: 1,518원");
		System.out.println("3. 100엔	: 935원");
		System.out.println("4. 1위안	: 200원");
		System.out.println("===================================================");
		System.out.print("선택 : "); 
		
		Scanner scan = new Scanner(System.in);
		num = scan.nextInt();
		don = null;
		
		if(num == 1) {
			don = myExMoneyCheck(1);
			System.out.println("가지고 있는 달러는 " + don + " 달러입니다." );
			System.out.println();
			// 내계좌 리스트 보여주기
			myaccountlist(num, flag);
			
		} else if (num == 2) {
			don = myExMoneyCheck(2);
			System.out.println("가지고 있는 유로는 " + don + " 유로입니다." );
			System.out.println();
			myaccountlist(num, flag);
		} else if (num == 3) {
			don = myExMoneyCheck(3);
			System.out.println("가지고 있는 엔은 " + don + " 엔은입니다." );
			System.out.println();
			myaccountlist(num, flag);
		} else if (num == 4) {
			don = myExMoneyCheck(4);
			System.out.println("가지고 있는 위안은 " + don + " 위안입니다." );
			System.out.println();
			myaccountlist(num, flag);
//			checkAccountNum(num);
		} else {
			System.out.println("메뉴에 있는 항목만 선택해주세요.");
			System.out.println();
			return;
		}
	}
	
	// 내가 가지고 있는계좌 모두 불러오는 메서드
	private void myaccountlist(int type, boolean flag) {
		for(Customer c : BankDAO.customertList) {
			if(c.getNo().equals(no) ) {
				System.out.println(c.getName() +"고객님의 일반계좌입니다.");
				System.out.println("===================================================");
				// 로그인고객의 일반계좌 보여주기
				showAccountList(c.getNo(),type, flag);
			}
		}

	}
	
	// 거래할 계좌 선택, 비밀번호확인
	private void checkAccountNum(int type, boolean flag) {
		for (int i=0; i<30; i++) {
			System.out.println();
		}
		System.out.println("===================================================");
		System.out.println();
		if(flag) {
			System.out.println("거래하실 계좌를 선택해주세요.");
		}else {
			System.out.println("판매 후 돈을 받을 계좌를 선택해주세요.");
		}
		
		System.out.println("취소하시려면 0번을 선택해주세요.");
		
		Scanner scan = new Scanner(System.in);
		num = scan.nextInt();
		
		if(num>0 && num <= accounts.size()) {
//			System.out.println("선택계좌번호"+num);
			System.out.println();
			System.out.println("계좌의 비밀번호를 입력해주세요 ");
			System.out.println("비밀번호 : ");
//			System.out.println(accounts.get(num-1).getPassword());
			scan.nextLine();
			str = scan.nextLine();
			if(accounts.get(num-1).getPassword().equals(str)) {				
				// 구매할 외환단위 입력
				int accountIndex = num-1;
				checkExchange(accountIndex, type, flag);
				
			} else {
				System.out.println("비밀번호가 틀렸습니다.");
			}
		} else if(num==0) {
			System.out.println("거래가 취소되었습니다.");
		}else {
			System.out.println("올바른 번호를 입력해주세요.");
		}
		
	}
	
	// 구매할 외환 입력 
	private void checkExchange(int accountIndex, int type, boolean flag) {
		if(flag) {
			System.out.println("구매하실 달러를 입력해주세요. ");
		} else {
			System.out.println("판매하실 유로를 입력해주세요. ");
		}
		
		Scanner scan = new Scanner(System.in);
		num = scan.nextInt();
		int sum;
		int won =  Integer.parseInt(accounts.get(accountIndex).getBalance());
		
		if(flag) { // 구매할때
			if(num > 0) {
				if(type == 1) {
					sum = won-(num * 1473);
					System.out.println("구매하실 금액은 총"+(num * 1473)+" 원 입니다."); 
					ExChangeAccount(sum,type,accountIndex,num, flag);
					
				} else if(type == 2) {
					System.out.println("구매하실 금액은 총"+(num * 1518)+" 원 입니다."); 
					sum = won-(num * 1518);
					ExChangeAccount(sum,type,accountIndex,num, flag);
	
				} else if(type == 3) {
					System.out.println("구매하실 금액은 총"+(num * 935)+" 원 입니다."); 
					sum = won-(num * 935);
					ExChangeAccount(sum,type,accountIndex,num, flag);
	
				} else if(type == 4) {
					System.out.println("구매하실 금액은 총"+(num * 200)+" 원 입니다."); 
					sum = won-(num * 200);
					ExChangeAccount(sum,type,accountIndex,num, flag);
					
				}
			}else {
				System.out.println("잘못된 숫자를 입력하셨습니다.");
				checkExchange(count,type, flag);
			}
		}else { // 판매할 때
			if(num > 0) {
				if(type == 1) {
					sum = won+(num * 1473);
					System.out.println("판매하실 금액은 총"+(num * 1473)+" 원 입니다."); 
					ExChangeAccount(sum,type,accountIndex,num, flag);
				} else if(type == 2) {
					System.out.println("구매하실 금액은 총"+(num * 1518)+" 원 입니다."); 
					sum = won+(num * 1518);
					ExChangeAccount(sum,type,accountIndex,num, flag);
				} else if(type == 3) {
					System.out.println("구매하실 금액은 총"+(num * 935)+" 원 입니다."); 
					sum = won+(num * 935);
					ExChangeAccount(sum,type,accountIndex,num, flag);
	
				} else if(type == 4) {
					System.out.println("구매하실 금액은 총"+(num * 200)+" 원 입니다."); 
					sum = won+(num * 200);
					ExChangeAccount(sum,type,accountIndex,num, flag);
				}
			}else {
				System.out.println("잘못된 숫자를 입력하셨습니다.");
				checkExchange(count,type, flag);
			}
		}
		
			
	}
	
	// 환전금액 빼기
	private void ExChangeAccount(int sum, int type, int accountIndex, int num, boolean flag) {
		
		System.out.println("===================================================");
		System.out.println();
		
		 String currency = "";
		 String currency2 = "";
		

		if(sum >= 0) { 
			accounts.get(accountIndex).setBalance(String.valueOf(sum)); // 계좌 잔액 업데이트

		    // 외환보유량 업데이트
		    for(CountryMoneyList m : BankDAO.countryMoneyListList) {
		    	if(m.getNo().equals(no)) {
		    		
		    		if(type == 1) {
		    			currency = "달러"; 
		    			if(flag) {
		    				currency2 = String.valueOf(Integer.parseInt(m.getDollar()) + num );
		    			}else {
		    				currency2 = String.valueOf(Integer.parseInt(m.getDollar()) - num );
						}
		    			m.setDollar( currency2 );
		    		}else if (type == 2) {
		 		    	currency = "유로"; 
		 		    	if(flag) {
		 		    		currency2 = String.valueOf(Integer.parseInt(m.getEuro()) + num );
		 		    	}else{
		 		    		currency2 = String.valueOf(Integer.parseInt(m.getEuro()) - num );
		 		    	}
		 		    	m.setEuro( currency2 );
		 		    }else if (type == 3) {
		 		    	currency = "엔"; 
		 		    	if(flag) {
		 		    		currency2 = String.valueOf(Integer.parseInt(m.getYen()) + num );
		 		    	}else {
		 		    		currency2 = String.valueOf(Integer.parseInt(m.getYen()) - num );
		 		    	}
		 		    	m.setYen( currency2 );
		 		    }else if (type == 4) {
		 		    	currency = "위안"; 
		 		    	if(flag) {
		 		    		currency2 = String.valueOf(Integer.parseInt(m.getYuan()) + num );
		 		    	}else {
		 		    		currency2 = String.valueOf(Integer.parseInt(m.getYuan()) - num );
		 		    	}
		 		    	m.setYuan( currency2 );
		 		    }
		    	}
		    }
		    BankDAO.save(); // 데이터 저장
		    
		    if(flag == true)  System.out.println("구매 완료: " + num + " " + currency + " 보유량 : " + currency2 + currency);
		    if(flag == false) System.out.println("판매 완료: " + num + " " + currency + " 보유량 : " + currency2 + currency);
		    
		    System.out.println("총 계좌 잔액: " + sum + "원");
		} else {
			System.out.println("잔액이 부족합니다.");
			
//				checkExchange(count,type);
		}
	}


	// 로그인 고객의 일반계좌 보여주기
	private void showAccountList(String cno, int type, boolean flag) {
		count = 1;
		for(Account a : BankDAO.accountList) {
			if(a.getCusutomerNo().equals(cno) && a.getTypeOfAccount().equals("0")) { 
				accounts.add(a); 
				System.out.println(count+". 계좌번호 : "+a.getAccountNumber()+"\t 잔액 : "+ a.getBalance() + "원");
				count++;
				
			}
		}
		checkAccountNum(type, flag);
	}

	// 외환 판매 
	private void myExchangeSale() {
		System.out.println("판매하시려는 외화를 선택해주세요");
		System.out.println("");
		System.out.println("1. 1달러	: 1,473원");
		System.out.println("2. 1유로	: 1,518원");
		System.out.println("3. 100엔	: 935원");
		System.out.println("4. 1위안	: 200원");
		System.out.println("===================================================");
		System.out.print("선택 : "); 
		
	}	
	
}
