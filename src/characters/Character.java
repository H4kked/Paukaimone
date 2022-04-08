package characters;

public abstract class Character {
	String name;
	String job;
	
	public Character(String name, String job) {
		super();
		this.name = name;
		this.job = job;
	}
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
	
	public void talk(String say)
	{
		System.out.println(this.getName() + " : " + say);
	}
	public void sys_talk(String say)
	{
		System.out.println(say);
	}
	public void introduce()
	{
		System.out.println("Hello ! I am " + this.getName() + " and I am a " + this.getJob() + ".");
	}
}
