package characters;

import java.util.Scanner;

import interfaces.Sell;

public class Merchant extends Character implements Sell{
	// FIELDS
	Integer[] inventory = new Integer[4];
	
	// CONSTRUCTOR
	public Merchant(String name, String job) {
		super(name, job);
	}

	// METHODS
	@Override
	public void sell(int i) {
		talk("Sure, here you are dear customer !");
	}
	@Override
	public void buy(int i) {
		talk("Wow, thank you dear customer !");
		
	}
	@Override
	public void introduce()
	{
		talk("Hello my dear customer welcome welcome welcome !");
		talk("I am " + this.name + " the merchant, what can I do for you?");
	}
	public void interaction_buy(Player player, Scanner keyboard)
	{
		talk("What would you like to buy ? (1-4)");
		sys_talk("1 - PAUSSION 			15 ¶");
		sys_talk("2 - ZUBERPAUSSION 	20 ¶");
		sys_talk("3 - PAUKAIBAULE 		15 ¶");
		sys_talk("4 - POTIONDÉTA 		10 ¶");
		System.out.print("> ");
		char ans = 0;
		do
		{
			ans = keyboard.nextLine().charAt(0);
		}
		while (ans != 49 || ans != 50 || ans != 51 || ans != 52);
		player.buy(ans);
		talk("Would you like to buy something else ? (y/n)");
		ans = 0;
		do
		{
			ans = keyboard.nextLine().charAt(0);
		}
		while(ans != 89 || ans != 121 || ans != 78 || ans != 100);
		if (ans == 89 || ans == 121)
		{
			interaction_buy(player, keyboard);
		}
		else
		{
			interaction_sell(player, keyboard);
		}
	}
	public void interaction_sell(Player player, Scanner keyboard)
	{
		talk("What would you like to sell ? (1-4)");
		sys_talk("1 - PAUSSION 			15 ¶");
		sys_talk("2 - ZUBERPAUSSION 	20 ¶");
		sys_talk("3 - PAUKAIBAULE 		15 ¶");
		sys_talk("4 - POTIONDÉTA 		10 ¶");
		System.out.print("> ");
		char ans = 0;
		do
		{
			ans = keyboard.nextLine().charAt(0);
		}
		while (ans != 49 || ans != 50 || ans != 51 || ans != 52);
		player.sell(ans);
		talk("Would you like to sell something else ? (y/n)");
		ans = 0;
		do
		{
			ans = keyboard.nextLine().charAt(0);
		}
		while(ans != 89 || ans != 121 || ans != 78 || ans != 100);
		if (ans == 89 || ans == 121)
		{
			interaction_sell(player, keyboard);
		}
	}
}
