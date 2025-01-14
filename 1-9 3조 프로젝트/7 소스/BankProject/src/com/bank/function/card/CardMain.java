package com.bank.function.card;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.bank.account.Account;
import com.bank.card.Card;
import com.bank.customer.Customer;
import com.bank.function.signin.Signin;
import com.bank.User;
import com.bank.repository.BankDAO;

/*
 
 카드 해지기능 없습니다.
 listCard() 에서 String s 대신 유저연동 필요합니다.
 userHome() 에서 상위 화면으로 돌아가는 메서드 연동 필요합니다. ** cardHome() 메서드에서 1회 사용.
 newCard() 에서 선택 1~5번 유저연 필요합니다 밑에 5번 선택지까지 동일
 
 
 data.card.txt 수정 있습니다
 com.bank.card 			패키지 Card.java 수정 있습니다
 com.bank.repository 	패키지 BankDAO.java 수정 있습니다
 
 CardProduct.java 만들긴했는데 안씁니다
 
 */
public class CardMain {
	
	public static void cardHome() {
		Scanner scan = new Scanner(System.in);
		for (int i=0; i<30; i++) {
			System.out.println();
		}
		System.out.println("===================================================");
		System.out.println("           		 카드");
		System.out.println("===================================================");
		System.out.println("1. 카드 조회");
		System.out.println("2. 카드 개설");
//		System.out.println("3. 카드 해지");
		System.out.println("0. 메뉴");
		System.out.print("번호 입력 : ");
		
		String sel = scan.nextLine();
		
		if(sel.equals("1")) {
			listCard();
		}else if(sel.equals("2")) {
			newCard();
//		}else if(sel.equals("3")) {
//			delCard();
		}else if(sel.equals("0")) {
			Signin.customerMenu(scan);
			
		}else {
			returnHome(); 
		}
//		User.getUser().getName()
		
	}

	private static void delCard() {
//		ArrayList<Card> card = BankDAO.cardList;
//		ArrayList<Account> account = BankDAO.accountList;
		Scanner scan = new Scanner(System.in);
        
        List<String> numResult = new ArrayList<>();//지정번호
        List<String> myCardNum = new ArrayList<>();//카드번호
        List<String> myCardPassword = new ArrayList<>();//카드비밀번호
        List<String> myAccount= new ArrayList<>();//연결된 계좌
        List<String> myCardCreateDay = new ArrayList<>();//개설일
        

        int counter = 1;
        
        for (Card serch : BankDAO.cardList) {//불러오는 리스트이름선언
        	for (Account a : BankDAO.accountList) {
        		System.out.println("12");
//                if (a.getCusutomerNo().equals(User.getUser().getNo())) {
                if (a.getCusutomerNo().equals(User.getUser().getNo())) {
                	if (serch.getAccountNo().equals(a.getAccountNumber())) {//리스트이름.매칭하려는객체.매칭번호
		            	numResult.add(counter++ + "");
		            	myCardNum.add(serch.getCardNumber());
		            	myCardCreateDay.add(serch.getCreateDay());//새로운 리스트.추가(리스트이름.불러오려는값)
		            }
		        }
            }
        }
        
        System.out.println("해지하실 카드의 번호를 눌러주세요");
        for (int i = 0; i < numResult.size(); i++) {
            System.out.printf("%2s. 카드번호 : %s   개설일 : %s\n"
            		,numResult.get(i)
            		,myCardNum.get(i)
            		,myCardCreateDay.get(i));
        }
        System.out.print("입력 : ");
        
        delCardSaveYN();
	}



