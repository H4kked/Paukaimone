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
		talk("I challenge you with my " + this.getPokemon() + " !");
	}
	public void lose_pokemon(Player player)
	{
		talk("No way ! I lost !");
		talk(this.getPokemon() + " ? What are you doing ? Hey !");
		player.setPokemon(this.getPokemon());
		sys_talk("You obtained " + this.getName() + "'s pokemon, " + this.getPokemon() + " !");
	}
	public void attack()
	{
		int rand = (int) Math.random()%4;
		
	}
}
