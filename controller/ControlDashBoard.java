package app.resou.etrade;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControlDashBoard implements Initializable {
    @FXML
    private ComboBox<String> categoryBox;
    @FXML
    private Button butNext;
    @FXML
    private Button butBack;
    // Seperated page
    private List<Item> AllItems = new ArrayList<>(); // save all data
    private int cuurentPage = 0;
    private final int ITEMS_PER_PAGE = 8;


    @FXML
    private FlowPane containCards;
    // tao danh sach de luu, sau khi khoi dong ung dung se chi su dung cai nay, khong
    // load lai FXMLLOADER;
    private List<ControlProductCard> controlItems = new ArrayList<>();

    @FXML
    public void initialize(URL location, ResourceBundle resources){
        typeItem();

    }

    public void loadProductCards(List<Item> EnterAllItems){
        this.AllItems = EnterAllItems;
        this.cuurentPage = 0;
        renderPage();

    }
    //
    public void renderPage(){
        try{
            containCards.getChildren().clear();
            controlItems.clear();
            // Calculate Slicing where???
            int firstSlice = cuurentPage * ITEMS_PER_PAGE;
            int endSlice = Math.min(firstSlice + ITEMS_PER_PAGE,AllItems.size());
            //
            for(int i = firstSlice;i < endSlice;i ++){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/resou/etrade/productCard.fxml"));
                VBox cardBox = loader.load();
                ControlProductCard controlProductCard = loader.getController();
                controlProductCard.setData(
                        AllItems.get(i).getName(),
                        AllItems.get(i).getType(),
                        AllItems.get(i).getTimeLeft(),
                        AllItems.get(i).getCurrentBid(),
                        AllItems.get(i).getImagePath()
                );
                containCards.getChildren().add(cardBox);
                controlItems.add(controlProductCard);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        updateButton();
    }

    //
    public void updateButton(){
        butBack.setDisable(this.cuurentPage == 0);
        int maxPages = (int) Math.ceil((double)AllItems.size() / ITEMS_PER_PAGE) - 1; // from 0 -> n;
        butNext.setDisable(maxPages <= cuurentPage || AllItems.isEmpty());
    }


    @FXML
    public void typeItem(){
        ObservableList<String> observable = FXCollections.observableArrayList(
               "Vehicle",
                "Jewlery",
                "Arts"
        );
        categoryBox.setItems(observable);
        categoryBox.getSelectionModel().selectFirst();
    }

    @FXML
    public String chooseCategory(){
        return categoryBox.getValue();
    }
    //
    @FXML
    public void pressNext(){
        cuurentPage ++;
        renderPage();
    }
    public void pressBack(){
        cuurentPage--;
        renderPage();
    }
}
