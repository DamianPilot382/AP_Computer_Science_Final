package me.stockMarket.main;
import java.math.BigDecimal;
import java.util.ArrayList;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;


public class Portfolio
{
	private BigDecimal balance;
	private ArrayList<PurchasedStock> stocks;
	
	public Portfolio(double balance)
	{
		this.balance = new BigDecimal(balance);
		stocks = new ArrayList<PurchasedStock>();
	}
	
	public BigDecimal getBalance()
	{
		return balance;
	}
	
	public ArrayList<PurchasedStock> getStocks()
	{
		return stocks;
	}
	
	public void buyStock(String symbol, int amount) throws NoBalanceException
	{
		Stock temp = YahooFinance.get(symbol);
		
		if(balance.compareTo(temp.getQuote().getPrice().multiply(new BigDecimal(amount))) >= 0)
		{
			this.balance = balance.subtract(temp.getQuote().getPrice().multiply(new BigDecimal(amount)));
			for(PurchasedStock p: stocks)
			{
				if(p.getSymbol().equals(symbol))
				{
					p.addAmount(amount);
					return;
				}
			}
			stocks.add(new PurchasedStock(symbol, amount));
			
		} else {
			throw new NoBalanceException(); 
		}
	}
	
	@SuppressWarnings("unused")
	public void sellStock(String symbol, int amount)
	{
		for(int i = 0; i < stocks.size(); i++){
			PurchasedStock stock = stocks.get(i);
			if(stock.getSymbol().equals(symbol)){
				if(stock.getAmount() >= amount){
					stock.sell(amount);
					balance = balance.add(YahooFinance.get(symbol).getQuote().getPrice().multiply(new BigDecimal(amount)));
					if(stock.getAmount() <= 0){
						stocks.remove(i);
					}
					return; 
				}
			}
			throw new IllegalArgumentException("No Stocks Found.");
		}
		
	}
	
	public void printStocks(){
		if(stocks.size() == 0){
			System.out.println("No Stocks");
		}else{
			for(PurchasedStock s: stocks)
			{
				System.out.println("Symbol: " + s.getSymbol() + ", Amount: " + s.getAmount());
			}
		}
	}
	
	
	
	
	
	
	
}
