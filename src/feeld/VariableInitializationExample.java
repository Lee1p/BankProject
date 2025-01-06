package feeld;

public class VariableInitializationExample {
	public static void main(String[] args) {
		
		/*
			
			========================
					쌍용은행
			========================
			1.	로그인
			2.	회원가입
			3.	계정찾기
			4.	종료
			
			
		*/
		
		로그인();
		회원가입();
		계정찾기();
		종료();
		
	}

	private static void 종료() {

		/*
			프로그램이 정상적으로 종료되었습니다.
		
		
		*/
		
		
		
		
		
	}

	private static void 계정찾기() {

		/*
			1.	ID찾기
			2.	Password찾기
			3.	초기화면 
			
		*/
		계정찾기_ID찾기();
		계정찾기_Password찾기();
		
		
	}

	private static void 계정찾기_Password찾기() {
		/*
		이름 :
		전화번호 :
		아이디 :
		---------------------------------- 분기
		비밀번호는 ~~입니다.
		---------------------------------- 분기
		없습니다.
		
		초기화면();
	*/	
	}

	private static void 계정찾기_ID찾기() {
		
		/*
			이름 :
			전화번호 :
			---------------------------------- 분기
			아이디는 ~~~입니다.
			---------------------------------- 분기
			없습니다.
			
			초기화면();
		*/	
	}

	private static void 회원가입() {

		/*
			아이디 :
			---------------------------------- 분기
				아이디가 중복됩니다.
				pause();
				회원가입();
			이름 :	
			비밀번호 :		
			비밀번호 재확인 : 
			전화번호 :	
				---------------------------------- 분기
				회원가입이 완료되었습니다.
				초기화면();
		*/
		
		
	}

	private static void 로그인() {
		
		/*
			ID : 
			Password :  
				
				---------------------------------- 분기
				
				로그인 성공
				로그인_고객();
				---------------------------------- 분기
				
				로그인 실패
				pause();
				선택1();
				
		*/
		
		로그인_고객();
		로그인_관리자();
		
		
	}

	private static void 로그인_고객() {
	
		/*
				=================================
						***님 반갑습니다.
				=================================
				1.	이체
				2.	계좌
				3.	카드
				4.	외환거래
				5.	마이페이지
				6.	고객센터
				7.	로그아웃
		*/
		
		로그인_고객_이체();
		로그인_고객_계좌();
		로그인_고객_카드();
		
//		로그인_고객_마이페이지();
		
	}

