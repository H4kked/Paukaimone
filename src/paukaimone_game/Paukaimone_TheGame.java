package paukaimone_game;

// Import from java libraries
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

// Import from sources
import pokemons.Pokemon;
import pokemons.Type;
import pokemons.Attack;


public class Paukaimone_TheGame {
	// Main function 
	public static void main(String[] args) {
		try {
			Pokemon[] pokemon = fLoadPokemon();
			for (int i = 0; i < pokemon.length; i++)
			{
				System.out.println(pokemon[i] + "\n");
			}
			Attack[] attack = fLoadAttacks();
			for (int i = 0; i < attack.length; i++)
			{
				System.out.println(attack[i] + "\n");
			}
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}	
	}
	
	// Function to create the pokemon from a text file
	// Returns an array of type Pokemon
	public static Pokemon[] fLoadPokemon() throws FileNotFoundException
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
	private static Type fFindType(String string) 
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
	
	private static Attack[] fLoadAttacks() throws FileNotFoundException
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
}
