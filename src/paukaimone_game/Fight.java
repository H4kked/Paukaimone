package paukaimone_game;

import java.util.Scanner;

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
	public void fight(Player player, Opponent opponent)
	{
		Scanner keyboard = new Scanner(System.in);
		if (player.getPokemon().getVit() >= opponent.getPokemon().getVit())
		{
			while (player.getPokemon().getPv() > 0 && opponent.getPokemon().getPv() > 0)
			{
				// TODO DISPLAY THE LIST OF THE ATTACKS OF THE POKEMON
				char ans = 0;
				sys_talk("Choose your attack : ");
				while (ans != 49 || ans != 50 || ans != 51 || ans != 52)
				{
					ans = keyboard.nextLine().charAt(0);
				}
				ans--;
				sys_talk(player.getPokemon().getName() + " uses " + player.getPokemon().getAttack()[ans] + " !");
				opponent.getPokemon().ouch(calculateDmg(player, opponent, player.getPokemon().getAttack()[ans]));
				
				if (player.getPokemon().getPv() <= 0 || opponent.getPokemon().getPv() <= 0)
				{
					break;
				}
				
				// TODO DISPLAY THE LIST OF THE ATTACKS OF THE POKEMON
				ans = (char)((int) Math.random() * (51 - 48) + 48);
				sys_talk(opponent.getPokemon().getName() + " uses " + opponent.getPokemon().getAttack()[ans] + " !");
				player.getPokemon().ouch(calculateDmg(opponent, player, opponent.getPokemon().getAttack()[ans]));
				
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
				// TODO DISPLAY THE LIST OF THE ATTACKS OF THE POKEMON
				char ans = (char)((int) Math.random() * (51 - 48) + 48);
				sys_talk(opponent.getPokemon().getName() + " uses " + opponent.getPokemon().getAttack()[ans] + " !");
				player.getPokemon().ouch(calculateDmg(opponent, player, opponent.getPokemon().getAttack()[ans]));

				if (player.getPokemon().getPv() <= 0 || opponent.getPokemon().getPv() <= 0)
				{
					break;
				}
				
				// TODO DISPLAY THE LIST OF THE ATTACKS OF THE POKEMON
				ans = 0;
				sys_talk("Choose your attack : ");
				while (ans != 49 || ans != 50 || ans != 51 || ans != 52)
				{
					ans = keyboard.nextLine().charAt(0);
				}
				ans--;
				sys_talk(player.getPokemon().getName() + " uses " + player.getPokemon().getAttack()[ans] + " !");
				opponent.getPokemon().ouch(calculateDmg(player, opponent, player.getPokemon().getAttack()[ans]));
				
				if (player.getPokemon().getPv() <= 0 || opponent.getPokemon().getPv() <= 0)
				{
					break;
				}
			}
		}
		keyboard.close();
	}
	public static int calculateDmg(Trainer attacker, Trainer defenser, Attack attack)
	{
		attacker.getPokemon().say_atck(attack);
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
		int number = (int) Math.random() %20;
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
		int number = (int) Math.random() % 100;
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
