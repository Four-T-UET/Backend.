package model;

import java.time.LocalDateTime;
public class BidTransaction extends Entity {
    private final Bidder bidder;
    private final double amount;
    private LocalDateTime time;
    public BidTransaction(Bidder bidder, double amount){
        super();
        this.bidder=bidder;
        this.amount=amount;
        this.time=LocalDateTime.now();
    }


}
	