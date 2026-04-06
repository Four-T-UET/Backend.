import java.nio.file.WatchKey;
import java.util.HashMap;

public class Clients extends User implements Bidder, Seller{
    private HashMap<String, Item> inventory = new HashMap<>();
    private Wallet wallet = new Wallet();
    public Clients(String username, String password){
        super(username, password);
    }

    @Override
    public boolean login(String name, String pass) {
        return super.login(name, pass);
    }
    public Clients register(String id, String name, String pass){
        return new Clients(name, pass);
    }

    @Override
    public void addItem(Item temp) {
        inventory.put(temp.getId(), temp);
    }

    @Override
    public void removeItem(Item temp) {
        inventory.remove(temp.getId());
    }

    @Override
    public Auction addAuction(Item temp, double firstprice, double miniumStep) {
        return new Auction(temp, firstprice, miniumStep);
    }

    @Override
    public void placeBid(Auction a, double price) {
		a.setCurentWinner(this,price);

    }

//    @Override
//    public void setAutoBid(Auction a, double maxBid, double increment) {
//
//    }
}

