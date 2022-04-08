package characters;

import pokemons.Pokemon;

public abstract class Trainer extends Character{
	Pokemon pokemon;
	
	public Trainer(String name, String job, Pokemon pokemon) {
		super(name, job);
		this.pokemon = pokemon;
	}

	public Pokemon getPokemon() {
		return pokemon;
	}
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
}
