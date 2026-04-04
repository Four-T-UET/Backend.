public abstract class User extends Entity{
	private String id;
	private String username;
	private String password;
	private String email;
	public User(String id,String name,String password,String email){
		this.id=id;
		this.name=name;
		this.password=password;
		this.email=email;
	}
	public String getName(){
		return this.name;
	}
	public String getId(){
		return this.id;
	}
	public String getEmail(){
		return this.email;
	}
}