package scripts;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import characters.Player;
import characters.Merchant;
import characters.Opponent;
import characters.Medic;

public class Scripts {
	public Scripts(){
		fBeginningScript();
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
	public void fBeginningScript()
	{
		System.out.println("??? : Hello there! Welcome to the world of PAUKAIMONE! My name is LAYTON!");
		this.fWait();
		System.out.println("\nPr LAYTON : People call me the PAUKAIMONE PROF!");
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
		System.out.println("Pr Layton : " + player.getName() + ", Your very own PAUKAIMONE legend is about to unfold!");
		this.fWait();
		System.out.println("Pr Layton : A world of dreams and adventures with PAUKAIMONE awaits! Let's go!");
		this.fWait();
	}
}
