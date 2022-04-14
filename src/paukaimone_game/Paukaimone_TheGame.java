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
	}
}