	private static void listCard() {
		ArrayList<Card> card = BankDAO.cardList;
		ArrayList<Account> account = BankDAO.accountList;
		Scanner scan = new Scanner(System.in);
        
        List<String> numResult = new ArrayList<>();//지정번호
        List<String> myCardNum = new ArrayList<>();//카드번호
        List<String> myCardPassword = new ArrayList<>();//카드비밀번호
        List<String> myAccount= new ArrayList<>();//연결된 계좌
        List<String> myCardCreateDay = new ArrayList<>();//개설일
        

        int counter = 1;
        
        for (Card serch : card) {//불러오는 리스트이름선언
        	for (Account a : BankDAO.accountList) {
                if (a.getCusutomerNo().equals(User.getUser().getNo())) {
                	
                	if (serch.getAccountNo().equals(a.getNo())) {//리스트이름.매칭하려는객체.매칭번호
		            	
		            	numResult.add(counter++ + "");
		            	myCardNum.add(serch.getCardNumber());
		            	myCardPassword.add(serch.getPassword());//새로운 리스트.추가(리스트이름.불러오려는값)
		            	myAccount.add(a.getAccountNumber());//새로운 리스트.추가(리스트이름.불러오려는값)
		            	myCardCreateDay.add(serch.getCreateDay());//새로운 리스트.추가(리스트이름.불러오려는값)
		            }
		        }
            }
        }
        
        System.out.println("보유하신 카드 목록입니다");
        for (int i = 0; i < numResult.size(); i++) {
            System.out.printf("%2s. 카드번호 : %s   카드 비밀번호 : %s   계좌번호 : %s   개설일 : %s\n"
            		,numResult.get(i)
            		,myCardNum.get(i)
            		,myCardPassword.get(i)
            		,myAccount.get(i)
            		,myCardCreateDay.get(i));
        }
        System.out.println();
        System.out.print("카드목록이 조회되었습니다 Enter를 누르시면 이전화면으로 돌아갑니다");
        scan.nextLine();
        cardHome();

	}



	private static void userHome() {
		System.out.println("사용자 첫 화면으로 돌아갑니다. 작업해주세요");
	}



	private static void returnHome() {
			
			Scanner scan = new Scanner(System.in);
			System.out.println();
			
			System.out.println("잘못된 번호를 입력하였습니다.");
			System.out.println("이전화면으로 돌아가려면 Enter를 눌러주세요.");
			scan.nextLine();
			cardHome();
	}







