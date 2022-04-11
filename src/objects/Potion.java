package objects;

// IMPORT FROM SOURCES
import interfaces.Heal;
import pokemons.Pokemon;

public class Potion extends Object implements Heal {
	
	// CONSTRUCTOR
	public Potion(String name, int price) {
		super(name, price);
	}

	// METHODS
	@Override
	public void heal(Pokemon pokemon) {
		pokemon.regen(20);
	}
}
