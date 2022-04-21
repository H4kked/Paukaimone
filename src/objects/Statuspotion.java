package objects;

import pokemons.Pokemon;

public class Statuspotion extends Object {
	
	// CONSTRUCTOR
		public Statuspotion(String name, int price) {
			super(name, price);
		}
		
	//METHODS
		public void use(Pokemon pokemon)
		{
			pokemon.setStatus("NO");
		}
}
