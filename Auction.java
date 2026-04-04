import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;
public class Auction extends Entity{
    private AuctionStatus status;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private Item product;
    private double currentPrice;
    private double miniumStep;
    private Bidder currentWinner;
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
    public void setCurentWinner(Clients bidder,double price){
        if(this.currentPrice + miniumStep<=price){
            String id=UUID.randomUUID().toString();
            this.currentWinner=bidder;
            this.currentPrice=price;
            this.addBidTransaction(id,bidder,price);
        }
    }
    public void addBidTransaction(String id,Clients bidder, double price){
        BidTransaction temp=new BidTransaction(bidder,price);
        list.put(temp.getId(),temp);
    }


}