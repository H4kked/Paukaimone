package objects;

public abstract class Object {
	
	// FIELDS
	String name;
	int price;
	
	// CONSTRUCTOR
	public Object(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	// GETTERS AND SETTERS
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
