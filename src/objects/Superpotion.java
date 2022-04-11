package objects;

// IMPORT FROM SOURCES
import interfaces.Heal;
import pokemons.Pokemon;

public class Superpotion extends Object implements Heal {
	
	// CONSTRUCTOR
	public Superpotion(String name, int price) {
		super(name, price);
	}

	// METHODS
	@Override
	public void heal(Pokemon pokemon) {
		pokemon.regen(50);
	}
}
