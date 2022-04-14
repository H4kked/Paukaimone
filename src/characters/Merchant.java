package characters;

import interfaces.Sell;

public class Merchant extends Character implements Sell{
	// FIELDS
	Integer[] inventory = new Integer[4];
	
	// CONSTRUCTOR
	public Merchant(String name, String job) {
		super(name, job);
	}

	// METHODS
	@Override
	public void sell(int i) {
		talk("Sure, here you are dear customer !");
	}
	@Override
	public void buy(int i) {
		talk("Wow, thank you dear customer !");
		
	}
	@Override
	public void introduce()
	{
		talk("Hello my dear customer welcome welcome welcome !");
		talk("I am " + this.name + " the merchant, what can I do for you?");
	}
}