	private static void newCard() {
		Scanner scan = new Scanner(System.in);

		for (int i=0; i<30; i++) {
			System.out.println();
		}
		System.out.println();
		System.out.println("====================================================");
		System.out.println("                   카 드 개 설");
		System.out.println("====================================================");
		System.out.println("1. VIP 카드		연회비 1,000,000원   월한도          없음");
		System.out.println("2. S-클래스 카드	연회비   200,000원   월한도 100,000,000원");
		System.out.println("3. C-클래스 카드	연회비    99,000원   월한도  50,000,000원");
		System.out.println("4. 비즈니스 카드	연회비    30,000원   월한도  10,000,000원 ");
		System.out.println("5. 이코노미 카드	연회비        없음   월한도   3,000,000원");
		System.out.println("0. 메뉴");
		System.out.print("번호 입력 : ");
		
		String sel = scan.nextLine(); //번호 입력 : 
		
		String newseq = getMaxSeqAdd(); //card.txt 고유번호 추가하기
		
		Calendar now = Calendar.getInstance();
		String regdate = String.format("%tF", now); //개설날자
		
		//카드개설 목록 정보
		String[] vipcard = {"VIP 카드","7777","1000000","0"};
		String[] scard= {"S-클래스 카드","7788","200000","100000000"};
		String[] ccard = {"C-클래스 카드","7111","99000","50000000"};
		String[] bizcard = {"비즈니스 카드","2255","30000","10000000"};
		String[] icocard = {"이코노미 카드","1166","0","3000000"};
		
		//카드번호 생성
		String cardnumV = NewCardNumV();
		String cardnumS = NewCardNumS(); 
		String cardnumC = NewCardNumC();
		String cardnumB = NewCardNumB();
		String cardnumI = NewCardNumI();
		
		//비밀번호 변수명 선언
		String cardPassword = "";
		
		//고유번호 ● 카드번호 ● 비밀번호 ● 계좌고유번호 ● 연회비 ● 월한도 ● 개설날자
		if(sel.equals("1")) {
			System.out.println("연결하실 계좌번호를 선택해주세요");
			String linkAccountNum = linkAccountNum("6"); //TODO "1" << Account.getcusutomerNo() 필요합니다 밑에 5번 선택지까지 동일 //계좌번호 선택, 입력, 이후 getLinkAccountNum()과 연동
			
			BankDAO.cardList.add(new Card(newseq, cardnumV, cardPassword = cardPassword(), linkAccountNum, "0","3000000",regdate));
			System.out.printf("카드이름 : VIP 카드 \n",vipcard[1]);
			System.out.printf("카드번호 : %s \n",cardnumV);
			System.out.printf("비밀번호 : %s \n",cardPassword);
			System.out.print("입력 : ");
			newCardSaveYN();
			
		}else if(sel.equals("2")) {
			System.out.println("연결하실 계좌번호를 선택해주세요");
			String linkAccountNum = linkAccountNum("6");//계좌번호 선택, 입력, 이후 getLinkAccountNum()과 연동
			
			BankDAO.cardList.add(new Card(newseq, cardnumS, cardPassword = cardPassword(), linkAccountNum, "100000000","200000",regdate));
			System.out.printf("카드이름 : C-클래스 카드 \n",scard[1]);
			System.out.printf("카드번호 : %s \n",cardnumS);
			System.out.printf("비밀번호 : %s \n",cardPassword);
			System.out.print("입력 : ");
			newCardSaveYN();

		}else if(sel.equals("3")) {
			System.out.println("연결하실 계좌번호를 선택해주세요");
			String linkAccountNum = linkAccountNum("6");//계좌번호 선택, 입력, 이후 getLinkAccountNum()과 연동
			
			BankDAO.cardList.add(new Card(newseq, cardnumC, cardPassword = cardPassword(), linkAccountNum, "50000000","99000",regdate));
			System.out.printf("카드이름 : C-클래스 카드 \n",ccard[1]);
			System.out.printf("카드번호 : %s \n",cardnumC);
			System.out.printf("비밀번호 : %s \n",cardPassword);
			System.out.print("입력 : ");
			newCardSaveYN();
			
		}else if(sel.equals("4")) {
			System.out.println("연결하실 계좌번호를 선택해주세요");
			String linkAccountNum = linkAccountNum("6");//계좌번호 선택, 입력, 이후 getLinkAccountNum()과 연동
			
			BankDAO.cardList.add(new Card(newseq, cardnumB, cardPassword = cardPassword(), linkAccountNum, "10000000","30000",regdate));
			System.out.printf("카드이름 : 비즈니스 카드 \n",bizcard[1]);
			System.out.printf("카드번호 : %s \n",cardnumB);
			System.out.printf("비밀번호 : %s \n",cardPassword);
			System.out.print("입력 : ");
			newCardSaveYN();

		}else if(sel.equals("5")) {
			System.out.println("연결하실 계좌번호를 선택해주세요");
			String linkAccountNum = linkAccountNum("6");//계좌번호 선택, 입력, 이후 getLinkAccountNum()과 연동
			
			BankDAO.cardList.add(new Card(newseq, cardnumI, cardPassword = cardPassword(), linkAccountNum, "3000000","0",regdate));
			System.out.printf("카드이름 : 이코노미 카드 \n",icocard[1]);
			System.out.printf("카드번호 : %s \n",cardnumI);
			System.out.printf("비밀번호 : %s \n",cardPassword);
			System.out.print("입력 : ");
			newCardSaveYN();

		}else if(sel.equals("0")) {
			cardHome();
		}else {
			returnHome();
		}

	}//newCard
	

