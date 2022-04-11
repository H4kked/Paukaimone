package characters;

import interfaces.Sell;

public class Merchant extends Character implements Sell{
	// FIELDS
	Integer[] inventory = new Integer[4];
	
	// CONSTRUCTOR
	public Merchant(String name, String job) {
		super(name, job);
		// TODO Auto-generated constructor stub
	}

	// METHODS
	@Override
	public void sell() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void buy() {
		// TODO Auto-generated method stub
		
	}

	
}
