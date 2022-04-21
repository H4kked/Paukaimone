package paukaimone_game;

// Import from java libraries

// Import from sources
import database_load.Loader;
import java.util.Scanner;

import scripts.Script;

public class Paukaimone_TheGame {
	// Main function 
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		Script script = new Script();
		Loader loader = new Loader(keyboard);
		script.fStartAdventure(loader.getPlayer());
		script.fChoosePokemon(loader.getPlayer(), loader.getStarter(), keyboard);
		script.game(loader, keyboard);
	}
}
