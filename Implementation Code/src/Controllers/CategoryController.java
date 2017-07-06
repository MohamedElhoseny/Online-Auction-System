package Controllers;

import Models.Admin;
import Models.Category;
import Models.SystemUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

/**
 *

 * Created by Elhosany on 3/25/2017.
 */
@FXMLController(value = "../Views/Manage_Category.fxml")
public class CategoryController
{
    @FXML
    private AnchorPane _category;

    @FXML
    private AnchorPane categoryview;

    @FXML
    private TableView<Category> Category_Table;

    @FXML
    private TableColumn<Category, Integer> ID_Column;

    @FXML
    private TableColumn<Category, String> Category_Column;

    @FXML
    private TableColumn<Category, String> Admin_Column;

    @FXML
    private JFXButton ADD;

    @FXML
    private TextField entered_id;

    @FXML
    private JFXButton deletebtn;

    @FXML
    private Pane _AddForm;

    @FXML
    private JFXTextField _id;

    @FXML
    private JFXTextField _name;

    @FXML
    private JFXButton _confirm;
    @FXML
    private JFXButton Save;

    @FXML
    private JFXButton _cancel;

    GUI_BAG bag = GUI_BAG.get_Bag();
    private Admin user;
    private ArrayList<Integer> deletedCategory;
    private ArrayList<Category> addedCategory;

    @PostConstruct
    public void init()
    {
        user = (Admin) bag.get_Registered_Component("user");
        user.initializeAdmin();
        deletedCategory = new ArrayList<>();
        addedCategory = new ArrayList<>();
        init_CategoryTable();
        init_events();

    }
    private void init_events()
    {
        //open close form
        ADD.setOnAction(event -> {
            GaussianBlur effect = new GaussianBlur();
            categoryview.setEffect(effect);
            _AddForm.setVisible(true);
        });
        _cancel.setOnAction(event -> {
            _AddForm.setVisible(false);
            categoryview.setEffect(null);
        });



        //Confirm on adding new Category
        _confirm.setOnAction(event -> {
            String id = _id.getText();
            String cname = _name.getText();
            Category newcategory = new Category(Integer.parseInt(id),cname,user.getId());
            Category_Table.getItems().add(newcategory);
            addedCategory.add(newcategory);

            _AddForm.setVisible(false);
            categoryview.setEffect(null);
            _id.clear();
            _name.clear();

        });


        //ADD or delete category
        deletebtn.setOnAction(event -> {
            try {
                int index = Category_Table.getSelectionModel().getSelectedIndex();
                deletedCategory.add(Category_Table.getItems().get(index).getId());
                Category_Table.getItems().remove(index);
            }catch (Exception ex){
                System.out.println("Error while selecting deleted uses");
            }
        });

        Save.setOnAction(event -> {

            if (addedCategory.isEmpty() && deletedCategory.isEmpty())
                Save.setStyle("-fx-background-color: red");

            if (!deletedCategory.isEmpty()) {
                user.DeleteCategory(deletedCategory);
                deletedCategory.clear();
                Save.setStyle("-fx-background-color: limegreen");
            }

            boolean check = true;
            if (!addedCategory.isEmpty()){
                for (Category c : addedCategory){
                    for (Category cc : user.getCategories()){
                        if (c.getId() == cc.getId())
                            check = false;
                    }
                }

                if (check){
                    user.AddCategory(addedCategory);
                    Save.setStyle("-fx-background-color: limegreen");
                }else {
                    Save.setStyle("-fx-background-color: red");
                }
            }
        });





    }

    private void init_CategoryTable()   //100%
    {
        ID_Column.setCellValueFactory(
                new PropertyValueFactory<Category,Integer>("ID")
        );

        Category_Column.setCellValueFactory(
                new PropertyValueFactory<Category,String>("Cat_Name")
        );

        Admin_Column.setCellValueFactory(
                new PropertyValueFactory<Category,String>("Admin_Name")
        );


     //////////////////////////////////////////////////////////////////////////////////////

        //init table data
        ArrayList<Category> CategoriesList = user.getCategories();
        ObservableList<Category> tablelist = FXCollections.observableArrayList(CategoriesList);
        Category_Table.setItems(tablelist);
    }
}
