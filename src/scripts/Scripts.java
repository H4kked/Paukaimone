package scripts;

import java.io.IOException;
import java.util.Scanner;

import java.util.concurrent.TimeUnit;
import characters.Player;
import characters.Merchant;
import characters.Opponent;
import characters.Medic;
import pokemons.Pokemon;

public class Scripts {
	public Scripts(){
		fBeginningScript();
	}
	
	public void sys_talk(String say)
	{
		System.out.println(say);
	}
	
	public void fWait()
	{
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void fShortWait()
	{
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fBeginningScript()
	{
		System.out.println("??? : Hello there! Welcome to the world of PAUKAIMONE! My name is LAYTON!");
		this.fWait();
		System.out.println("\nPr Layton : People call me the PAUKAIMONE PROF!");
		this.fWait();
		System.out.println("\nPr Layton : This world is inhabited by creatures called PAUKAIMONE!");
		this.fWait();
		System.out.println("\nPr Layton : For some people, PAUKAIMONE are pets, Others use them for fights.");
		this.fWait();
		System.out.println("\nPr Layton : As for me... ");
		this.fWait();
		System.out.println("\nPr Layton : I study PAUKAIMONE as a profession.");
		this.fWait();
	}
	public void fStartAdventure(Player player)
	{
		System.out.println("\nPr Layton : " + player.getName() + ", Your very own PAUKAIMONE legend is about to unfold!");
		this.fWait();
		System.out.println("\nPr Layton : A world of dreams and adventures with PAUKAIMONE awaits! Let's go!");
		this.fWait();
	}

	public void fChoosePokemon(Player player, Pokemon[] starter, Scanner keyboard) {
		System.out.println("\nPr Layton : Hey " + player.getName() + ", there are 3 PAUKAIMONE here!");
		this.fWait();
		System.out.println("\nPr Layton : They are inside the PAUKAIBALLS!");
		this.fWait();
		System.out.println("\nPr Layton : There are only 3 left but you can have one! Choose!\n");
		this.fWait();
		for (int j = 0; j < 3; j++)
		{
			int count = 0;
			for (int i = 0; i<starter.length; i++)
			{
				if (starter[i] != null)
				{
					sys_talk((i + 1) + " - The " + starter[i].getType() + " PAUKAIMONE, " + starter[i].getName());
				}
			}
			this.fWait();
			System.out.println("\nPr Layton : Which pokemon do you want ?");
			char choice = 49;
			do {
				do {
					System.out.print("> ");;
					choice = keyboard.nextLine().charAt(0);
				} while (choice <= 48 || choice > 51);
			} while (starter[(int) choice - 49] == null);
			
			switch (starter[(int) choice - 49].getName())
			{
				case "Ouisticram":
					System.out.println("\nPr Layton : Nope, this PAUKAIMONE is mine");
					starter[(int) choice - 49] = null;
					break;
				case "Bulbizarre":
					sys_talk("\nYou scared Bulbizarre");
					this.fWait();
					System.out.print("\nPr Layton : It seems Bulbizarre is scared of you... ");
					this.fWait();
					System.out.println("I can't let you take this PAUKAIMONE please choose another one");
					starter[(int) choice - 49] = null;
					break;
				case "Carapuce":
					sys_talk("\nCarapuce immediately ran away when seeing you");
					this.fWait();
					System.out.print("\nPr layton : Hum... ");
					this.fWait();
					System.out.println("I wasn't expecting that");
					starter[(int) choice - 49] = null;
					break;
				default:
					System.out.println("UNEXPECTED ERROR");
					break;
			}
		}
		keyboard.close();
	}
}