	private static void 로그인_고객_카드() {
		
		/*
		 
			1. 카드개설
			2. 카드조회
			3. 카드해지
			4. 메뉴
		
		
		*/
		
		로그인_고객_카드_카드개설();
		로그인_고객_카드_카드조회();
		로그인_고객_카드_카드해지();
		
		
		
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

	private static void 로그인_고객_카드_카드조회() {
		
		/*
			●1. S-Line 	1243-1234-1231-1234  
			●2. 카카오페이 1243-1234-1231-1234
			●3. 카카오페이 1243-1234-1231-1234
			●0. 메뉴
			
			
			상세 조회하실 카드 번호를 입력해주세요.
			
			입력:
				----------------------------------------
					●1. S-Line 	1243-1234-1231-1234  
					연회비 99,000원
					월한도 10,000,000원
					
					2024-12-30 12:44:22 55,000$ 
					2024-12-30 12:41:05 54,000$ 
					2024-12-30 12:40:00 53,000$ 
					
					1.	상세조회
					
						상세 조회하실 날짜를 입력해주세요.
						==========================================
						조회 시작 기간
						년 :
						월 :
						일 :
						
						조회 마지막 기간 
						년 :
						월 :
						일 :
							****************************************** (Y/N)
							Y
							●
							●
							●
							
							메뉴로 돌아가시려면 enter
							pause();
							로그인_고객_카드_카드조회();
							****************************************** (Y/N)
							N
							유효한 기간이 아닙니다.
							pause();
							로그인_고객_카드_카드조회();
					
					2.	메뉴
					
				----------------------------------------
				유효한 번호가 아닙니다.
				pause();
				로그인_고객_카드_카드조회();
		
		*/
		
		
	}

	private static void 로그인_고객_카드_카드개설() {
		
		
		/*
			●1.	S-Line 	연회비 99,000원	월한도 10,000,000원
			●2.	카카오페이 연회비 	  없음	월한도  2,000,000원 
			●3.	
			
			●0. 메뉴
			만드실 카드를 선택해주세요
			입력 : 
			 	----------------------------------------- (Y/N)
			 	● 저축 110-431-123123 잔액 123,123,123 // 카드 한도 이상을 가진 계좌만 출력
			 	● 저축 110-431-444444

			 	연동하실 계좌를 선택해주세요.
			 	입력 : 
			 		================================================ (Y/N)
			 		Y
			 		비밀번호로 사용하실 숫자 4자리를 입력해주세요.
			 		비밀번호 : 
			 			****************************************************** (Y/N)
			 			카드 생성이 완료되었습니다.
			 			카드번호는 1234-1243-1234-1234
			 			pause;
			 			로그인_고객_카드();
			 			
			 			******************************************************
			 			비밀번호가 유효하지 않습니다.
			 			pause();
			 			로그인_고객_카드();
			 			
			 		================================================ (Y/N)
			 		N1
			 		유효한 번호가 아닙니다.
			 		pause();
		 			로그인_고객_카드();
			 		================================================
			 		N2
			 		연동 가능한 계좌가 없습니다.
			 		pause();
		 			로그인_고객_카드();
			 		
				----------------------------------------- (Y/N)
				N
				유효한 번호가 아닙니다.
				pause();
	 			로그인_고객_카드();
		
		*/
		
	}

	private static void 로그인_고객_계좌() {

		/*
			1.	계좌 조회
			2.	계좌 개설
			3.	계좌 해지
			4.	첫 메뉴로 돌아가기
			
		
		*/
		
		로그인_고객_계좌_계좌조회();
		로그인_고객_계좌_계좌개설();
		로그인_고객_계좌_계좌해지();
		
		
		
	}

	private static void 로그인_고객_계좌_계좌해지() {
		
		/*
			●1. 	저축 110-431-123123 123,456,789$ 		
			●2. 	저축 110-431-123123 123,456,789$
			●3. 	저축 110-431-123123 123,456,789$
			●4. 	적금 110-567-123123 123,456,789$
			
			1.	선택
				--------------------------------------- (Y/N)
					선택하신 계좌
					저축 110-431-123123 123,456,789$ 		
					해지하시겠습니까?
					
					===================================== (Y/N)
						
						*************************************(Y/N) // 잔고가 0원일 때
						정상적으로 해지 되었습니다.
		 				*************************************(Y/N) // 잔고가 0원이 아닐 떄
						잔고가 남아있습니다.
						남은 금액을 송금하실 계좌를 입력해주세요.
						
							송금하실 계좌번호를 입력해주세요
			
							계좌번호 :	 
							------------------------------ 분기
							유효한
									=============================== 분기
									당행
									송금하실 계좌의 정보
									123-123124-12 당행 수수료 X
					 					********************************** 분기 (Y/N)
										Y
										송금이 정상적으로 완료되었습니다.
										해지가 완료되었습니다.
										pause();
										로그인_고객_계좌_계좌해지();
					 					********************************** 분기
										N
										송금을 취소하였습니다.
										해지가 취소되었습니다.
										로그인_고객_계좌_계좌해지();
									=============================== 분기
									타행
									123-123124-12 타행 수수료 O
									
				 						********************************** 분기 (Y/N)
										Y
										송금이 정상적으로 완료되었습니다.
										해지가 완료되었습니다.
										pause();
										로그인_고객_계좌_계좌해지();
					 					********************************** 분기
										N
										송금을 취소하였습니다.
										해지가 취소되었습니다.
										로그인_고객_계좌_계좌해지();
							------------------------------ 분기
							유효 X -- 계좌번호 유효성
							계좌번호가 유효하지 않습니다.
							해지가 취소되었습니다.
							pause();
							로그인_고객_계좌_계좌해지();
							
			2.	메뉴
		
		
		*/
		
	}

	private static void 로그인_고객_계좌_계좌개설() {
			
		
		/*
			1.	저축통장개설
				---------------------------------------
				계좌에 사용하실 비밀번호 숫자 4자리를 입력해주세요.(로그인 시 비밀번호와 다릅니다.)
				비밀번호 : 
					=========================================== (Y/N)
					Y
					개설이 완료되었습니다.
					계좌번호는 110-431-123123
					pause();
					로그인_고객_계좌();
					=========================================== (Y/N)
					N
					비밀번호가 유효하지 않습니다.
					pause();
					로그인_고객_계좌();
				
			2.	적금통장개설
				---------------------------------------
				●1. 12개월 5%  	
				●2. 24개월 10%	
				●3. 36개월 15%	
				가입하실 상품을 골라주세요.
					==========================================
					액수 : 10,000,000
					납부일 :	(1/10/20)
					
						***************************************** (Y/N)
						Y
						매달 10일 10,000,000원 으로
						12개월 5% 상품 가입 완료
						pause();
						로그인_고객_계좌_계좌개설()
						***************************************** (Y/N)
						N
						유효한 액수가 아닙니다.
						pause();
						로그인_고객_계좌_계좌개설()
					
			3.	메뉴
		
		
		
		*/
		
		
		
	}

	private static void 로그인_고객_계좌_계좌조회() {
		
		/*
			●1. 	저축 110-431-123123 123,456,789$ 		
			●2. 	저축 110-431-123123 123,456,789$
			●3. 	저축 110-431-123123 123,456,789$
			●4. 	적금 110-567-123123 123,456,789$
			
			1.	선택
				----------------------------------------
				Y
				●	123,444 홍길동 입금
					2025-01-02 12:11:46 잔액 222,333,444$
				●	123,445 아무개 출금 
					2024-12-31
				●	 45,444 카드1  출금
				
				1.	입금내역만 보기
					==========================================
					메뉴로 돌아가시려면 enter
					
				2.	출금내역만 보기
					==========================================
					메뉴로 돌아가시려면 enter
					
				3.	날짜별로 조회
					==========================================
					조회 시작 기간
					년 :
					월 :
					일 :
					
					조회 마지막 기간 
					년 :
					월 :
					일 :
						****************************************** (Y/N)
						Y
						●
						●
						●
						
						메뉴로 돌아가시려면 enter
						
						****************************************** (Y/N)
						N
						유효한 기간이 아닙니다.
						pause();
						로그인_고객_계좌_계좌조회();
					
				4. 메뉴
				
				----------------------------------------
				N
				유효한 번호가 아닙니다.
				pause();
				로그인_고객_계좌_계좌조회();
				
			2.	이전 메뉴
		
		*/
		
	}

	private static void 로그인_고객_이체() {

		/*
			●	1.
			●	2.
			●	3.

			서비스를 이용하실 통장을 골라주세요.
			
			1.	계좌이체
			2.	공과금이체
			3.	자동이체
			4.	첫 메뉴로 돌아가기 		//로그인_고객()
		*/
		
		로그인_고객_이체_계좌이체();
		로그인_고객_이체_공과금이체();
		로그인_고객_이체_자동이체();
		
		
		
	}

	private static void 로그인_고객_이체_자동이체() {
		
		/*
			1.	자동이체 설정
				------------------------------------
				계좌번호: 
				납부일: 
				액수: 
				입금자명 : 
					=================================== (Y/N)
					Y
					성공적으로 등록 완료하였습니다.
					매월 #일 ####이 이체됩니다.
					pause();
					로그인_고객_이체_자동이체()
					=================================== (Y/N)
					N
					유효하지 않은 계좌번호입니다.
					pause();
					로그인_고객_이체_자동이체();
					
					
			2.	자동이체 목록
				●	전력공사
				●	
				pause();
				로그인_고객_이체_자동이체();
				
			3.	자동이체 취소
				
				1.●	전력공사
				2.●	
				
				0.메뉴
				
				번호 입력: 
					-------------------------------------
					취소하시겠습니까?
						====================================== (Y/N)
						성공적으로 취소되었습니다.
						로그인_고객_이체_자동이체();
						====================================== (Y/N)
						메뉴로 돌아갑니다.
						pause();
						로그인_고객_이체_자동이체();
					-------------------------------------
					잘못된 번호입니다.
					pause();
					로그인_고객_이체_자동이체();
					
			4.	메뉴
					
					
		*/
		
		
	}

	private static void 로그인_고객_이체_공과금이체() {
		
		
		/*
			1.	공과금 조회
			2.	공과금 납부
			3.	첫 메뉴로 돌아가기	
		
		*/
		
		로그인_고객_이체_공과금이체_공과금조회();
		로그인_고객_이체_공과금이체_공과금납부();
		
		
		
	}

	private static void 로그인_고객_이체_공과금이체_공과금납부() {
		
		
		/*
			1. 2024-12-01 ㅇㄹ 5,000 미납
			2. 2024-12-01 ㅇㄹ 5,000 미납
			3. 2024-12-01 ㅇㄹ 5,000 미납
			
			
			1. 전부 납부
				---------------------------------------- 
				총 122,4123원 납부하시겠습니까? (Y/N)
					======================================= (Y/N)
					Y
					납부 완료
					로그인_고객_이체_공과금이체();
					=======================================
					N
					잔액 부족
					로그인_고객_이체_공과금이체();
				----------------------------------------
				납부 취소하셨습니다.
				전 메뉴로 돌아가기
				pause();
				
			2. 선택
				번호 입력:
				---------------------------------------- 
				123,124원입니다 납부 하시겠습니까?
					======================================= (Y/N)
					Y
					납부 완료
					로그인_고객_이체_공과금이체();
					=======================================
					N
					잔액 부족
					로그인_고객_이체_공과금이체();
		*/
		
	}

	private static void 로그인_고객_이체_공과금이체_공과금조회() {
		
		/*
			2024-12-01 ㅇㄹ 5,000 미납
			2024-12-01 ㅁㄴ 5,000 납부
			2024-12-01 ㅇㅇ 4,500 납부
			2024-12-01

			전 메뉴로 돌아가려면 enter 입력
			pause();
			
		*/
		
		
	}

	private static void 로그인_고객_이체_계좌이체() {

		/*
			송금하실 계좌번호를 입력해주세요
			
			계좌번호 :	 
			금액 :
				------------------------------ 분기
				유효한
					=============================== 분기
					당행
					송금하실 계좌의 정보
					123-123124-12 당행 수수료 X
						********************************** 분기 (Y/N)
						Y
						송금이 정상적으로 완료되었습니다.
						거래 후 잔액 : 123123
						pause();
						로그인_고객();
						********************************** 분기
						N
						송금을 취소하였습니다.
						로그인_고객();
					=============================== 분기
					타행
					123-123124-12 타행 수수료 O
						********************************** 분기 (Y/N)
						Y
						송금이 정상적으로 완료되었습니다.
						거래 후 잔액 : 123123
						pause();
						로그인_고객();
						********************************** 분기
						N
						송금을 취소하였습니다.
						로그인_고객();
				------------------------------ 분기
				금액이 모자랍니다.
				pause(); 
				로그인_고객_이체_계좌이체(); 
				------------------------------ 분기
				유효 X
				pause();
				로그인_고객_이체_계좌이체();
		
		*/
		
	}

	private static void 로그인_고객_마이페이지() {
	
		/*
			=================================
						***님 마이페이지
			=================================
			1.	회원탈퇴
			2.	정보수정
		
		*/
		
		
		
	}

	private static void 로그인_관리자() {
		/*		
				=================================
						관리자 모드입니다.
				=================================
				1.	고객정보조회
				2.	회원관리
				3.	카드상품
				4.	게시판관리
				5.	로그아웃
				
		*/
		
		로그인_관리자_고객정보조회();
		로그인_관리자_회원관리();
		
	}

	private static void 로그인_관리자_회원관리() {
		
		/*
			#건의 탈퇴 신청이 있습니다.
			1. 홍길동,qwe123
			2. 아무개,asd456
			
			
			1.	전부 수락
			2.	전부 거절
			3.	선택
			---------------------------------- 분기
				번호 입력: 
				*********************************** 분기
				수락하시겠습니까? (Y/N)
				
		
		
		
		*/
	}

	private static void 로그인_관리자_고객정보조회() {
		
		/*
				목록
			●	홍길동,qwe123,010-1234-1234
			●	아무개,asd456,010-4567-1233
			●	강아지,zxc444,010-2334-1234
			
			
			상세 조회 하시겠습니까? (Y/N)
			---------------------------------- 분기
			Y
			1.	이름 검색
				*********************************** 분기
				이름 입력: 
					====================================== 분기 (유효 Y/N)
					Y
					홍길동,저축통장,적금통장
					홍길동,저축통장
					====================================== 분기
					N
					목록에 없는 고객 이름.
					 
			2.	아이디 검색
				*********************************** 분기
				아이디 입력:
					====================================== 분기 (유효 Y/N)
					Y
					qwe123,저축통장,적금통장
					====================================== 분기
					N
					목록에 없는 아이디.
			3.	전화번호 검색
				*********************************** 분기
				전화번호 입력:
					====================================== 분기 (유효 Y/N)
					Y
					010-2334-1234,저축통장,적금통장
					====================================== 분기
					N
					목록에 없는 전화번호
				
				
			---------------------------------- 분기
			N
			로그인_관리자();
		
		*/
		
		
		
		
	}

	
}
