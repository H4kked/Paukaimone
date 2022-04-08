package objects;

import interfaces.Heal;
import pokemons.Pokemon;

public class Potion extends Object implements Heal {
	public Potion(String name, int price) {
		super(name, price);
	}

	@Override
	public void heal(Pokemon pokemon) {
		pokemon.regen(20);
	}
}
