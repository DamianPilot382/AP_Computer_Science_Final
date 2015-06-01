package me.stockMarket.main;

@SuppressWarnings("serial")
public class NoBalanceException extends Throwable 
{
	public NoBalanceException(){
		super("Insufficient Funds.");
	}
}
