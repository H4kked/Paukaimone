package characters;

import pokemons.Pokemon;

public class Opponent extends Trainer{

	public Opponent(String name, String job, Pokemon pokemon) {
		super(name, job, pokemon);
	}
	
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
}
