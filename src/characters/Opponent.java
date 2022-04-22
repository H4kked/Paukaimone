package characters;

// IMPORT FROM SOURCES
import pokemons.Pokemon;

public class Opponent extends Trainer{

	// CONSTRUCTORS
	public Opponent(String name, String job, Pokemon pokemon) {
		super(name, job, pokemon);
	}
	
	// METHODS
	public void challenge()
	{
		talk("I challenge you with my " + this.getPokemon().getName() + " !");
		this.getPokemon().talk(this.getPokemon().getName() + " !");
	}
	public void lose_pokemon(Player player)
	{
		// the player's instance "pokemon" is set to the instance "pokemon" of the opponent's one
		talk("No way ! I lost !");
		talk(this.getPokemon().getName() + " ? What are you doing ? Hey !\n");
		player.setPokemon(this.getPokemon());
		sys_talk("You obtained " + this.getName() + "'s pokemon, " + this.getPokemon().getName() + " !\n");
		sys_talk(this.getPokemon() + "\n");
	}
}
