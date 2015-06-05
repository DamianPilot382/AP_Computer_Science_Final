package me.stockMarket.main;
import java.math.BigDecimal;
import java.util.ArrayList;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;


public class Portfolio
{
	private BigDecimal balance;
	private ArrayList<PurchasedStock> stocks;
	
	public Portfolio(BigDecimal balance)
	{
		this.balance = balance;
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
			AccountCheck.setUserBalance(balance);
			for(PurchasedStock p: stocks)
			{
				if(p.getSymbol().equals(symbol))
				{
					p.addAmount(amount);
					return;
				}
			}
			setStocks();
			
			ArrayList<String> adding = new ArrayList<String>();
			adding.add(symbol);
			adding.add(amount + "");
			AccountCheck.addUserStocks(adding);
		} else {
			throw new NoBalanceException(); 
		}
	}
	
	public void sellStock(String symbol, int amount)
	{
		
		ArrayList<String> adding = new ArrayList<String>();
		adding.add(symbol);
		adding.add(amount + "");
		try{
		AccountCheck.sellUserStocks(adding);
		}catch(Exception e){
			throw new IllegalArgumentException("No funds");
		}
		
					balance = balance.add(YahooFinance.get(symbol).getQuote().getPrice().multiply(new BigDecimal(amount)));
					AccountCheck.setUserBalance(balance);

					setStocks();		
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
	
	private void setStocks(){
		ArrayList<PurchasedStock> s = new ArrayList<PurchasedStock>();
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 0; i < temp.size(); i += 2){
			s.add(new PurchasedStock(temp.get(i), Integer.parseInt(temp.get(2))));
		}
		
		stocks = s;
		
	}
	
	
}
