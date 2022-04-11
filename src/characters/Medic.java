package characters;

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
		pokemon.setPv(pokemon.getMax_pv());		
	}
}
