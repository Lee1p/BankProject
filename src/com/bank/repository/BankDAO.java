package com.bank.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.bank.account.Account;
import com.bank.customer.Customer;

public class BankDAO {

    private final static String account;
    private final static String customer;
    public static ArrayList<Customer> customertList;
    public static ArrayList<Account> accountList;

    static {
        account = ".\\data\\account.txt";
        customer = ".\\data\\customer.txt";
        customertList = new ArrayList<Customer>();
        accountList = new ArrayList<Account>();
    }

    public static void load() {
        loadAccounts();
        loadCustomers();
    }

    private static void loadAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(account))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split("●");
                Account account = new Account(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
                accountList.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadCustomers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(customer))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split("●");
                Customer customer = new Customer(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], new ArrayList<>(), new ArrayList<>());
                customertList.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        saveAccounts();
        saveCustomers();
    }

    private static void saveAccounts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(account))) {
            for (Account account : accountList) {
                // 계좌 타입(typeOfAccount) 값을 0 또는 1로 저장
                writer.write(String.format("%s●%s●%s●%s●%s●%s●%s\r\n", account.getNo(), account.getAccountNumber(),
                        account.getPassword(), account.getCusutomerNo(), account.getOpenDate(), account.getBalance(),
                        account.getTypeOfAccount()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveCustomers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(customer))) {
            for (Customer customer : customertList) {
                writer.write(String.format("%s●%s●%s●%s●%s●%s●%s\r\n", customer.getNo(), customer.getName(),
                        customer.getId(), customer.getPassword(), customer.getPhoneNumber(), customer.getOpenDate()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getNextIndex(String filePath) {
        int index = 1;  // 기본값을 1로 설정

        // 파일이 존재하고 내용이 있으면 마지막 고유번호를 찾습니다.
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String lastLine = null;
            String line;
            while ((line = reader.readLine()) != null) {
                lastLine = line;  // 마지막 줄을 계속 저장
            }

            if (lastLine != null) {
                // 마지막 줄에서 고유번호를 추출하여 인덱스 + 1을 반환
                String[] parts = lastLine.split("●");
                index = Integer.parseInt(parts[0]) + 1;  // 고유번호는 첫 번째 항목
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return index;
    }
}
