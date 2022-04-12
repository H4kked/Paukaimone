package pokemons;

public enum Type {
	FIRE("fire"),
	GRASS("grass"),
	ICE("ice"),
	DRAGON("dragon"),
	GROUND("ground"),
	FLYING("flying"),
	ELECTRIC("electric"),
	WATER("water"),
	NORMAL("normal");
	
	String type;

	private Type(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
