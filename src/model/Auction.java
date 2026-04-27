package model;

import enums.AuctionEvent;
import enums.AuctionStatus;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Auction extends Entity {
    private AuctionStatus status;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private Item product;
    private double currentPrice;
    private double miniumStep;
    private Bidder currentWinner;
	private HashSet<Bidder> observers = new HashSet<>();
    private HashMap<String,BidTransaction> BidHistory=new HashMap<>();
	private ReentrantLock bidLock = new ReentrantLock();
	// Constructor
    public Auction(Item product, double currentPrice, double miniumStep){
		if(currentPrice < 0){
			System.out.println("Giá tiền không hợp lệ, vui lòng nhập lại: "); // thêm ngoại lệ
		}
        super();
        this.product=product;
        this.currentPrice=currentPrice;
        this.miniumStep=miniumStep;
        this.status= AuctionStatus.PENDING;
        this.startTime=LocalDateTime.now();
        this.finishTime=startTime.plusDays(1);//useless
    }

	// Observer đăng ký
	public void registerObserver(Bidder bidder){
		if (bidder == null){
			return;
		}
		bidLock.lock();
		try {
			observers.add(bidder);
		} finally {
			bidLock.unlock();
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

		synchronized (this){ // NGon vay, this ở đây chỉ cái auction đó
							// không Auction.class vì sẽ blocks ALL auction
			if (bidder == null || this.status != AuctionStatus.RUNNING){
				return false;
			}
			if (this.currentPrice + miniumStep <= price) {
				//update new winner
				this.currentWinner = bidder;
				this.currentPrice = price;
				this.addBidTransaction(bidder, price);

				// add observer
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

	// --------------------------------------------------------
	// Logic chuyển trạng thái của AUCTION --------------------
	public synchronized void startAuction(){
		if(this.status == AuctionStatus.PENDING){
			this.status = AuctionStatus.RUNNING;
			System.out.println("Bắt đầu phiên đấu giá");
		}
	}
	public synchronized void finishAuction(){
		if(this.status == AuctionStatus.RUNNING){
			this.status = AuctionStatus.FINISHED;
			this.payingAuction();
			System.out.println("Hoàn thành phiên đấu giá");
		}
	}
	public synchronized void payingAuction(){
		if (this.status != AuctionStatus.FINISHED){
			System.out.println("Phiên đấu giá không thể thanh toán");
			return;
		}
		if (this.currentWinner == null || this.currentWinner.getWallet() == null){
			System.out.println("Không có người thắng để thanh toán");
			return;
		}

		this.currentWinner.getWallet().deductLockBalance();
		this.status = AuctionStatus.PAID;
		System.out.println("Phiên đấu giá đã được thanh toán");
	}

	public synchronized void cancelAuction(){
		if(this.status != AuctionStatus.PAID && this.status != AuctionStatus.CANCELLED){
			this.status = AuctionStatus.CANCELLED;
			notifyObservers(AuctionEvent.AUCTION_CANCELLED,"Phiên đấu giá đã bị huỷ");
			try{
				this.currentWinner.getWallet().releaseBalance();

			} catch (NullPointerException e) {
				System.out.println("");
			}
			System.out.println("Phiên đâu giá đã bị huỷ");
		}else{
			System.out.println("Không thể huỷ phiên đấu giá");
		}
	}
}