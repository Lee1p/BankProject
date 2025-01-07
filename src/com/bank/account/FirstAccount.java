package com.bank.account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;
import com.bank.customer.Customer;

public class FirstAccount {

    // 계좌 개설 메서드
    public static void firstAccount(Customer customerName) {
        Scanner sc = new Scanner(System.in);

        boolean loop = true;

        while (loop) {
            System.out.println("1.예금 통장 개설");
            System.out.println("------------------");
            System.out.println("계좌에 사용하실 비밀번호 숫자 4자리를 입력해주세요.(로그인 시 비밀번호와 다릅니다.)");
            System.out.print("비밀번호:");

            String passwordInput = sc.nextLine();

            // 비밀번호 유효성검사 정규식표현 숫자4자리인지 확인
            if (passwordInput.matches("^\\d{4}$")) {
                while (true) {  // 생성 여부 입력을 반복하는 새로운 루프 추가
                    System.out.println("==================생성(Y/N)");
                    System.out.print("선택:");
                    String makeAccount = sc.nextLine();

                    // Y 또는 N만 입력 받음
                    if (makeAccount.equalsIgnoreCase("Y")) {
                        System.out.println();

                        System.out.println(customerName.getName() + " 님의 계좌가 개설이 완료되었습니다.");
                        // 계좌번호 생성
                        String accountNumber = generateAccountNumber();

                        // 계좌 정보 저장 (고유번호, 계좌번호, 계좌비밀번호, 이름, 개설일자,계좌타입)
                        String typeOfAccount = "0";  // 예금 통장: 0
                        saveAccountToFile(customerName.getName(), accountNumber, passwordInput, typeOfAccount);

                        // 계좌번호 출력
                        System.out.println("계좌번호: " + accountNumber);
                        

                        // Enter 키를 눌러 메인화면으로 돌아가기
                        System.out.println("Enter키를 눌러 메인화면으로 이동합니다.");
                        sc.nextLine();  // 여기서 사용자 입력을 기다림, 사용자가 Enter 키를 눌러야 계속 진행됨

                        // 메인 화면으로 돌아가기
                        AccountSelect.Accountdisplay();  // 메인 메뉴를 다시 호출
                        break;  // 계좌 개설 완료 후 루프 종료

                    } else if (makeAccount.equalsIgnoreCase("N")) {
                        System.out.println("계좌 개설을 취소합니다.");
                        break;  // 계좌 개설 취소 후 루프 종료
                    } else {
                        System.out.println("잘못된 입력입니다. Y 또는 N만 입력해주세요.");
                        continue;  // 잘못된 입력시 다시 선택
                    }
                }
                break;  // 비밀번호가 유효하면 전체 루프 종료

            } else {
                System.out.println("잘못된 입력입니다. 4자리 숫자만 입력해주세요.");
                continue;  // 비밀번호가 잘못되었을 경우 다시 비밀번호 입력을 받음
            }
        }
    }

    // 계좌번호 생성
    private static String generateAccountNumber() {
        Random rand = new Random();
        int randomDigits = rand.nextInt(1000000);  // 6자리 랜덤 숫자 생성
        return "111-431-" + String.format("%06d", randomDigits);  // 6자리 숫자를 앞에 0을 채워서 출력
    }

    // 계좌 정보를 파일에 저장하는 메서드
    private static void saveAccountToFile(String customerName, String accountNumber, String password, String typeOfAccount) {
        String filePath = ".\\data\\account.txt";  // 파일 경로

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            int index = getNextIndex(filePath);  // 고유번호 계산
            String balance = "0";  // 잔액은 초기값으로 0으로 설정

            // 계좌 정보 저장 (고유번호, 계좌번호, 계좌비밀번호, 이름, 개설일자, 잔액, 계좌타입)
            writer.write(String.format("%d●%s●%s●%s●%s●%s●%s\r\n", index, accountNumber, password, customerName,
                    LocalDate.now().toString(), balance, typeOfAccount));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 파일에서 고유번호를 계산하는 메서드
    private static int getNextIndex(String filePath) {
        int index = 1;  // 기본값은 1로 설정

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // '●'로 구분된 문자열에서 첫 번째 부분(고유번호)을 추출
                String[] parts = line.split("●");
                try {
                    int currentIndex = Integer.parseInt(parts[0]);  // 고유번호 추출
                    index = currentIndex + 1;  // 다음 고유번호는 현재 고유번호 + 1
                } catch (NumberFormatException e) {
                    // 고유번호 파싱 실패 시 처리
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return index;  // 계산된 고유번호 반환
    }
}

