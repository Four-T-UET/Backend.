public abstract class Item extends Entity{
	private String name;
	private double basePrice;
	private User owner;
	public Item(String name, double basePrice, ){
		this.name=name;
		this.basePrice=basePrice;
	}
	public String getName(){
		return this.name;
	}
	public double getBasePrice(){
		return this.basePrice;
	}
}
	