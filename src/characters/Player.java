package characters;

//Import form sources
import objects.Potion;
import objects.Superpotion;
import pokemons.Pokemon;
import interfaces.Sell;

public class Player extends Trainer implements Sell{
	// FIELDS
	Integer[] inventory = new Integer[4];
	
	// CONSTRUCTOR
	public Player(String name, String job, Pokemon pokemon) {
		super(name, job, pokemon);
	}

	// GETTERS AND SETTERS
	public Integer[] getInventory() {
		return inventory;
	}
	public void setInventory(Integer[] inventory) {
		this.inventory = inventory;
	}

	// METHODS
	public void useObject(int i)
	{
		inventory = this.getInventory();
		if (inventory[i] == 0)
		{
			sys_talk("You don't have that item anymore !");
		}
		else
		{
			inventory[i] = inventory[i-1];
			switch(i)
			{
			case 1:
				// THE USER HAS CHOSEN A POTION
				Potion potion = new Potion("potion", 0);
				potion.heal(this.getPokemon());
				break;
			case 2:
				// THE USER HAS CHOSEN A SUPERPOTION
				Superpotion superpotion = new Superpotion("superpotion", 0);
				superpotion.heal(this.getPokemon());
				break;
			case 3:
				// THE USER HAS CHOSEN A POKEBALL
				sys_talk("You cannot use that here.");
				break;
			case 4:
				// TO DO IF STATUS POTION IMPLEMENTED
				break;
			}
		}
		this.setInventory(inventory);
	}
	public void captureOpp()
	{
		// TODO
	}
	@Override
	public void sell() {
		// TODO
		
	}
	@Override
	public void buy() {
		// TODO
		
	}
}
