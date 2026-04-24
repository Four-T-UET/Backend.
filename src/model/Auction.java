package model;

import enums.AuctionEvent;
import enums.AuctionStatus;

import java.time.LocalDateTime;
import java.util.*;

public class Auction extends Entity {
    private AuctionStatus status;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private Item product;
    private double currentPrice;
    private double miniumStep;
    private Bidder currentWinner;
	private List<Bidder> observers=new ArrayList<>();
    private HashMap<String,BidTransaction> BidHistory=new HashMap<>();

	// Constructor
    public Auction(Item product, double currentPrice, double miniumStep){
        super();
        this.product=product;
        this.currentPrice=currentPrice;
        this.miniumStep=miniumStep;
        this.status= AuctionStatus.OPEN;
        this.startTime=LocalDateTime.now();
        this.finishTime=startTime.plusDays(1);
    }

	// Observer đăng ký
	public void registerObserver(Bidder bidder){
		if(!observers.contains(bidder)){
			observers.add(bidder);
		}
	}
	// Thông báo cho tất cả các observer về sự thay đổi Auction
	public void notifyObservers(AuctionEvent event, String message){
		for (Bidder observer: observers){
			observer.update(this,event,message);
		}
	}

	// Hàm kiểm tra, set người chiến thắng hiện tại
    public boolean setCurrentWinner(Bidder bidder, double price){
        if (this.status != AuctionStatus.RUNNING){
			return false;
		}
		synchronized (Auction.class){ // NGon vay
			if (this.currentPrice + miniumStep <= price) {
				//update new winner
				this.currentWinner = bidder;
				this.currentPrice = price;
				this.addBidTransaction(bidder, price);
				//add observer
				this.registerObserver(bidder);

				notifyObservers(AuctionEvent.PRICE_UPDATED, "Gia da duoc cap nhat: " + price);
				return true;
			} else {
				return false;
			}
		}
    }

	// Thêm lịch sử đã giao dịch
    public void addBidTransaction(Bidder bidder, double price){
        BidTransaction temp = new BidTransaction(bidder,price);
        BidHistory.put(temp.getId(),temp);
    }


	// Getter - Setter
	public double getMiniumStep(){
		return this.miniumStep;
	}
	public double getCurrentPrice(){
		return this.currentPrice;
	}
	public Bidder getCurrentWinner(){return this.currentWinner;}
	public AuctionStatus getStatus(){
		return status;
	}
	public void setStatus(AuctionStatus status){
		this.status = status;
	}

	// --------------------------------------------------------
	// Logic chuyển trạng thái của AUCTION --------------------
	public synchronized void startAuction(){
		if(this.status == AuctionStatus.OPEN){
			this.status = AuctionStatus.RUNNING;
			System.out.println("model.Auction starting");
		}
	}
	public synchronized void finishAuction(){
		if(this.status == AuctionStatus.RUNNING){
			this.status = AuctionStatus.FINISHED;
			System.out.println("model.Auction finished");
		}
	}
	public synchronized void payingAuction(){
		if(this.status == AuctionStatus.FINISHED){
			this.status = AuctionStatus.PAID;
			System.out.println("model.Auction being paid");
		}else{
			System.out.println("Cannot paying for the item");
		}
	}
	public synchronized void cancelAuction(){
		if(this.status != AuctionStatus.PAID && this.status != AuctionStatus.CANCELLED){
			this.status = AuctionStatus.CANCELLED;
			System.out.println("Cancel to finish the model.Auction");
		}else{
			System.out.println("Cannot cancel the model.Auction");
		}
	}
}