import java.util.*;
public class AuctionManager{
	private static volatile AuctionManager instance;
	public static AuctionManager getInstance(){
		if (instance==null){
			synchronized (AuctionManager.class){
				if (instance==null){
					instance=new AuctionManager();
				}
			}
		}
		return instance;
	}
	private HashMap<String,Auction> ds=new HashMap<>();
	public void add(Auction auction){
		this.ds.put(auction.getId(),auction);
	}
}