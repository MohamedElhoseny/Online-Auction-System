package Controllers;

import Models.Admin;
import Models.SystemUser;
import com.jfoenix.controls.JFXButton;
import io.datafx.controller.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javax.annotation.PostConstruct;
import java.util.ArrayList;


/**
 * Created by Elhosany on 4/9/2017.
 */
@FXMLController(value = "../Views/Manage_User.fxml")
public class ManageUserController
{

    @FXML
    private AnchorPane _viewpane;
    @FXML
    private JFXButton deletebtn;
    @FXML
    private JFXButton savebtn;


    @FXML
    private TableView<SystemUser> userstable;
    @FXML
    private TableColumn<SystemUser,Integer> C_ID;
    @FXML
    private TableColumn<SystemUser,String> C_Fname;
    @FXML
    private TableColumn<SystemUser,String> C_Lname;
    @FXML
    private TableColumn<SystemUser,String> C_email;
    @FXML
    private TableColumn<SystemUser,String> C_phone;

    private GUI_BAG bag;
    private Admin user;
    private ArrayList<Integer> selecteduser;

    @PostConstruct
    public void init()
    {
        bag = GUI_BAG.get_Bag();
        user = (Admin) bag.get_Registered_Component("user");
        selecteduser = new ArrayList<>();
        init_UsersTable();

        deletebtn.setOnAction(event -> {
            try {
                int index = userstable.getSelectionModel().getSelectedIndex();
                selecteduser.add(userstable.getItems().get(index).getId());
                userstable.getItems().remove(index);
            }catch (Exception ex){
                System.out.println("Error while selecting deleted uses");
            }

        });
    }

    private void init_UsersTable() {
        //init property
        C_ID.setCellValueFactory(
                new PropertyValueFactory<SystemUser,Integer>("id")
        );
        C_Fname.setCellValueFactory(
                new PropertyValueFactory<SystemUser,String>("Fname")
        );
        C_Lname.setCellValueFactory(
                new PropertyValueFactory<SystemUser,String>("Lname")
        );
        C_email.setCellValueFactory(
                new PropertyValueFactory<SystemUser,String>("email")
        );
        C_phone.setCellValueFactory(
                new PropertyValueFactory<SystemUser,String>("phone")
        );

       //init table data
        ArrayList<SystemUser> users = user.getSystemUsersList();
        ObservableList<SystemUser> Systemusers = FXCollections.observableArrayList(users);
        userstable.setItems(Systemusers);
    }
    @FXML
    private void deleteSelectedUsers(ActionEvent e){
       if (!selecteduser.isEmpty()){
           user.DeleteUser(selecteduser);
           savebtn.setStyle("-fx-background-color: limegreen");
       }else {
           savebtn.setStyle("-fx-background-color: red");
       }
    }
}
