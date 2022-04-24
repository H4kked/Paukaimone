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
			//TimeUnit.SECONDS.sleep(4);
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void fShortWait()
	{
		try {
			//TimeUnit.SECONDS.sleep(2);
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// the fight is a game of back and forth with the player and the opponent
	// however, as the player was getting completely destroyed by the first opponent,
	// we doubled the damage dealt by the player
	public void fight(Player player, Opponent opponent, Scanner keyboard)
	{
		// if the player's pokemon's speed is higher, he will play first
		// if not, he will play second
		if (player.getPokemon().getVit() >= opponent.getPokemon().getVit())
		{
			// the fight lasts as long as none of the pokemon has hp equals or below 0
			// this is checked also after every attack so that no pokemon attacks after being defeated
			while (player.getPokemon().getPv() > 0 && opponent.getPokemon().getPv() > 0)
			{
				this.fShortWait();
				sys_talk("\n" + player.getPokemon().getName() + " has " + player.getPokemon().getPv() + " PV.");
				sys_talk(opponent.getPokemon().getName() + " has " + opponent.getPokemon().getPv() + " PV.\n");
				this.fShortWait();
				char ans = 0;
				
				// check if the status of the pokemon isn't NO, if so propose to the player to use a status potion if he has one
				// so he can play on this turn
				if ( (!"NO".equals(player.getPokemon().getStatus())) && (player.getInventory()[3] > 0) )
				{
					sys_talk("Your PAUKAIMONE is " + player.getPokemon().getStatus() + ", would you like to use a STATUSSPAUSSION ?");
					do
					{
						sys_talk("> ");
						ans = keyboard.nextLine().charAt(0);
					}
					while((ans != 'y') || (ans != 'Y') || (ans != 'n') || (ans != 'N'));
					if ((ans != 'y') || (ans != 'Y'))
					{
						player.useObject(3);
					}
				}
				
				// same if he'd like to heal his pokemon before playing
				if ((player.getInventory()[0] > 0) || (player.getInventory()[1] > 0))
					{
					ans = ' ';
					sys_talk("Would you like to use a PAUSSION ?");
					do
					{
						ans = keyboard.nextLine().charAt(0);
					}
					while((ans != 'y') || (ans != 'Y') || (ans != 'n') || (ans != 'N'));
					if ((ans == 'y') || (ans == 'Y'))
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
				}
				
				if ("NO".equals(player.getPokemon().getStatus()))
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
					// this method calculates the amount of hp left to the opponent's pokemon (ouch)
					// based on the damage dealt by the player's attack (calculate Dmg)
					// then set the status of the pokemon to the effect of the attack
					opponent.getPokemon().ouch(1000 * calculateDmg(player, opponent, player.getPokemon().getAttack()[choice]));
					sys_talk("You dealt " + 1000 * calculateDmg(player, opponent, player.getPokemon().getAttack()[choice]) + " damages.\n");
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
				sys_talk(opponent.getPokemon().getStatus());
				if ("NO".equals(opponent.getPokemon().getStatus()))
				{
					ans = (char)((int) (Math.random() * (51 - 48) + 48));
					int choice = (int) ans - 48;
					this.fShortWait();
					opponent.talk(opponent.getPokemon().getName() + " ! Attack " + opponent.getPokemon().getAttack()[choice].getName() + " !");
					opponent.getPokemon().talk(opponent.getPokemon().getName() + " !");
					this.fShortWait();
					// this method calculates the amount of hp left to the player's pokemon (ouch)
					// based on the damage dealt by the opponent's attack (calculate Dmg)
					// then set the status of the pokemon to the effect of the attack
					player.getPokemon().ouch(calculateDmg(opponent, player, opponent.getPokemon().getAttack()[choice]));
					sys_talk("You've been dealt " + calculateDmg(opponent, player, opponent.getPokemon().getAttack()[choice]) + " damages.\n");
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
				this.fWait();
				sys_talk("\n" + player.getPokemon().getName() + " has " + player.getPokemon().getPv() + " PV.");
				sys_talk(opponent.getPokemon().getName() + " has " + opponent.getPokemon().getPv() + " PV.\n");
				this.fShortWait();
				if ("NO".equals(opponent.getPokemon().getStatus()))
				{
					// the opponent's attack is chosen randomly
					char ans = (char)((int) (Math.random() * (51 - 48) + 48));
					int choice = (int) ans - 48;
					opponent.talk(opponent.getPokemon().getName() + " ! Attack " + opponent.getPokemon().getAttack()[choice].getName() + " !");
					opponent.getPokemon().talk(opponent.getPokemon().getName() + " !");
					this.fShortWait();
					// this method calculates the amount of hp left to the player's pokemon (ouch)
					// based on the damage dealt by the opponent's attack (calculate Dmg)
					// then set the status of the pokemon to the effect of the attack
					player.getPokemon().ouch(calculateDmg(opponent, player, opponent.getPokemon().getAttack()[choice]));
					sys_talk("You've been dealt " + calculateDmg(opponent, player, opponent.getPokemon().getAttack()[choice]) + " damages.\n");
					player.getPokemon().setStatus(opponent.getPokemon().getAttack()[choice].getEffect());
					sys_talk("1");
				}
				else
				{
					sys_talk("Your opponent's PAUKAIMONE is " + opponent.getPokemon().getStatus() + " , he has to rest and skip this turn.");
					opponent.getPokemon().setStatus("NO");
				}
				sys_talk("2");
				if ((player.getPokemon().getPv() <= 0) || (opponent.getPokemon().getPv() <= 0))
				{
					break;
				}
				
				char ans = 0;
				
				if ( (!"NO".equals(player.getPokemon().getStatus())) && (player.getInventory()[3] > 0) )
				{
					sys_talk("Your PAUKAIMONE is " + player.getPokemon().getStatus() + ", would you like to use a STATUSSPAUSSION ?");
					do
					{
						ans = keyboard.nextLine().charAt(0);
					}
					while((ans != 'y') || (ans != 'Y') || (ans != 'n') || (ans != 'N'));
					if ((ans != 'y') || (ans != 'Y'))
					{
						player.useObject(3);
					}
				}
				sys_talk("3");
				if ((player.getInventory()[0] > 0) || (player.getInventory()[1] > 0))
				{
					sys_talk("Would you like to use a PAUSSION ?");
					ans = ' ';
					do
					{
						sys_talk("> ");
						ans = keyboard.nextLine().charAt(0);
					}
					while((ans != 'y') || (ans != 'Y') || (ans != 'n') || (ans != 'N'));
					if ((ans == 'y') || (ans == 'Y'))
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
				}
				sys_talk("4");
				sys_talk(player.getPokemon().getStatus());
				if ("NO".equals(player.getPokemon().getStatus()))
				{
					ans = 0;
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
					// this method calculates the amount of hp left to the opponent's pokemon (ouch)
					// based on the damage dealt by the player's attack (calculate Dmg)
					// then set the status of the pokemon to the effect of the attack
					opponent.getPokemon().ouch(1000 * calculateDmg(player, opponent, player.getPokemon().getAttack()[choice]));
					sys_talk("You dealt " + 1000 * calculateDmg(player, opponent, player.getPokemon().getAttack()[choice]) + " damages.\n");
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
		// this method calculates the damage dealt based on the attack of the pokemon and the power of the attack
		// times a multiplicator and over the defense of the pokemon receiving the attack
		float multiplicator = (float) isEfficient(attack, attacker.getPokemon()) * isCritical(attack) * isWorking(attack);
		float numerator = (float) 2.4 * attacker.getPokemon().getAtk() * attack.getPower();
		float denumerator = 50 * defenser.getPokemon().getDef();
		int dmg = (int) ((int) ( (numerator / denumerator) + 2 ) * multiplicator);
		return dmg;
	}
	public static float isEfficient(Attack attack, Pokemon pokemon)
	{
		// this method is part of the multiplicator, damage can be double or divided by 2
		// depending on the type of the attack and of the pokemon receiving the attack
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
		// there's a 1 over 20 chance that the damage dealt is doubled
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
		// and there is a chance that the attack is not working
		// based on the precision of the attack
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
