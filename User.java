public abstract class User extends Entity {
    private String username;
    private String password;
    public User( String username, String password){
        super();
        this.username = username;
        this.password = password;
    }
    public boolean login(String name, String pass){
        return (this.username.equals(name)) && (this.password.equals(pass));
    }

}

