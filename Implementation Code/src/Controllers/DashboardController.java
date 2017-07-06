package Controllers;

import Animation.Transition.FadeInUpBigTransition;
import Models.Admin;
import io.datafx.controller.FXMLController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import javax.annotation.PostConstruct;

/**
 * Created by Elhosany on 3/25/2017.
 */
@FXMLController(value = "../Views/Dashboard.fxml")
public class DashboardController
{
    @FXML
    AnchorPane _Dasboard;   /*Layout*/
    @FXML
    AreaChart sessions_chart;
    @FXML
    HBox state;

    @FXML
    private Label userscount;

    @FXML
    private Label salescount;

    @FXML
    private Label requestcount;

    Admin user;

    @PostConstruct
    public void init()
    {
        GUI_BAG bag = GUI_BAG.get_Bag();
        user = (Admin) bag.get_Registered_Component("user");

        init_sessionsChart_Data();
        init_viewData();
    }

    private void init_viewData() {
        userscount.setText(user.getuserscount()+"");
        salescount.setText(user.getsalescount()+"");
        requestcount.setText(user.getrequestscount()+"");
    }

    private void init_sessionsChart_Data()
    {
        //Define x-axis and y-axis Types
        CategoryAxis sessions_time = new CategoryAxis();   //x-axis
        sessions_time.setLabel("Sessions Times & Duration");

        NumberAxis session_bids = new NumberAxis();  //y-axis
        session_bids.setLabel("Bids Per session");

        //Creating Data Modules
        XYChart.Series series1 = new XYChart.Series<>();
        series1.setName("Saturday");
        series1.getData().add(new XYChart.Data("5:00 PM",3));
        series1.getData().add(new XYChart.Data("8:00 PM",5));
        series1.getData().add(new XYChart.Data("11:00 PM",4));
        series1.getData().add(new XYChart.Data("2:00 AM",10));

        XYChart.Series series2 = new XYChart.Series<>();
        series2.setName("Sunday");
        series2.getData().add(new XYChart.Data("5:00 PM",3));
        series2.getData().add(new XYChart.Data("8:00 PM",7));
        series2.getData().add(new XYChart.Data("11:00 PM",4));
        series2.getData().add(new XYChart.Data("2:00 AM",10));

        XYChart.Series series3 = new XYChart.Series<>();
        series3.setName("Monday");
        series3.getData().add(new XYChart.Data("5:00 PM",5));
        series3.getData().add(new XYChart.Data("8:00 PM",5));
        series3.getData().add(new XYChart.Data("11:00 PM",2));
        series3.getData().add(new XYChart.Data("2:00 AM",10));

        XYChart.Series series4 = new XYChart.Series<>();
        series4.setName("Tuesday");
        series4.getData().add(new XYChart.Data("5:00 PM",1));
        series4.getData().add(new XYChart.Data("8:00 PM",2));
        series4.getData().add(new XYChart.Data("11:00 PM",4));
        series4.getData().add(new XYChart.Data("2:00 AM",9));

        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200), event -> {
        }));
        t1.play();
        sessions_chart.getData().addAll(series1,series2,series3,series4);

    }
}
