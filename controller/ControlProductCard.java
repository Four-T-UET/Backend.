package app.resou.etrade;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;


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

    //
    public void setData(String setItemName,String setType,String setItemTimeLeft,double setItemCurrentBid,String imagePath){
        itemName.setText(setItemName);
        itemType.setText(setType);
        itemCurrentBid.setText(Double.toString(setItemCurrentBid));
        itemTimeLeft.setText( setItemTimeLeft);
        //Load Image
        try{
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            itemImageView.setImage(image);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
