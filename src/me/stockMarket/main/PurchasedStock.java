package me.stockMarket.main;

import java.math.BigDecimal;

import yahoofinance.YahooFinance;

public class PurchasedStock {
	private int amount;
	private String symbol;
	@SuppressWarnings("unused")
	private BigDecimal dollarCostAverage;

	public PurchasedStock(String symbol, int amount) {
		this.amount = amount;
		this.symbol = symbol;
		dollarCostAverage = YahooFinance.get(symbol).getQuote().getPrice();
	}

	public void buy(int amount) {
		this.amount += amount;

	}

	public void sell(int amount) {
		this.amount -= amount; 
	}
	
	public String getSymbol()
	{
		return symbol;
	}

	public int getAmount(){
		return amount;
	}
	
	public void addAmount(int amount)
	{
		this.amount += amount;
	}
}
