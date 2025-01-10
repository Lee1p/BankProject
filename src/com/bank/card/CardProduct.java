package com.bank.card;
//2●S-클래스 카드●7771●200000●100000000
//고유번호●카드명●카드번호첫자리●연회비●결제한도
public class CardProduct {
	private String no;
	private String cardName;
	private String firstCardNumber;
	private String annualFee;
	private String limit;
	
	public CardProduct(String no, String cardName, String firstCardNumber, String annualFee, String limit) {
		super();
		this.no = no;
		this.cardName = cardName;
		this.firstCardNumber = firstCardNumber;
		this.annualFee = annualFee;
		this.limit = limit;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CardProduct [no=");
		builder.append(no);
		builder.append(", cardName=");
		builder.append(cardName);
		builder.append(", firstCardNumber=");
		builder.append(firstCardNumber);
		builder.append(", annualFee=");
		builder.append(annualFee);
		builder.append(", limit=");
		builder.append(limit);
		builder.append("]");
		return builder.toString();
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getFirstCardNumber() {
		return firstCardNumber;
	}

	public void setFirstCardNumber(String firstCardNumber) {
		this.firstCardNumber = firstCardNumber;
	}

	public String getAnnualFee() {
		return annualFee;
	}

	public void setAnnualFee(String annualFee) {
		this.annualFee = annualFee;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}
}
