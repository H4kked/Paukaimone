package pokemons;

public class Attack {
	// FIELDS
	private int precision;
	private int power;
	private String name;
	private Type type;
	private String effect;
	
	// CONSTRUCTOR
	public Attack(int precision, int power, String name, Type type, String effect) {
		this.precision = precision;
		this.power = power;
		this.name = name;
		this.type = type;
		this.effect  = effect;
	}
	
	// GETTERS AND SETTERS
	public int getPrecision() {
		return precision;
	}
	public int getPower() {
		return power;
	}
	public String getName() {
		return name;
	}
	public Type getType() {
		return type;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(Type type) {
		this.type = type;
	}

	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}

	// OVERRIDED METHODS
	@Override
	public String toString() {
		return this.getName() + "\n" + this.getType() + "\nPWR : " + this.getPower() + "   PRC : " + this.getPrecision();
	}
		
}
