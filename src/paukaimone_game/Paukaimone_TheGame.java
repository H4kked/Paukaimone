package paukaimone_game;

// Import from java libraries

// Import from sources
import database_load.Loader;
import scripts.Scripts;


public class Paukaimone_TheGame {
	// Main function 
	public static void main(String[] args) {
		Scripts script = new Scripts();
		Loader loader = new Loader();
<<<<<<< HEAD
		for (int i = 0; i < loader.getPoke_list().length; i++)
		{
			System.out.println(loader.getPoke_list()[i] + "\n");
		}
		for (int i = 0; i < loader.getAttack_list().length; i++)
		{
			System.out.println(loader.getAttack_list()[i] + "\n");
		}
=======
>>>>>>> branch 'mathis' of https://github.com/H4kked/Paukaimone
	}
}
