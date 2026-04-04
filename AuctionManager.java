import java.util.*;
public class AuctionManager{
	private HashMap<String,Auction> ds=new HashMap<>();
	public void add(Auction auction){
		this.ds.put(auction.getId(),auction);
	}
}