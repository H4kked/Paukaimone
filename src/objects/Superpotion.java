package objects;

import interfaces.Heal;
import pokemons.Pokemon;

public class Superpotion extends Object implements Heal {
	public Superpotion(String name, int price) {
		super(name, price);
	}

	@Override
	public void heal(Pokemon pokemon) {
		pokemon.regen(50);
	}
}
