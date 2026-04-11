import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.UUID;
public class Auction extends Entity{
    private AuctionStatus status;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private Item product;
    private double currentPrice;
    private double miniumStep;
    private Bidder currentWinner;
	private List<Bidder> observers=new ArrayList<>();
    private HashMap<String,BidTransaction> list=new HashMap<>();
    public Auction(Item product, double currentPrice, double miniumStep){
        super();
        this.product=product;
        this.currentPrice=currentPrice;
        this.miniumStep=miniumStep;
        this.status=AuctionStatus.PENDING;
        this.startTime=LocalDateTime.now();
        this.finishTime=startTime.plusDays(1);
    }
	public void registerObserver(Bidder bidder){
		if(!observers.contains(bidder)){
			observers.add(bidder);
		}
	}
    public boolean setCurentWinner(Bidder bidder,double price){
        if (this.status != AuctionStatus.OPEN){
			return false;
		}
		if(this.currentPrice + miniumStep<=price){
            Bidder oldWinner = this.currentWinner;
			//update new winner
			this.currentWinner=bidder;
            this.currentPrice=price;
            this.addBidTransaction(bidder,price);
			//add observer
			this.registerObserver(bidder);
			
			notifyObservers(AuctionEvent.PRICE_UPDATED, "Gia da duoc cap nhat: "+price);
			return true;	
        }
		else{
			return false;
		}
    }
    public void addBidTransaction(Bidder bidder, double price){
        BidTransaction temp=new BidTransaction(bidder,price);
        list.put(temp.getId(),temp);
    }
	public double getMiniumStep(){
		return this.miniumStep;
	}
	public double getCurrentPrice(){
		return this.currentPrice;
	}
	public void notifyObservers(AuctionEvent event, String message){
		for (Bidder observer: observers){
			observer.update(this,event,message);
		}
	}
	public Bidder getCurrentWinner(){
		return this.currentWinner;
	}


}