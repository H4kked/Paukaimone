package characters;

//Import form sources
import objects.Potion;
import objects.Statuspotion;
import objects.Superpotion;
import objects.Pokeball;
import pokemons.Pokemon;
import interfaces.Sell;

// Import from java
import java.util.Scanner;

public class Player extends Trainer implements Sell{
	// FIELDS
	Integer[] inventory = new Integer[4];
	int money;
	boolean isfightingpierre;
	
	// CONSTRUCTOR
	public Player(String name, String job, Pokemon pokemon, int money) {
		super(name, job, pokemon);
		money = this.money;
	}

	// GETTERS AND SETTERS
	public Integer[] getInventory() {
		return inventory;
	}
	public void setInventory(Integer[] inventory) {
		this.inventory = inventory;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public boolean getIsfightingpierre() {
		return isfightingpierre;
	}
	public void setIsfightingpierre(boolean isfightingpierre) {
		this.isfightingpierre = isfightingpierre;
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
				// THE USER HAS CHOSEN A STATUSPOTION
				Statuspotion statuspotion = new Statuspotion("statuspotion", 0);
				statuspotion.use(this.getPokemon());
				break;
			}
		}
		this.setInventory(inventory);
	}
	public void pokesteal(Opponent opponent, Scanner keyboard)
	{
		opponent.getPokemon().setPv(opponent.getPokemon().getMax_pv());
		sys_talk("You have defeated " + opponent.getName() + " !");
		sys_talk("Would you like to steal his pokemon ? (y/n)");
		char ans = 0;
		do
		{
			System.out.print("> ");
			ans = keyboard.nextLine().charAt(0);
		}
		while ((ans != 'y') && (ans != 'Y') && (ans != 'n') && (ans != 'N'));
		if (ans == 'n' || ans == 'N')
		{
			// THE USER HAS A POKEBALL
			if (this.inventory[3] > 0)
			{
				this.inventory[3]--;
				capture();
				this.getPokemon().setPv(this.getPokemon().getMax_pv());
			}
			// THE USER DOES NOT HAVE A POKEBALL
			else
			{
				sys_talk("You do not possess a pokeball to catch your fleeing pokemon.");
				ans = 'y';
			}
		}
		if (ans == 'Y' || ans == 'y')
		{
			opponent.lose_pokemon(this);
		}
	}
	public void capture()
	{
		int rand = (int) Math.random()%100;
		if (rand == 1)
		{
			sys_talk("You throw your pokeball to your fleeing pokemon...");
			sys_talk("Without success.");
			sys_talk("You see your pokemon running away, helpless.");
			sys_talk("You look where your opponent was standing a second ago, but your eyes find nobody.");
			sys_talk("\n You're standing, alone, without a single pokemon with you.");
			sys_talk("You adventure stops right here, right now. Maybe you will have more luck next time !");
			System.exit(-3);
		}
	}
	@Override
	public void sell(int i) {
		if (this.inventory[i] > 0)
		{
			this.inventory[i]--;
			switch(i)
			{
				case 1:
					this.setMoney(this.getMoney() + 15);
				case 2:
					this.setMoney(this.getMoney() + 20);
				case 3:
					this.setMoney(this.getMoney() + 15);
				case 4:
					this.setMoney(this.getMoney() + 10);
				default:
					sys_talk("Please enter a valid number.");
			}
		}
		else
		{
			sys_talk("You do not have this object.");
		}
	}
	@Override
	public void buy(int i) {
		switch(i)
		{
			case 1:
				if (this.getMoney() > 15)
				{
					this.inventory[i]++;
					this.setMoney(this.getMoney() - 15);
				}
			case 2:
				if (this.getMoney() > 20)
				{
					this.inventory[i]++;
					this.setMoney(this.getMoney() - 20);
				}
			case 3:
				if (this.getMoney() > 15)
				{
					this.inventory[i]++;
					this.setMoney(this.getMoney() - 15);
				}
			case 4:
				if (this.getMoney() > 10)
				{
					this.inventory[i]++;
					this.setMoney(this.getMoney() - 10);
				}
			default:
				sys_talk("Please enter a valid number.");
		}
	}
}
