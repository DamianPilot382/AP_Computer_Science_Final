package me.stockMarket.main;

import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

public class PurchasedStock {
	private int amount;
	private String symbol;
	@SuppressWarnings("unused")
	private BigDecimal dollarCostAverage;

	public PurchasedStock(String symbol, int amount) {
		this.amount = amount;
		this.symbol = symbol;
		try {
			dollarCostAverage = YahooFinance.get(symbol).getQuote().getPrice();
		} catch(IOException e) {
			e.printStackTrace();
		}
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