	private static String linkAccountNum(String n) {
		ArrayList<Account> account = BankDAO.accountList;
		Scanner scan = new Scanner(System.in);
        
        List<String> numResult = new ArrayList<>();//지정번호
        List<String> accResult = new ArrayList<>();//계좌번호
        List<String> balResult = new ArrayList<>();//계좌잔고
        
        String target = n;//매칭하려는 값
        int counter = 1;
        
        for (Account serch : account) {//계좌를 불러오는 리스트이름선언
            if (serch.getCusutomerNo().equals(target)) {//리스트이름.매칭하려는객체.매칭번호
            	numResult.add(counter++ + "");
            	accResult.add(serch.getAccountNumber());
            	balResult.add(serch.getBalance());//새로운 리스트.추가(리스트이름.불러오려는값)
            }
        }
        
        System.out.println("카드를 개설하려는 계좌의 번호를 선택해주세요");
        for (int i = 0; i < accResult.size(); i++) {
            System.out.printf("%s번 계좌번호 : %s\t잔고 : %,15d원\n",numResult.get(i),accResult.get(i),Integer.parseInt(balResult.get(i)));
        }
        System.out.print("\n입력 : ");
        int sel = -1;
        sel = scan.nextInt()-1;
        accResult.get(sel);
        return accResult.get(sel);

	}



	private static String userNum() {
		return null;
	}



	private static String getLinkAccountNum() {
		return null;
	}

	private static void delCardSaveYN() {
		Scanner scan = new Scanner(System.in);
		boolean checkYN = false;
		
		while(!checkYN) {
			System.out.println("카드를 해지하시려면 y 아니라면 n을 눌러주세요\n");
			String input = scan.nextLine();
			if (input.equals("y")) {
				BankDAO.save();
				System.out.println("카드가 성공적으로 해지되었습니다");
				checkYN = true;
				System.out.println("Enter를 누르면 첫 화면으로 돌아갑니다");
				scan.nextLine();
				cardHome();
			}else if(input.equals("n")) {
				System.out.println("카드해지가 취소되었습니다");
				System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다");
				scan.nextLine();
				System.out.println();
				newCard();
				checkYN = true;
			}else {
				System.out.println("잘못된 입력입니다");
				System.out.println();
			}
			
		}
	}


	private static void newCardSaveYN() {
		Scanner scan = new Scanner(System.in);
		boolean checkYN = false;
		
		while(!checkYN) {
			System.out.println("카드를 개설하시려면 y 아니라면 n을 눌러주세요\n");
			String input = scan.nextLine();
			if (input.equals("y")) {
				BankDAO.save();
				System.out.println("카드가 성공적으로 개설되었습니다");
				checkYN = true;
				System.out.println("Enter를 누르면 첫 화면으로 돌아갑니다");
				scan.nextLine();
				cardHome();
			}else if(input.equals("n")) {
				System.out.println("카드개설이 취소되었습니다");
				System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다");
				scan.nextLine();
				System.out.println();
				newCard();
				checkYN = true;
			}else {
				System.out.println("잘못된 입력입니다");
				System.out.println();
			}
			
		}
	}



	private static String cardPassword() {
		Scanner scan = new Scanner(System.in);
		int cardPassword = 0;
		boolean checkPassword = false;
		
		while(!checkPassword) {
			try {
				System.out.println("비밀번호 4자리를 입력해 주세요");
				System.out.print("입력 : ");
				cardPassword = Integer.parseInt(scan.nextLine());
				if(String.valueOf(cardPassword).length() !=4) {
					System.out.println("틀린 비밀번호 입니다");
					System.out.println("Enter를 눌러 다시 입력해주세요");
					scan.nextLine();
				}else {
					checkPassword = true;
				}
			} catch (Exception e) {
				System.out.println("틀린 비밀번호 입니다");
				System.out.println("Enter를 눌러 다시 입력해주세요");
				scan.nextLine();
			}
		}
		return cardPassword +"";
	}



	private static String getDataLineForUser(String s) { //String으로 계좌고유번호 받아오기 
		//계좌번호 고유번호를 입력받아 card 몇번째 줄에 있는 데이터인지 출력
		ArrayList<Card> cardList = BankDAO.cardList;
		String userNo = s; //계좌고유번호 변수명
		int dataLineNo = -1;//찾는 값 변수명 = 없을시 -1 반환
		for (int i = 0; i < cardList.size(); i++) {  //순환하며 검색
			Card accNoObject = cardList.get(i); { //Card클래스의 = Card리스트 지정
			if (accNoObject.getAccountNo().equals(userNo)) //지정된 리스트의.계좌고유번호에서.userNo값을 찾아라
				dataLineNo = i + 1; // 찾은 줄 값 반환 break; 
			}
		}
		return dataLineNo +""; //String으로 반환
	}
	
	
	private static String NewCardNumI() {
		String[] icocard = {"이코노미 카드","1166","0","3000000"};

		String cardnumI;
        boolean duplication;
        
        do {
        	cardnumI = icocard[1]+RanCardNum();//중복검사
            duplication = false;

            for(Card cardnum : BankDAO.cardList) {
                if(cardnum.getCardNumber().equals(cardnumI)) {
                    duplication = true; //중복
                }
            }
        } while(duplication);
        
        return cardnumI;
	}



