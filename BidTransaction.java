import java.time.LocalDateTime;
public class BidTransaction extends Entity{
    private Clients bidder;
    private double amount;
    private LocalDateTime time;
    public BidTransaction(Clients bidder,double amount){
        super();
        this.bidder=bidder;
        this.amount=amount;
        this.time=LocalDateTime.now();
    }
    public void getInfo(){
        System.out.println(this.bidder+"-"+this.amount+"-"+this.time.toString());
    }
}
	