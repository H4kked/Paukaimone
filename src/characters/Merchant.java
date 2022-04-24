package characters;

import java.util.Scanner;

import interfaces.Sell;

public class Merchant extends Character implements Sell{
	// CONSTRUCTOR
	public Merchant(String name, String job) {
		super(name, job);
	}

	// METHODS
	@Override
	public void sell(int i) {
		talk("Sure, here you are dear customer hehe !");
	}
	@Override
	public void buy(int i) {
		talk("Wow, thank you dear customer hehe !");
		
	}
	@Override
	public void introduce()
	{
		talk("Hello my dear customer welcome welcome welcome hehe !");
		talk("I am " + this.name + " the merchant, what can I do for you hehe ?");
	}
	public void interaction_buy(Player player, Scanner keyboard)
	{
		talk("What would you like to buy hehe ? (1-4)");
		sys_talk("1 - PAUSSION 			15 ¶");
		sys_talk("2 - ZUBERPAUSSION 	20 ¶");
		sys_talk("3 - PAUKAIBAULE 		15 ¶");
		sys_talk("4 - POTIONDÉTA 		10 ¶");
		sys_talk("5 - LEAVE 		0 ¶");
		System.out.print("> ");
		char ans = 0;
		do
		{
			ans = keyboard.nextLine().charAt(0);
		}
		while (ans <= 49 || ans > 53);
		if (ans != 53)
		{
			player.buy(ans-1);
			this.sell(ans-1);
			talk("Would you like to buy something else hehe ? (y/n)");
			ans = 0;
			do
			{
					ans = keyboard.nextLine().charAt(0);
			}
			while(ans != 'y' || ans != 'Y' || ans != 'n' || ans != 'N');
			if (ans == 'y' || ans == 'Y')
			{
				interaction_buy(player, keyboard);
			}
			else
			{
				interaction_sell(player, keyboard);
			}
		}
	}
	public void interaction_sell(Player player, Scanner keyboard)
	{
		talk("What would you like to sell hehe ? (1-4)");
		sys_talk("1 - PAUSSION 	15 ¶");
		sys_talk("2 - ZUBERPAUSSION 	20 ¶");
		sys_talk("3 - PAUKAIBAULE 	15 ¶");
		sys_talk("4 - POTIONDÉTA 	10 ¶\n");
		sys_talk("5 - LEAVE 	0 ¶\n");
		sys_talk("You currently have " + player.getMoney() + " ¶.");
		System.out.print("> ");
		char ans = 0;
		do
		{
			ans = keyboard.nextLine().charAt(0);
		}
		while (ans <= 49 || ans > 53);
		if (ans != 53)
		{
			player.sell(ans-1);
			this.buy(ans-1);
			talk("Would you like to sell something else hehe ? (y/n)");
			ans = 0;
			do
			{
				ans = keyboard.nextLine().charAt(0);
			}
			while(ans != 'y' || ans != 'Y' || ans != 'n' || ans != 'N');
			if (ans == 'y' || ans == 'Y')
			{
				interaction_sell(player, keyboard);
			}
			else
			{
				talk("Goobye dear customer, it has been a pleasure hehe !");
			}
		}
	}
}
