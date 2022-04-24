package characters;

import java.util.Scanner;

// IMPORT FROM SOURCES
import interfaces.Heal;
import pokemons.Pokemon;

public class Medic extends Character implements Heal {

	// CONTRUCTOR
	public Medic(String name, String job) {
		super(name, job);
		// TODO Auto-generated constructor stub
	}

	// METHODS
	@Override
	public void heal(Pokemon pokemon) {
		// the medic make the pokemon's hp full
		pokemon.setPv(pokemon.getMax_pv());		
	}
	public void interaction(Player player, Scanner keyboard)
	{
		talk("Would you like me to heal your pokemon ? ^^ (y/n)");
		char ans = 0;
		do
		{
			ans = keyboard.nextLine().charAt(0);
		}
		while ((ans != 'y') && (ans != 'Y') && (ans != 'n') && (ans != 'N'));
		if (ans == 'y' || ans == 'Y')
		{
			heal(player.getPokemon());
			talk("Here you are, little Paukaimone. Thank you for passing by, see you soon ! ^^");
		}
		else
		{
			talk("Oh, ok. Well, see you soon ! ^^");
		}
	}
}
