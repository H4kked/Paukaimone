package database_load;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import pokemons.Attack;
import pokemons.Pokemon;
import pokemons.Type;

public class Loader {
	private Attack[] attack_list;
	private Pokemon[] poke_list;
	
	public Loader() {
		this.attack_list = new Attack[18];
		this.poke_list = new Pokemon[12];
		try {
			this.setAttack_list(this.fLoadAttacks());
			this.setPoke_list(this.fLoadPokemon());
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("ERROR WHILE LOADING, CANNOT FIND FILES");
			System.exit(-1);
		}
	}
	
	public Pokemon[] getPoke_list() {
		return poke_list;
	}
	public void setPoke_list(Pokemon[] poke_list) {
		this.poke_list = poke_list;
	}
	public Attack[] getAttack_list() {
		return attack_list;
	}
	public void setAttack_list(Attack[] attack_list) {
		this.attack_list = attack_list;
	}

	// Function to create the pokemon from a text file
	// Returns an array of type Pokemon
	public Pokemon[] fLoadPokemon() throws FileNotFoundException
	{
		Pokemon[] poke_list = new Pokemon[12]; // We can use an array because our game use don't need to remove pokemon
		String path = "./pokemon_db.txt";
		File pokemon_db = new File(path);
		Scanner db_reader = new Scanner(pokemon_db);
		String my_line = "";
		int count_pokemon = 0;
		db_reader.nextLine(); // This line is the example of how the pokemon have to be stored so we can skip it
		
		while (db_reader.hasNextLine()) 
		{
			my_line = db_reader.nextLine();
			String[] my_arr = my_line.split("/", 8);
			Type type = fFindType(my_arr[6]);
			
			if (type == null)
			{
				System.exit(-1);
			}
			
			poke_list[count_pokemon] = new Pokemon(my_arr[0], Integer.parseInt(my_arr[1]), Integer.parseInt(my_arr[2]), Integer.parseInt(my_arr[3]), Integer.parseInt(my_arr[4]), Integer.parseInt(my_arr[5]), type, my_arr[7]);
			count_pokemon++;
		}
		db_reader.close();
		return poke_list; 
	}
	public Type fFindType(String string) 
	{
		Type type = Type.NORMAL;
		switch (string)
		{
		case "fire":
			type = Type.FIRE;
			break;
		case "grass":
			type = Type.GRASS;
			break;
		case "ice":
			type = Type.ICE;
			break;
		case "dragon":
			type = Type.DRAGON;
			break;
		case "ground":
			type = Type.GROUND;
			break;
		case "flying":
			type = Type.FLYING;
			break;
		case "electric":
			type = Type.ELECTRIC;
			break;
		case "water":
			type = Type.WATER;
			break;
		case "normal":
			type = Type.NORMAL;
			break;
		default:
			System.out.println("TYPE NOT FOUND");
			type = null;
		}
		
		
		return type;
	}
	public Attack[] fLoadAttacks() throws FileNotFoundException
	{
		Attack[] attack_list = new Attack[18];
		String path = "./attaque_db.txt";
		File attack_db = new File(path);
		Scanner db_reader = new Scanner(attack_db);
		String my_line = db_reader.nextLine();;
		int count_attack = 0;
		
		while (db_reader.hasNextLine())
		{
			my_line = db_reader.nextLine();
			String[] my_arr = my_line.split("/", 5);
			Type type = fFindType(my_arr[3]);
			
			if (type == null) 
			{
				System.exit(-1);
			}
			
			attack_list[count_attack] = new Attack(Integer.parseInt(my_arr[2]), Integer.parseInt(my_arr[1]), my_arr[0], type, my_arr[4]);
			count_attack++;
		}
		db_reader.close();		
		return attack_list;
	}
	public void fMatchAttack() {
		// TODO combolist
	}
	
}
