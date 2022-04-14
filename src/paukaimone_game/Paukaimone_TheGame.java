package paukaimone_game;

// Import from java libraries

// Import from sources
import database_load.Loader;


public class Paukaimone_TheGame {
	// Main function 
	public static void main(String[] args) {
		Loader loader = new Loader();
		for (int i = 0; i < loader.getPoke_list().length; i++)
		{
			System.out.println(loader.getPoke_list()[i] + "\n");
		}
		for (int i = 0; i < loader.getAttack_list().length; i++)
		{
			System.out.println(loader.getAttack_list()[i] + "\n");
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
