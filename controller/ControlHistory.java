package controller;

import controller.out.production.Backend_API.ItemCategory;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

public class ControlHistory implements Initializable {
    //_______________________
    // FOR PURCHASES
    //_______________________
    /*
    Cách thông tin được chạy trong TableView:
        + Đầu tiên: chạy gọi initialized() -> mục đich để nạp các nut bấm, ... vào
    trong bộ nhớ. setDataForColumn(), được gọi đó chính là hướng dẫn cho các data sau
    này được ghi như thế nào?
           -------> NOTE: lúc này dữ liệu trong bảng vẫn trống

        +Tiếp theo: mới nhận data từ MainBoard || Database rồi mới gọi loadDataForPurchases();
     */
    List<Auction> auctionList = new ArrayList<>();
    public void setAuctionList(List<Auction> auctionList){
        this.auctionList = auctionList;
        loadDataForPurchases();
        loadPieChart();
        // LOADING LUÔN THÔNG TIN,
    }

    @FXML
    private TableView<Auction> tablePurchases;
    @FXML
    private TableColumn<Auction,String> itemColPurchases;
    @FXML
    private TableColumn<Auction,String> categoryColPurchases;
    @FXML
    private TableColumn<Auction,Double> winpriceColPurchases;
    @FXML
    private TableColumn<Auction, LocalDateTime> dateColPurchases;
    @FXML
    private TableColumn<Auction,AuctionStatus> statusColPurchases;
    @FXML
    private ComboBox<ItemCategory> categoryComboPurchases;
    @FXML
    private ImageView imagePurchaseIcon;
    @FXML
    private PieChart piechartPurchases;
    //
    public void loadDataForPurchases(){
        ObservableList<Auction> auctionObservableList = FXCollections.observableArrayList(this.auctionList);
        /*
        Auctions are pull in tableView
        Using ObservableList for render UI -> Client
         */
        tablePurchases.setItems(auctionObservableList);

    }
    //observableArrayList(Callback extractor)	tự động update khi property con thay đổi
    // cần phải thay đổi ngay
    public void setDataForColumn(){
        itemColPurchases.setCellValueFactory(cellData -> {
            //cellData mang thông tin Auction hiển thị lên trên màn hình
            // -> getValue() trả ra Aution của hàng;
            Item product = cellData.getValue().getProduct();
            String name = (product != null) ? product.getName() : "Unknown!";
            //JavaFx không chấp nhận return name
            //Do nó bảng tableView chỉ chấp nhận đối tượng trả ve
            // la -> "Property"
            return new SimpleStringProperty(name);
        });

        categoryColPurchases.setCellValueFactory(cellData -> {
            Item product = cellData.getValue().getProduct();
            String category = product.toString();
            return new SimpleStringProperty(category);
        });

        winpriceColPurchases.setCellValueFactory(cellData ->{
            return new SimpleObjectProperty<>(cellData.getValue().getCurrentPrice());
        });

        dateColPurchases.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getFinishTime())
        );

        statusColPurchases.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getStatus())
        );
    }
    //
    public void setDataForCategory(){
        ObservableList<ItemCategory> itemCategories = FXCollections.observableArrayList(ItemCategory.values());
        //ItemCategory.value() trả về tất cả các giá trị
        //ta không cần phải liệt kê thủ công ARTS,VEHICLE,ELECTRONICS
        categoryComboPurchases.setItems(itemCategories);
    }
    //
    public void loadImage(String path){
        Image image = new Image(getClass().getResourceAsStream(path));
        imagePurchaseIcon.setImage(image);
    }
    //
    public void loadPieChart(){
        Map<ItemCategory,Integer> count = new HashMap<>();
        for(Auction auction: auctionList){
            ItemCategory temp =  auction.getProduct().getCategory();
            count.put(temp,count.getOrDefault(temp,0) + 1);
        }
        int total = 0;
        for(Integer value: count.values()){
            total += value;
        }
        if(total == 0){
            piechartPurchases.setData(FXCollections.observableArrayList());
            return;// tranh chia cho 0
        }
        //load data
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        //-> PieChart.Data là 1 lát trong biểu đồ tròn
        // VD:
        //PieChart.Data data = new PieChart.Data("ELECTRONICS", 5);
        // ý nghĩa là lượng Electronics là 5
        // tạo các List chứa PieChart.Data -> chứa những lát cắt
        for(Map.Entry<ItemCategory,Integer> entry: count.entrySet()){
            double percent = (double) entry.getValue() / total;
            String label = entry.getKey().name() + " (" + String.format("%.1f", percent) + "%)";
            pieData.add(new PieChart.Data(label, entry.getValue()));
        }

        piechartPurchases.setData(pieData);
    }

    //-------------
    //FOR SELLING
    //-------------
    //continue


    //khởi tạo
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //-----PURCHASES------
        setDataForColumn();
        setDataForCategory();
        loadImage("controller/loginImage.jpg");// cần thay đổi:)))
    }



}
