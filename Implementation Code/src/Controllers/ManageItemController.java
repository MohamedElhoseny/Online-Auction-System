package Controllers;

import Models.Admin;
import Models.Category;
import Models.Item;
import com.jfoenix.controls.JFXButton;
import io.datafx.controller.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@FXMLController(value = "../Views/Manage_Item.fxml")
public class ManageItemController
{
    @FXML
    private TableView<Item> item_table;

    @FXML
    private TableColumn<Item,Integer> c_id;

    @FXML
    private TableColumn<Item,String> c_Name;

    @FXML
    private TableColumn<Item,String> c_itemname;

    @FXML
    private TableColumn<Item,String> c_category;

    @FXML
    private TableColumn<Item,Double> c_price;

    @FXML
    private TableColumn<Item,Integer> c_starttime;

    @FXML
    private TextField entered_id;

    @FXML
    private JFXButton savebtn;

    @FXML
    private JFXButton approve;

    @FXML
    private JFXButton reject;

    GUI_BAG bag = GUI_BAG.get_Bag();
    private Admin user;
    private ArrayList<Item>  approveditem;
    private ArrayList<Item> rejecteditem;

    @PostConstruct
    public void init()
    {
        user = (Admin) bag.get_Registered_Component("user");
        user.initializeAdmin();

        init_CategoryTable();

        approveditem = new ArrayList<>();
        rejecteditem = new ArrayList<>();


        init_events();

    }
    private void init_events()
    {
        //open close form
        reject.setOnAction(event -> {
            int index = item_table.getSelectionModel().getSelectedIndex();
            rejecteditem.add(item_table.getItems().get(index));
            item_table.getItems().remove(index);
        });

        approve.setOnAction(event -> {
            int index = item_table.getSelectionModel().getSelectedIndex();
            approveditem.add(item_table.getItems().get(index));
            item_table.getItems().remove(index);
        });


        savebtn.setOnAction(event -> {
             boolean accept = false;
               if (!rejecteditem.isEmpty()){
                   user.RejectItem(rejecteditem);
                   accept = true;
                   rejecteditem.clear();
               }

               if (!approveditem.isEmpty()){
                   user.ApproveItem(approveditem);
                   accept = true;
                   approveditem.clear();
               }
             if (accept)
                 savebtn.setStyle("-fx-background-color: limegreen");
            else
                 savebtn.setStyle("-fx-background-color: red");
        });
    }

    private void init_CategoryTable()   //100%
    {
        c_id.setCellValueFactory(
                new PropertyValueFactory<Item,Integer>("ID")
        );

        c_Name.setCellValueFactory(
                new PropertyValueFactory<Item,String>("SellerName")
        );

        c_itemname.setCellValueFactory(
                new PropertyValueFactory<Item,String>("Item_name")
        );
        c_category.setCellValueFactory(
                new PropertyValueFactory<Item,String>("CatName")
        );
        c_price.setCellValueFactory(
                new PropertyValueFactory<Item,Double>("Price")
        );
        c_starttime.setCellValueFactory(
                new PropertyValueFactory<Item,Integer>("StartTime")
        );


        //////////////////////////////////////////////////////////////////////////////////////
        ArrayList<Item> ItemList = user.getRequestedItems();
        System.out.println(ItemList.size());
        ObservableList<Item> tablelist = FXCollections.observableArrayList(ItemList);
        item_table.setItems(tablelist);
    }
}
