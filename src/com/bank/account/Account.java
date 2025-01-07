package com.bank.account;

public class Account {

    private String no;
    private String accountNumber;
    private String password;
    private String cusutomerNo;
    private String openDate;
    private String balance; //잔고
    private String typeOfAccount;  // 예금(0), 적금(1)

    public Account(String no, String accountNumber, String password, String cusutomerNo, String openDate,
                   String balance, String typeOfAccount) {
        this.no = no;
        this.accountNumber = accountNumber;
        this.password = password;
        this.cusutomerNo = cusutomerNo;
        this.openDate = openDate;
        this.balance = balance;
        this.typeOfAccount = typeOfAccount;
    }

    // Getters and Setters
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCusutomerNo() {
        return cusutomerNo;
    }

    public void setCusutomerNo(String cusutomerNo) {
        this.cusutomerNo = cusutomerNo;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    @Override
    public String toString() {
        return "Account [no=" + no + ", accountNumber=" + accountNumber + ", password=" + password + ", cusutomerNo="
                + cusutomerNo + ", openDate=" + openDate + ", balance=" + balance + ", typeOfAccount=" + typeOfAccount + "]";
    }
}

