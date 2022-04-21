package paukaimone_game;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import characters.Opponent;
import characters.Player;
import characters.Trainer;
import pokemons.Attack;
import pokemons.Pokemon;
import pokemons.Type;

public class Fight {
	public static void sys_talk(String say)
	{
		System.out.println(say);
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
	public void fight(Player player, Opponent opponent, Scanner keyboard)
	{
		if (player.getPokemon().getVit() >= opponent.getPokemon().getVit())
		{
			while (player.getPokemon().getPv() > 0 && opponent.getPokemon().getPv() > 0)
			{
				this.fShortWait();
				sys_talk("\n" + player.getPokemon().getName() + " has " + player.getPokemon().getPv() + " PV.");
				sys_talk(opponent.getPokemon().getName() + " has " + opponent.getPokemon().getPv() + " PV.\n");
				this.fShortWait();
				char ans = 0;
				
				if ( (player.getPokemon().getStatus() != "NO") && (player.getInventory()[3] > 0) )
				{
					sys_talk("Your PAUKAIMONE is " + player.getPokemon().getStatus() + ", would you like to use a STATUSSPAUSSION ?");
					do
					{
						ans = keyboard.nextLine().charAt(0);
					}
					while(ans != 'y' || ans != 'Y' || ans != 'n' || ans != 'N');
					if (ans == 'y' || ans == 'Y')
					{
						player.useObject(3);
					}
				}
				
				sys_talk("Would you like to use a PAUSSION ?");
				do
				{
					ans = keyboard.nextLine().charAt(0);
				}
				while(ans != 'y' || ans != 'Y' || ans != 'n' || ans != 'N');
				if (ans == 'y' || ans == 'Y')
				{
					sys_talk("1 - PAUSSION			(20 PV)");
					sys_talk("2 - ZUBERPAUSSION		(50 PV)");
					do
					{
						ans = keyboard.nextLine().charAt(0);
					}
					while(ans <= 48 || ans > 49);
					if (ans == 48)
					{
						player.useObject(1);
					}
					else
					{
						player.useObject(2);
					}
				}
				
				if (player.getPokemon().getStatus() == "NO")
				{
					sys_talk(player.getPokemon().getName() + "'s attacks are :");
					player.getPokemon().fDisplayAttack();
					sys_talk("Choose your attack : ");
					System.out.print("> ");
					do
					{
						ans = keyboard.nextLine().charAt(0);
					}
					while (ans <= 48 || ans > 52);
					ans--;
					int choice = (int) ans - 48;
					player.talk(player.getPokemon().getName() + " ! Attack " + player.getPokemon().getAttack()[choice].getName() + " !");
					player.getPokemon().talk(player.getPokemon().getName() + " !");
					this.fShortWait();
					opponent.getPokemon().ouch(2 * calculateDmg(player, opponent, player.getPokemon().getAttack()[choice]));
					sys_talk("You delt " + 2 * calculateDmg(player, opponent, player.getPokemon().getAttack()[choice]) + " damages.\n");
					opponent.getPokemon().setStatus(player.getPokemon().getAttack()[choice].getEffect());
				}
				else
				{
					sys_talk("Your PAUKAIMONE is " + player.getPokemon().getStatus() + " , he has to rest and skip this turn.");
					player.getPokemon().setStatus("NO");
				}
				
				if (player.getPokemon().getPv() <= 0 || opponent.getPokemon().getPv() <= 0)
				{
					break;
				}
				
				if (opponent.getPokemon().getStatus() == "NO")
				{
					ans = (char)((int) (Math.random() * (51 - 48) + 48));
					int choice = (int) ans - 48;
					this.fShortWait();
					opponent.talk(opponent.getPokemon().getName() + " ! Attack " + opponent.getPokemon().getAttack()[choice].getName() + " !");
					opponent.getPokemon().talk(opponent.getPokemon().getName() + " !");
					this.fShortWait();
					player.getPokemon().ouch(calculateDmg(opponent, player, opponent.getPokemon().getAttack()[choice]));
					sys_talk("You've been delt " + calculateDmg(opponent, player, opponent.getPokemon().getAttack()[choice]) + " damages.\n");
					player.getPokemon().setStatus(opponent.getPokemon().getAttack()[choice].getEffect());
				}
				else
				{
					sys_talk("Your opponent's PAUKAIMONE is " + opponent.getPokemon().getStatus() + " , he has to rest and skip this turn.");
					opponent.getPokemon().setStatus("NO");
				}
				
				if (player.getPokemon().getPv() <= 0 || opponent.getPokemon().getPv() <= 0)
				{
					break;
				}
			}
		}
		else
		{
			while (player.getPokemon().getPv() > 0 && opponent.getPokemon().getPv() > 0)
			{
				if (opponent.getPokemon().getStatus() == "NO")
				{
					this.fWait();
					sys_talk("\n" + player.getPokemon().getName() + " has " + player.getPokemon().getPv() + " PV.");
					sys_talk(opponent.getPokemon().getName() + " has " + opponent.getPokemon().getPv() + " PV.\n");
					this.fShortWait();
					char ans = (char)((int) (Math.random() * (51 - 48) + 48));
					int choice = (int) ans - 48;
					opponent.talk(opponent.getPokemon().getName() + " ! Attack " + opponent.getPokemon().getAttack()[choice].getName() + " !");
					opponent.getPokemon().talk(opponent.getPokemon().getName() + " !");
					this.fShortWait();
					player.getPokemon().ouch(calculateDmg(opponent, player, opponent.getPokemon().getAttack()[choice]));
					sys_talk("You've been delt " + calculateDmg(opponent, player, opponent.getPokemon().getAttack()[choice]) + " damages.\n");
					player.getPokemon().setStatus(opponent.getPokemon().getAttack()[choice].getEffect());
				}
				else
				{
					sys_talk("Your opponent's PAUKAIMONE is " + opponent.getPokemon().getStatus() + " , he has to rest and skip this turn.");
					opponent.getPokemon().setStatus("NO");
				}
			
				if (player.getPokemon().getPv() <= 0 || opponent.getPokemon().getPv() <= 0)
				{
					break;
				}
				
				char ans = 0;
				
				if ( (player.getPokemon().getStatus() != "NO") && (player.getInventory()[3] > 0) )
				{
					sys_talk("Your PAUKAIMONE is " + player.getPokemon().getStatus() + ", would you like to use a STATUSSPAUSSION ?");
					do
					{
						ans = keyboard.nextLine().charAt(0);
					}
					while(ans != 'y' || ans != 'Y' || ans != 'n' || ans != 'N');
					if (ans == 'y' || ans == 'Y')
					{
						player.useObject(3);
					}
				}
				
				sys_talk("Would you like to use a PAUSSION ?");
				do
				{
					ans = keyboard.nextLine().charAt(0);
				}
				while(ans != 'y' || ans != 'Y' || ans != 'n' || ans != 'N');
				if (ans == 'y' || ans == 'Y')
				{
					sys_talk("1 - PAUSSION			(20 PV)");
					sys_talk("2 - ZUBERPAUSSION		(50 PV)");
					do
					{
						ans = keyboard.nextLine().charAt(0);
					}
					while(ans <= 48 || ans > 49);
					if (ans == 48)
					{
						player.useObject(1);
					}
					else
					{
						player.useObject(2);
					}
				}

				if (player.getPokemon().getStatus() == "NO")
				{
					this.fWait();
					player.getPokemon().fDisplayAttack();
					sys_talk("Choose your attack : ");
					System.out.print("> ");
					while (ans <= 48 || ans > 52)
					{
						ans = keyboard.nextLine().charAt(0);
					}
					ans--;
					int choice = (int) ans - 48;
					player.talk(player.getPokemon().getName() + " ! Attack " + player.getPokemon().getAttack()[choice].getName() + " !");
					player.getPokemon().talk(player.getPokemon().getName() + " !");
					this.fShortWait();
					opponent.getPokemon().ouch(2 * calculateDmg(player, opponent, player.getPokemon().getAttack()[choice]));
					sys_talk("You delt " + 2 * calculateDmg(player, opponent, player.getPokemon().getAttack()[choice]) + " damages.\n");
					opponent.getPokemon().setStatus(player.getPokemon().getAttack()[choice].getEffect());
				}
				else
				{
					sys_talk("Your opponent's PAUKAIMONE is " + opponent.getPokemon().getStatus() + " , he has to rest and skip this turn.");
					player.getPokemon().setStatus("NO");
				}
			
				if (player.getPokemon().getPv() <= 0 || opponent.getPokemon().getPv() <= 0)
				{
					break;
				}
			}
		}
	}
	public static int calculateDmg(Trainer attacker, Trainer defenser, Attack attack)
	{
		float multiplicator = (float) isEfficient(attack, attacker.getPokemon()) * isCritical(attack) * isWorking(attack);
		float numerator = (float) 2.4 * attacker.getPokemon().getAtk() * attack.getPower();
		float denumerator = 50 * defenser.getPokemon().getDef();
		int dmg = (int) ((int) ( (numerator / denumerator) + 2 ) * multiplicator);
		return dmg;
	}
	public static float isEfficient(Attack attack, Pokemon pokemon)
	{
		if ( (attack.getType() == Type.FIRE && pokemon.getType() == Type.GRASS) || (attack.getType() == Type.FIRE && pokemon.getType() == Type.ICE) || (attack.getType() == Type.WATER && pokemon.getType() == Type.FIRE) || (attack.getType() == Type.WATER && pokemon.getType() == Type.GROUND) || (attack.getType() == Type.GRASS && pokemon.getType() == Type.WATER) || (attack.getType() == Type.GRASS && pokemon.getType() == Type.GROUND) || (attack.getType() == Type.ELECTRIC && pokemon.getType() == Type.WATER) || (attack.getType() == Type.ELECTRIC && pokemon.getType() == Type.FLYING) || (attack.getType() == Type.ICE && pokemon.getType() == Type.GRASS) || (attack.getType() == Type.ICE && pokemon.getType() == Type.GROUND) || (attack.getType() == Type.ICE && pokemon.getType() == Type.FLYING) || (attack.getType() == Type.ICE && pokemon.getType() == Type.DRAGON) || (attack.getType() == Type.GROUND && pokemon.getType() == Type.FIRE) || (attack.getType() == Type.GROUND && pokemon.getType() == Type.ELECTRIC) || (attack.getType() == Type.FLYING && pokemon.getType() == Type.GRASS) || (attack.getType() == Type.DRAGON && pokemon.getType() == Type.DRAGON) )
		{
			return 2;
		}
		else if (  (attack.getType() == Type.FIRE && pokemon.getType() == Type.FIRE) || (attack.getType() == Type.FIRE && pokemon.getType() == Type.WATER) || (attack.getType() == Type.FIRE && pokemon.getType() == Type.DRAGON) || (attack.getType() == Type.WATER && pokemon.getType() == Type.WATER) || (attack.getType() == Type.WATER && pokemon.getType() == Type.GRASS) || (attack.getType() == Type.WATER && pokemon.getType() == Type.DRAGON) || (attack.getType() == Type.GRASS && pokemon.getType() == Type.FIRE) || (attack.getType() == Type.GRASS && pokemon.getType() == Type.GRASS) || (attack.getType() == Type.GRASS && pokemon.getType() == Type.FLYING) || (attack.getType() == Type.GRASS && pokemon.getType() == Type.DRAGON) || (attack.getType() == Type.ELECTRIC && pokemon.getType() == Type.GRASS) || (attack.getType() == Type.ELECTRIC && pokemon.getType() == Type.ELECTRIC) || (attack.getType() == Type.ELECTRIC && pokemon.getType() == Type.DRAGON) || (attack.getType() == Type.ICE && pokemon.getType() == Type.FIRE) || (attack.getType() == Type.ICE && pokemon.getType() == Type.WATER) || (attack.getType() == Type.ICE && pokemon.getType() == Type.ICE) || (attack.getType() == Type.GROUND && pokemon.getType() == Type.GRASS) || (attack.getType() == Type.FLYING && pokemon.getType() == Type.ELECTRIC) || (attack.getType() == Type.DRAGON && pokemon.getType() == Type.DRAGON) )
		{
			return (float) 0.5;
		}
		else if ( (attack.getType() == Type.ELECTRIC && pokemon.getType() == Type.GROUND) || (attack.getType() == Type.GROUND && pokemon.getType() == Type.FLYING) )
		{
			return  0;
		}
		else
		{
			return 1;
		}
	}
	public static int isCritical(Attack attack)
	{
		int number = (int) Math.random() * 20;
		if (number == 1)
		{
			sys_talk("Critical hit !");
			return 2;
		}
		else
		{
			return 1;
		}
	}
	public static int isWorking(Attack attack)
	{
		int number = (int) Math.random() * 100;
		if (number <= attack.getPrecision())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
}
