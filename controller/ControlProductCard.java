package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.time.format.DateTimeFormatter;
import java.io.IOException;


public class ControlProductCard {
    @FXML
    private ImageView itemImageView;
    @FXML
    private Label itemName;
    @FXML
    private Label itemType;
    @FXML
    private Label itemTimeLeft;
    @FXML
    private Label itemCurrentBid;

    /*
    Setting data in card (Product) ---
     */
    public void setData(Auction auction){
        itemName.setText(auction.getItem().getName());
        itemType.setText(auction.getItem().getCategory().toString());
        itemCurrentBid.setText(String.valueOf(auction.getCurrentPrice()));
        // Khởi tạo bộ định dạng (ví dụ: Ngày/Tháng/Năm Giờ:Phút)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        if (auction.getFinishTime() != null) {
            itemTimeLeft.setText(auction.getFinishTime().format(formatter));
        } else {
            itemTimeLeft.setText("N/A"); // Dự phòng nếu chưa có giờ kết thúc
        }
        //Load Image
        try{
            Image image = new Image(getClass().getResourceAsStream(auction.getItem().getImagePath()));
            itemImageView.setImage(image);
        }catch (Exception e){
            e.printStackTrace(); // need to be another way?
        }
    }

    public static VBox renderCard(Auction myAuction){
        try{
            // Can't use getClass() because "static",so, we need to getClass through <Class>.class
            FXMLLoader loadder = new FXMLLoader(ControlProductCard.class.getResource("controller/productCard.fxml"));
            //Create Obj which is a allThings(things created in SceneBuilder)

            VBox cardBox = loadder.load();// -> load fxml -> create
            ControlProductCard controlProductCard = loadder.getController();
            controlProductCard.setData(myAuction);
            return cardBox;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
