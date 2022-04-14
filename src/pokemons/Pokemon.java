package pokemons;

public class Pokemon {
	// FIELDS
	private String name;
	protected int pv;
	private int max_pv;
	private int def;
	private int atk;
	private int vit;
	private Type type;
	private String status; 
	private Attack[] attack;
	
	// CONSTRUCTOR
	public Pokemon(String name, int pv, int def, int atk, int vit, int max_pv, Type type, String status) {
		this.name = name;
		this.pv = pv;
		this.def = def;
		this.atk = atk;
		this.vit = vit;
		this.type = type;
		this.max_pv = max_pv;
		this.status = status;
		this.attack = new Attack[4];
	}

	// GETTERS AND SETTERS
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPv() {
		return pv;
	}
	public void setPv(int pv) {
		this.pv = pv;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getVit() {
		return vit;
	}
	public void setVit(int vit) {
		this.vit = vit;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public int getMax_pv() {
		return max_pv;
	}
	public void setMax_pv(int max_pv) {
		this.max_pv = max_pv;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Attack[] getAttack() {
		return attack;
	}

	public void setAttack(Attack[] attack) {
		this.attack = attack;
	}

	// METHODS
	public void talk(String string)
	{
		System.out.println(this.getName() + " : " + string);
	}
	public void sys_talk(String say)
	{
		System.out.println(say);
	}
	public void regen(int pv) {
		int new_pv;
		new_pv = this.getPv() + pv;
		if (new_pv > this.getMax_pv())
		{
			this.setPv(this.getMax_pv());
		}
		else
		{
			this.setPv(new_pv);
		}
	}
	public boolean P_flee(Pokemon adv)
	{
		float flee;
		flee = (this.getVit() * 32) / ((adv.getVit()/4)%255) + 30; 
		if (flee > 255)
		{
			return true;
		}
		else
		{
			flee = (float) Math.random()%255;
			if (flee > 255)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	public void ouch(int pv)
	{
		int pv_restants;
		pv_restants = this.getPv() - pv;
		this.setPv(pv_restants);
	}
	public void say_atck(Attack atck)
	{
		sys_talk(this.name + " uses " + atck.getName() + ".");
	}
	public void fDisplayAttack()
	{
		for (int i = 0; i < this.getAttack().length; i++)
		{
			System.out.println(this.getAttack()[i].getName());
		}
	}

	// OVERRIDED METHODS
	@Override
	public String toString() {
		return this.getName() + "\nPV : " + this.getPv() + "/" + this.getMax_pv() + "\nStatus : " + this.getStatus() + "\nATK : " + this.getAtk() + "    DEF : " + this.getDef() + "\nVIT : " + this.getVit();
	}
	
}
