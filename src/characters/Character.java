package characters;

public abstract class Character {
	
	// FIELDS
	String name;
	String job;
	
	// CONSTRUCTOR
	public Character(String name, String job) {
		super();
		this.name = name;
		this.job = job;
	}
	
	// GETTERS AND SETTERS
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	// METHODS
	public void talk(String say)
	{
		System.out.println(this.getName() + " : " + say + "\n");
	}
	public void sys_talk(String say)
	{
		System.out.println(say);
	}
	public void introduce()
	{
		System.out.println("Hello ! I am " + this.getName() + " the " + this.getJob() + ".\n");
	}
}
