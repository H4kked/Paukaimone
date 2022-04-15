package paukaimone_game;

// Import from java libraries

// Import from sources
import database_load.Loader;
import java.util.Scanner;

import scripts.Scripts;


public class Paukaimone_TheGame {
	// Main function 
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		Scripts script = new Scripts();
		Loader loader = new Loader(keyboard);
		script.fStartAdventure(loader.getPlayer());
		script.fChoosePokemon(loader.getPlayer(), loader.getStarter(), keyboard);
	}
}
