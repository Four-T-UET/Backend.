import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;
public class Auction extends Entity{
	private AuctionStatus status;
	private LocalDateTime startTime;
	private LocalDateTime finishTime;
	private Product product;
	private double currentPrice;
	private double miniumStep;
	private Bidder currentWinner;
	private HashMap<String,BidTransaction> list=new HashMap<>();
	public Auction(String id,Product product, double currentPrice, double miniumStep){
		super(id);
		this.product=product;
		this.currentPrice=currentPrice;
		this.miniumStep=miniumStep;
		this.status=AuctionStatus.PENDING;
		this.startTime=LocalDateTime.now();
		this.finishTime=startTime.plusDays(1);
	}
	public void setCurentWinner(Bidder bidder,double price){
		if(this.currentPrice + miniumStep<=price){
			String id=UUID.randomUUID().toString();
			this.currentWinner=bidder;
			this.currentPrice=price;
			this.addBidTransaction(id,bidder,price);
		}
	}
	public void addBidTransaction(String id,Bidder bidder, double price){
		BidTransaction temp=new BidTransaction(id,bidder,price);
		list.put(temp.getID(),temp);
	}
		
	
}