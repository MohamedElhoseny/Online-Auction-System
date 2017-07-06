package Controllers;
import Models.Admin;
import Models.Sessions;
import Models.SystemUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import io.datafx.controller.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Elhosany on 4/9/2017.
 */
@FXMLController(value = "../Views/Manage_Sessions.fxml")
public class SessionController
{
    @FXML
    private AnchorPane _viewpane;

    @FXML
    private JFXDatePicker datepicker;

    @FXML
    private JFXDatePicker timepicker;

    @FXML
    private JFXButton addsession;

    @FXML
    private TableView<Sessions> sessiontable;

    @FXML
    private TableColumn<Sessions, String> Session_Date;

    @FXML
    private TableColumn<Sessions, Integer> Start_Time;

    @FXML
    private TableColumn<Sessions, Integer> End_Time;

    @FXML
    private TableColumn<Sessions, Integer> reserved;

    @FXML
    private Label _error;

    //Out-of-GUI
    LocalTime newSession;
    private GUI_BAG gui_bag;
    private Admin user;

    @PostConstruct
    public void init()
    {
        gui_bag = GUI_BAG.get_Bag();
        user = (Admin) gui_bag.get_Registered_Component("user");

        user.initializeAdmin();
        init_basicevent();
        init_sessiontable();
    }

    private void init_basicevent() {
        addsession.setOnAction(event -> add_session());
    }

    private void init_sessiontable() {
        Session_Date.setCellValueFactory(
                new PropertyValueFactory<Sessions, String>("Session_date")
        );
        Start_Time.setCellValueFactory(
                new PropertyValueFactory<Sessions, Integer>("Start_time")
        );
        End_Time.setCellValueFactory(
                new PropertyValueFactory<Sessions, Integer>("End_time")
        );
        reserved.setCellValueFactory(
                new PropertyValueFactory<Sessions, Integer>("reserved")
        );

        ObservableList<Sessions> list = FXCollections.observableArrayList(user.getSessions());
        sessiontable.setItems(list);
    }
    private void add_session()        //100%
    {
        int starthour = 0;
        String date = "";

        try {
            starthour = timepicker.getTime().getHour();
            if (datepicker.getValue().isBefore(LocalDate.now())){
                  _error.setText("* You Can not add This Session Date Check Current Date !!");
                  _error.setVisible(true);
            }else{
                int day = datepicker.getValue().getDayOfMonth();
                int month = datepicker.getValue().getMonthValue();
                int year = datepicker.getValue().getYear();
                date = day+"-"+month+"-"+year;
                _error.setVisible(false);

                for (Sessions s: user.getSessions()) {
                    if (s.getStart_time() == starthour && s.getSession_date().equals(date)) {
                        _error.setText("* Sorry There's another Same Session at this time !!");
                        _error.setVisible(true);
                        break;
                    }
                }
            }
        }catch (NullPointerException e){
            _error.setVisible(true);
        }

        if (!_error.isVisible()) {
           Sessions s = user.SpecifyAuctionTime(starthour, date);
           // init_SessionsList();  as table is work as listener

           sessiontable.getItems().add(s);
           user.initializeAdmin();
        }
    }
}
