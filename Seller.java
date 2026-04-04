public interface Seller {
    void addItem(Item temp);
//    void adjustItem(Item temp);
    void removeItem(Item temp);

    Auction addAuction(Item temp, double firstprice,double miniumStep);
}
