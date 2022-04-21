package scripts;

import java.io.IOException;
import java.util.Scanner;

import java.util.concurrent.TimeUnit;
import characters.Player;
import database_load.Loader;
import paukaimone_game.Fight;
import characters.Merchant;
import characters.Opponent;
import characters.Medic;
import pokemons.Pokemon;

public class Script {
	public Script(){
		fBeginningScript();
	}
	
	public void sys_talk(String say)
	{
		System.out.println(say + "\n");
	}
	
	public void fWait()
	{
		try {
			TimeUnit.SECONDS.sleep(4);
			//TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void fShortWait()
	{
		try {
			TimeUnit.SECONDS.sleep(2);
			//TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fBeginningScript()
	{
		System.out.println("??? : Hello there! Welcome to the world of PAUKAIMONE ! My name is LAYTON !");
		this.fWait();
		System.out.println("\nPr Layton : People call me the PAUKAIMONE PROF !");
		this.fWait();
		System.out.println("\nPr Layton : This world is inhabited by creatures called PAUKAIMONE !");
		this.fWait();
		System.out.println("\nPr Layton : For some people, PAUKAIMONE are pets, Others use them for fights.");
		this.fWait();
		System.out.println("\nPr Layton : As for me... ");
		this.fShortWait();
		System.out.println("\nPr Layton : I study PAUKAIMONE as a profession.");
		this.fWait();
	}
	public void fStartAdventure(Player player)
	{
		System.out.println("\nPr Layton : " + player.getName() + ", Your very own PAUKAIMONE legend is about to unfold !");
		this.fWait();
		System.out.println("\nPr Layton : A world of dreams and adventures with PAUKAIMONE awaits ! Let's go !");
		this.fWait();
	}

	public void fChoosePokemon(Player player, Pokemon[] starter, Scanner keyboard) {
		System.out.println("\nPr Layton : Hey " + player.getName() + ", there are 3 PAUKAIMONE here !");
		this.fWait();
		System.out.println("\nPr Layton : They are inside the PAUKAIBAULES !");
		this.fWait();
		System.out.println("\nPr Layton : There are only 3 left but you can have one ! Choose !");
		this.fShortWait();
		for (int j = 0; j < 3; j++)
		{
			sys_talk("");
			for (int i = 0; i<starter.length; i++)
			{
				if (starter[i] != null)
				{
					sys_talk((i + 1) + " - The " + starter[i].getType() + " PAUKAIMONE, " + starter[i].getName());
				}
			}
			this.fShortWait();
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
					System.out.println("\nPr Layton : Nope, this PAUKAIMONE is mine.");
					starter[(int) choice - 49] = null;
					break;
				case "Bulbizarre":
					sys_talk("\nYou scared Bulbizarre...");
					this.fWait();
					System.out.print("\nPr Layton : It seems Bulbizarre is scared of you... ");
					this.fWait();
					System.out.println("I can't let you take this PAUKAIMONE please choose another one.");
					starter[(int) choice - 49] = null;
					break;
				case "Carapuce":
					sys_talk("\nCarapuce immediately ran away when seeing you...");
					this.fWait();
					System.out.print("\nPr layton : Hum... ");
					this.fWait();
					System.out.println("I wasn't expecting that...");
					starter[(int) choice - 49] = null;
					break;
				default:
					System.out.println("UNEXPECTED ERROR");
					break;
			}
			this.fWait();
		}
		System.out.println("\nPr Layton : Seems like no PAUKAIMONE wants to be yours...");
		this.fWait();
		System.out.println("\nPr Layton : I have an idea. Here take this Pikachu !");
		sys_talk("\nYou obtain a Pikachu ! ");
		sys_talk(player.getPokemon().toString());
		this.fWait();
		System.out.println("\nPr Layton : Before you go, I have a last question for you.");
		this.fWait();
		System.out.println("\nPr Layton : Do you prefer 1 or 2 ?");
		char choice = 48;
		do {
			System.out.print("> ");
			choice = keyboard.nextLine().charAt(0);
		} while (choice <= 48 || choice > 50);
		if ((int) choice - 48 == 1)
		{
			player.setIsfightingpierre(true);
		}
		else 
		{
			player.setIsfightingpierre(false);
		}
		
		System.out.println("\nPr Layton : Well, seems to me that you are ready to go on an adventure !");
		this.fWait();
		System.out.println("\nPr Layton : You may encounter a lot of different people.");
		this.fWait();
		System.out.println("\nPr Layton : Don't be scared and you might become the very best !");
		this.fWait();
	}
	
	public void game(Loader load, Scanner keyboard)
	{
		Fight fight = new Fight();
		for (int i = 0; i < load.getOpponent_list().length; i++)
		{
			this.fWait();
			sys_talk("\n\nYou walk a few miles, when you someone calls you.");
			load.getOpponent_list()[i].introduce();
			load.getOpponent_list()[i].challenge();
			
			fight.fight(load.getPlayer(), load.getOpponent_list()[i], keyboard);
			
			if (load.getPlayer().getPokemon().getPv() <= 0)
			{
				sys_talk("You have been defeated. You were not the very best.");
				sys_talk("GAME OVER");
				System.exit(-4);
			}
			load.getPlayer().pokesteal(load.getOpponent_list()[i], keyboard);
			load.getPlayer().setMoney(load.getPlayer().getMoney() + 10);
			sys_talk("\n You obtained 10 ¶ !\n");
			
			switch(i)
			{
				case 2, 6:
					load.getMerchant().introduce();
					load.getMerchant().interaction_buy(load.getPlayer(), keyboard);
					break;
				case 3, 8:
					load.getMedic().introduce();
					load.getMedic().interaction(load.getPlayer(), keyboard);
					break;
			}
		}
		
		
		System.out.println("\nPr Layton : Hello again, " + load.getPlayer().getName() + " !");
		this.fWait();
		System.out.println("\nPr Layton : You really made your way there, huh ?");
		this.fWait();
		System.out.println("\nPr Layton : I'm proud of you, " + load.getPlayer().getName() + ".");
		this.fWait();
		System.out.println("\nPr Layton : You really deserves to be called " + load.getPlayer().getName() + " the " + load.getPlayer().getJob() + ".");
		this.fWait();
		System.out.println("\nPr Layton : But your adventure is not finished yet !");
		this.fWait();
		System.out.println("\nPr Layton : Did you really think you could leave without challenging the PAUKAIMONE MASTERS ?");
		this.fWait();
		System.out.println("\nPr Layton : Which MASTER would you like to compete against ?");
		this.fWait();
		sys_talk("1 - PIERRE THE POKAIMONE GRAND MASTER WHICH HAS BEEN UNDEFEATED FOR THE LAST 8 GENERATIONS");
		sys_talk("    WITH HIS LVL 100 PALKIA THE LEGENDARY PAUKAIMONE THAT HAS BEEN DRUGGED WITH STEROIDS");
		sys_talk("2 - MATHIS and his POUSSIFEU");
		keyboard.nextLine();
		System.out.println("\nPr Layton : I'm joking, you already made your choice. In the beginning of the game...");
		this.fWait();
		
		if (load.getPlayer().getIsfightingpierre() == true)
		{
			System.out.println("\nPr Layton : Since your favorite number is 1...");
			this.fWait();
			System.out.println("\nPr Layton : YOU WILL BE FIGHTING PIERRE !!");
			this.fWait();
			fight.fight(load.getPlayer(), load.getMaster_list()[0], keyboard);
		}
		else
		{
			System.out.println("\nPr Layton : Since your favorite number is 2...");
			this.fWait();
			System.out.println("\nPr Layton : YOU WILL BE FIGHTING MATHIS !!");
			this.fWait();
			fight.fight(load.getPlayer(), load.getMaster_list()[1], keyboard);
		}
		
		System.out.println("\nPr Layton : No way ! You made it !");
		this.fWait();
		System.out.println("\nPr Layton : That's unbelievable. You are the youngest PAUKAIMONE MASTER of History !");
		this.fWait();
		System.out.println("\nPr Layton : Congratulation. Here, take this !");
		this.fWait();
		sys_talk("You obtained the JAVA's ARENA BADGE.");
		this.fWait();
		System.out.println("\nPr Layton : Well, I believe you've reached the end of the adventure.");
		this.fWait();
		System.out.print("\nPr Layton : Or...");
		this.fShortWait();
		System.out.println("Did you ?");
		this.fWait();
		sys_talk("Pr LAYTON put a PAUKAIBAULE out of his pocket and throws it in the air !");
		sys_talk("It looks like it is openning !");
		sys_talk("BOOOOOM !!");
		sys_talk("A magnifiscent firework cracked out of the fireball, lightening the sky.");
		sys_talk("Soon, every trainer you've encountered during this adventure is near the professor, clapping, shouting your name.");
		sys_talk("Congratulations " + load.getPlayer().getName() + " !");
		sys_talk("THE END");
	}
}