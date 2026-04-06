public class Main {
    public static void main(String[] args) {
        // 1. Tạo 2 người dùng
        Clients user1 = new Clients("Alice", "pass123");
        Clients user2 = new Clients("Bob", "pass456");

        // 2. Tạo một món đồ để đấu giá
        RealItem iphone = new RealItem("iPhone 15 Pro", "Mới 100%");

        // 3. User1 mở một phiên đấu giá bán iPhone (Giá khởi điểm 1000, bước giá 50)
        Auction auction = user1.addAuction(iphone, 1000, 50);

        System.out.println("--- BẮT ĐẦU ĐẤU GIÁ ---");
        
        // 4. User2 vào đặt giá 1050 (Hợp lệ vì 1000 + 50 <= 1050)
        user2.placeBid(auction, 1050);
        
        // 5. User1 tự đặt giá 1080 (Thất bại vì bước giá yêu cầu là 50 -> Cần ít nhất 1100)
        user1.placeBid(auction, 1080);
        
        // 6. User1 đặt lại 1150 (Hợp lệ)
        user1.placeBid(auction, 1150);
        
        System.out.println("Chạy thành công mà không cần sửa kiến trúc!");
    }
}