	private static String NewCardNumB() {
		String[] bizcard = {"비즈니스 카드","2255","30000","10000000"};

		String cardnumB;
        boolean duplication;
        
        do {
        	cardnumB = bizcard[1]+RanCardNum();//중복검사
            duplication = false;

            for(Card cardnum : BankDAO.cardList) {
                if(cardnum.getCardNumber().equals(cardnumB)) {
                    duplication = true; //중복
                }
            }
        } while(duplication);
        
        return cardnumB;
	}



	private static String NewCardNumC() {
		String[] ccard = {"C-클래스 카드","7111","99000","50000000"};

		String cardnumC;
        boolean duplication;
        
        do {
        	cardnumC = ccard[1]+RanCardNum();//중복검사
            duplication = false;

            for(Card cardnum : BankDAO.cardList) {
                if(cardnum.getCardNumber().equals(cardnumC)) {
                    duplication = true; //중복
                }
            }
        } while(duplication);
        
        return cardnumC;
	}
	
	private static String NewCardNumS() {
		String[] scard= {"S-클래스 카드","7788","200000","100000000"};
		
		String cardnumS;
        boolean duplication;
        
        do {
        	cardnumS = scard[1]+RanCardNum();//중복검사
            duplication = false;

            for(Card cardnum : BankDAO.cardList) {
                if(cardnum.getCardNumber().equals(cardnumS)) {
                    duplication = true; //중복
                }
            }
        } while(duplication);
        
        return cardnumS;
	}
	
	private static String NewCardNumV() {
		
		String[] vipcard = {"VIP 카드","7777","1000000","0"};
		
		String cardnumV;
        boolean duplication;
        
        do {
        	cardnumV = vipcard[1]+RanCardNum();//중복검사
            duplication = false;

            for(Card cardnum : BankDAO.cardList) {
                if(cardnum.getCardNumber().equals(cardnumV)) {
                    duplication = true; //중복
                }
            }
        } while(duplication);
        
        return cardnumV;
	}

	private static String RanCardNum() {
		Random cardNumber = new Random();
		return String.format("-%04d-%04d-%04d"
								, cardNumber.nextInt(9999)
								, cardNumber.nextInt(9999)
								, cardNumber.nextInt(9999));
	}


	private static String getMaxSeq() {//고유목록 최대 번호 확인
		return Integer.parseInt(BankDAO.cardList.get(BankDAO.cardList.size()-1).getNo()) + "";
	}

	private static String getMaxSeqAdd() {//고유목록 최대 번호 +1 만들기
		return Integer.parseInt(BankDAO.cardList.get(BankDAO.cardList.size()-1).getNo()) + 1 + "";
	}

	private static void 로그인_고객_카드_카드해지() {

		/*		
			●1. S-Line 	1243-1234-1231-1234  
			●2. 카카오페이 1243-1234-1231-1234
			●3. 카카오페이 1243-1234-1231-1234
			●0. 메뉴
			
			해지하실 카드를 선택해주세요
			입력 :
				---------------------------------------- 
				카드 비밀번호를 입력해주세요
					========================================
					성공적으로 해지되었습니다.
					pause();
					로그인_고객_카드();
					
					========================================
					비밀번호가 틀렸습니다.
					pause();
					로그인_고객_카드();
				
				----------------------------------------
				유효한 번호가 아닙니다.
				pause();
				로그인_고객_카드()
				
		*/
	}
}