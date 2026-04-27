package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControlDashBoard implements Initializable {
    private List<Auction> allAuctions;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button acountBut;
    @FXML
    private Button historyBut;
    @FXML
    private Button walletBut;
    @FXML
    private Button sellingBut;
    @FXML
    private Button settingBut;
    @FXML
    private Button findItem;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<ItemCategory> categoryComBox;
    @FXML
    private Pagination pagination;
    @FXML
    private FlowPane containCards;

    @FXML
    public void chooseCategory(){

    }
    @FXML
    public void Find(ActionEvent event) {
        ItemCategory type = categoryComBox.getValue();
        String searchData = searchField.getText().toLowerCase();
//        compareData(type, searchData);
    }
    // list<auction> này đã được lọc qua
    public void setAuctions(List<Auction> AuctionsDB) {
        this.allAuctions = AuctionsDB;
        loadAuctions();
    }

    public void loadProductCards(List<Auction> listAuctions){
        containCards.getChildren().clear();
        for (Auction auction : listAuctions){
            VBox card = ControlProductCard.renderCard(auction);
            if (card != null){
                containCards.getChildren().add(card);
            }
        }
    }

    @FXML
    private void chageToHistory(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/DashBoardTOHistory.fxml"));
            Parent root = loader.load();
            mainPane.setCenter(root);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void chageToWallet(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/DashBoardTOWallet.fxml"));
            Parent root = loader.load();
            mainPane.setCenter(root);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void chageToSelling(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/DashBoardTOSelling.fxml"));
            Parent root = loader.load();
            mainPane.setCenter(root);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void changeToAccount(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/DashBoardTOAccount.fxml"));
            Parent root = loader.load();
            mainPane.setCenter(root);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
//    @FXML
//    private void changeToSettings(ActionEvent event) {
//        try{
//            FXMLLoader  loader = new FXMLLoader(getClass().getResource("/controller/DashBoardTOSettings.fxml"));
//
//        }
//    }

//    private void compareData(ItemCategory type,String searchData){
//        switch (type){
//            case ALL:
//
//            case REAL_ESTATE:
//
//            case VEHICLE:
//
//            case ELECTRONICS:
//
//            case ARTS:
//
//        }
//    }

    private void findFromSearch(){

    }
    public void loadAuctions(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        categoryComBox.setItems(FXCollections.observableArrayList(ItemCategory.values()));
        categoryComBox.getSelectionModel().select(ItemCategory.ALL);
        findItem.setOnAction(event ->{
            Find(event);
        });
        historyBut.setOnAction(event ->{
            chageToHistory(event);
        });
        walletBut.setOnAction(event ->{
            chageToWallet(event);
        });
        sellingBut.setOnAction(event ->{
            chageToSelling(event);
        });
        acountBut.setOnAction(event ->{
            changeToAccount(event);
        });
    }
}
