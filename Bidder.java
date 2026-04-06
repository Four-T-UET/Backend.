public interface Bidder {
    void placeBid(Auction a,double price){
		a.setCurentWinner(this,price);
//    void setAutoBid(Auction a, double maxBid, double increment);
}

