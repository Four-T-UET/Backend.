public interface Bidder {
    void placeBid(Auction a,double price);
	void update(Auction a, AuctionEvent b, String mesage);
		
//    void setAutoBid(Auction a, double maxBid, double increment);
}

