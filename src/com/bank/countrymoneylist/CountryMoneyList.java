package com.bank.countrymoneylist;

//1●2000●50000●3000●3000
//고유번호 ● 달러 ● 유로 ● 엔화 ● 위안


public class CountryMoneyList {

	private String no;
	private String dollar;
	private String euro;
	private String yen;
	private String yuan;
	
	public CountryMoneyList(String no, String dollar, String euro, String yen, String yuan) {
		super();
		this.no = no;
		this.dollar = dollar;
		this.euro = euro;
		this.yen = yen;
		this.yuan = yuan;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getDollar() {
		return dollar;
	}

	public void setDollar(String dollar) {
		this.dollar = dollar;
	}

	public String getEuro() {
		return euro;
	}

	public void setEuro(String euro) {
		this.euro = euro;
	}

	public String getYen() {
		return yen;
	}

	public void setYen(String yen) {
		this.yen = yen;
	}

	public String getYuan() {
		return yuan;
	}

	public void setYuan(String yuan) {
		this.yuan = yuan;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CountryMoneyHistory [no=");
		builder.append(no);
		builder.append(", dollar=");
		builder.append(dollar);
		builder.append(", euro=");
		builder.append(euro);
		builder.append(", yen=");
		builder.append(yen);
		builder.append(", yuan=");
		builder.append(yuan);
		builder.append("]");
		return builder.toString();
	}
	
	
}
