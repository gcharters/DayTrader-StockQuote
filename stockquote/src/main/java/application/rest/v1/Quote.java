package application.rest.v1;

import java.math.BigDecimal;

public class Quote {

	private String symbol;
	private BigDecimal price;
	private String companyName;
	private double volume;
	private BigDecimal open1;
	private BigDecimal low;
	private BigDecimal high;
	
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String getPrice() {
		return price.toPlainString();
	}
	
	public void setPrice(String price) {
		this.price = new BigDecimal(price);
	}	
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public String getOpen1() {
		return open1.toPlainString();
	}

	public void setOpen1(BigDecimal open1) {
		this.open1 = open1;
	}
	
	public void setOpen1(String open1) {
		this.open1 = new BigDecimal(open1);
	}
	
	public String getLow() {
		return low.toPlainString();
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}
	
	public void setLow(String low) {
		this.low = new BigDecimal(low);
	}
	
	public String getHigh() {
		return high.toPlainString();
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	
	public void setHigh(String high) {
		this.high = new BigDecimal(high);
	}
}
