public abstract class Item extends Entity{
    private String name;
    private String description;
    public Item(String name,String description){
        super();
        this.name = name;
        this.description = description;
    }
    // Getter - setter
    public String getName(){return this.name;}
    public void setName(String newName){this.name = newName;}
    public String getDescription(){return this.description;}
    public void setDescription(String newDescription){this.description = newDescription;}
    
}

