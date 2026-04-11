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
		//check money
		if (wallet.getBalance() + wallet.getLockBalance() < price){
			System.out.println("Khong du tien trong tai khoan");
			return;
		}
		boolean isBidValid=a.setCurentWinner(this,price);
		//Lockmoney
		if (isBidValid){
			//lockbalance
			wallet.lockWallet(price);
			System.out.println("Dat gia thanh cong");
		}
		else{
			System.out.println("Dat gia that bai, so tien dat khong hop le");
		}
	}
			
			

    
	public void update(Auction a, AuctionEvent eventType, String message){
		//print notification
		System.out.println("Cap nhat phien: "+ message);
		// logic wallet
		if (eventType==AuctionEvent.PRICE_UPDATED){
			//check lockbalance
			boolean isMoneyLocked = this.wallet.getLockBalance() > 0;
			//check winner
			boolean amIWinner = (a.getCurrentWinner() == this);
			
			if (isMoneyLocked && !amIWinner){
				this.wallet.releaseBalance();
				System.out.println("Ban da bi vuot gia, tien da duoc tra ve tai khoan");
			}
		}
	}
				
			
			

//    @Override
//    public void setAutoBid(Auction a, double maxBid, double increment) {
//
//    }
}

