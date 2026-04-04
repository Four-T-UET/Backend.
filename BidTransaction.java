import java.time.LocalDateTime;
public class BidTransaction extends Entity{
	private Bidder bidder;
	private double amount;
	private LocalDateTime time;
	public BidTransaction(String id,Bidder bidder,double amount){
		super(id);
		this.bidder=bidder;
		this.amount=amount;
		this.time=LocalDateTime.now();
	}
	public void getInfo(){
		System.out.println(this.bidder+"-"+this.amount+"-"+this.time.toString());
	}
}
	