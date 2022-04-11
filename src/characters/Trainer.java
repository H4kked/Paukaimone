package characters;

// IMPORT FROM SOURCES
import pokemons.Pokemon;

public abstract class Trainer extends Character{
	// FIELDS
	Pokemon pokemon;
	
	// CONSTRUCTOR
	public Trainer(String name, String job, Pokemon pokemon) {
		super(name, job);
		this.pokemon = pokemon;
	}

	// METHODS
	public Pokemon getPokemon() {
		return pokemon;
	}
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
}