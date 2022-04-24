package database_load;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import pokemons.Attack;
import pokemons.Pokemon;
import pokemons.Type;
import characters.Opponent;
import characters.Player;
import characters.Medic;
import characters.Merchant;

public class Loader {
	private Attack[] attack_list;
	private Pokemon[] poke_list;
	private Pokemon[] starter;
	private Opponent[] opponent_list;
	private Opponent[] master_list;
	private Player player;
	private Merchant merchant;
	private Medic medic;
	
	public Loader(Scanner keyboard) {
		// we create those ones "manually" because there are just one of them per class
		this.merchant = new Merchant("Benoit", "Merchant");
		this.medic = new Medic("Mathilde", "Nurse");
		this.setStarter(new Pokemon[3]);
		try {
			this.setAttack_list(this.fLoadAttacks());
			this.setPoke_list(this.fLoadPokemon());
			this.fMatchAttack();
			this.fLoadTrainers(keyboard);
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
	public Opponent[] getOpponent_list() {
		return opponent_list;
	}
	public Opponent[] getMaster_list() {
		return master_list;
	}
	public Player getPlayer() {
		return player;
	}
	public void setOpponent_list(Opponent[] opponent_list) {
		this.opponent_list = opponent_list;
	}
	public void setMaster_list(Opponent[] master_list) {
		this.master_list = master_list;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public Medic getMedic() {
		return medic;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public void setMedic(Medic medic) {
		this.medic = medic;
	}

	public Pokemon[] getStarter() {
		return starter;
	}

	public void setStarter(Pokemon[] starter) {
		this.starter = starter;
	}

	// Function to create the pokemon from a text file
	// Returns an array of type Pokemon
	public Pokemon[] fLoadPokemon() throws FileNotFoundException
	{
		Pokemon[] poke_list = new Pokemon[12]; // We can use an array because our game use don't need to remove pokemon
		Pokemon[] starter = new Pokemon[3];
		String path = "./pokemon_db.txt";
		File pokemon_db = new File(path);
		Scanner db_reader = new Scanner(pokemon_db);
		String my_line = "";
		int count_pokemon = 0;
		int count_starter = 0;
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
			
			if (my_arr[0].equals("Bulbizarre") || my_arr[0].equals("Carapuce") || my_arr[0].equals("Ouisticram"))
			{
				starter[count_starter] = new Pokemon(my_arr[0], Integer.parseInt(my_arr[1]), Integer.parseInt(my_arr[2]), Integer.parseInt(my_arr[3]), Integer.parseInt(my_arr[4]), Integer.parseInt(my_arr[5]), type, my_arr[7]);
				count_starter++;
			}
			
			poke_list[count_pokemon] = new Pokemon(my_arr[0], Integer.parseInt(my_arr[1]), Integer.parseInt(my_arr[2]), Integer.parseInt(my_arr[3]), Integer.parseInt(my_arr[4]), Integer.parseInt(my_arr[5]), type, my_arr[7]);
			count_pokemon++;
		}
		db_reader.close();
		this.setStarter(starter);
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
		// as the pokemons, the attackas are extracted from a file and put into an array
		// of array then dispatch between pokemons
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
		Integer[][] combolist = {
				{2,4,9,10}, 
				{4,7,8,11}, 
				{1,2,3,4}, 
				{2,4,5,6}, 
				{6,12,14,16}, 
				{6,9,17,18}, 
				{4,12,16,17}, 
				{12,14,16,18}, 
				{11,12,13,14},
				{2,4,7,8},
				{1,12,15,16},
				{1,12,13,14}
			};
		
		for (int poke_index = 0; poke_index < this.getPoke_list().length; poke_index++)
		{
			Attack[] attack_list = new Attack[4];
			for (int attack_index = 0; attack_index < 4; attack_index++)
			{
				attack_list[attack_index] = this.getAttack_list()[combolist[poke_index][attack_index] - 1];
			}
			this.getPoke_list()[poke_index].setAttack(attack_list);
		}
	}
	public void fLoadTrainers(Scanner keyboard) throws FileNotFoundException
	{
		// put the trainers into a list while starting the game
		// as well as the player (that why we have to start the game)
		Opponent[] opponent_list = new Opponent[9];
		Opponent[] master_list = new Opponent[2];
		String path = "./opponent_db.txt";
		File opponent_db = new File(path);
		Scanner db_reader = new Scanner(opponent_db);
		String my_line = db_reader.nextLine();
		int count_opponent = 0;
		
		while (db_reader.hasNextLine())
		{
			my_line = db_reader.nextLine();
			String[] my_arr = my_line.split("/", 2);
			
			if (my_arr[0].equals("Player"))
			{
				System.out.println("\nPr Layton : But enough about me, what is your name again ?");
				System.out.print("> ");
				String name = keyboard.nextLine();
				System.out.println("\nPr Layton : Oh " + name + ", and what is the best word to describe yourself ?");
				System.out.print("> ");
				String job = keyboard.nextLine();
				this.setPlayer(new Player(name, job, this.getPoke_list()[9], 0));
				Integer[] list = {0, 0, 1, 0};
				this.getPlayer().setInventory(list);
			}
			else if (my_arr[0].equals("Pierre")) 
			{
				master_list[0] = new Opponent(my_arr[0], my_arr[1], this.getPoke_list()[10]);
			}
			else if (my_arr[0].equals("Mathis"))
			{
				master_list[1] = new Opponent(my_arr[0], my_arr[1], this.getPoke_list()[11]);
			}
			else 
			{
				opponent_list[count_opponent] = new Opponent(my_arr[0], my_arr[1], this.getPoke_list()[count_opponent]);
				count_opponent++;
			}
		}
		db_reader.close();
		this.setOpponent_list(opponent_list);
		this.setMaster_list(master_list);
	}
}
