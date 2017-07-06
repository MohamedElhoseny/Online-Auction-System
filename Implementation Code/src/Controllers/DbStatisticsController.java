package Controllers;

import io.datafx.controller.FXMLController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

import javax.annotation.PostConstruct;

/**
 * Created by Elhosany on 4/9/2017.
 */
@FXMLController(value = "../Views/DbStatistics.fxml")
public class DbStatisticsController
{

    @FXML
    private LineChart sysuser;

    @PostConstruct
    public void init()
    {
        init_sysuserChart();
    }

    private void init_sysuserChart()
    {
        //Define x-axis and y-axis Types
        CategoryAxis sessions_time = new CategoryAxis();   //x-axis
        sessions_time.setLabel("Week's Days");

        NumberAxis session_bids = new NumberAxis();  //y-axis
        session_bids.setLabel("Users");

        //Creating Data Modules
        XYChart.Series series1 = new XYChart.Series<>();
        series1.setName("Elhosany");
        series1.getData().add(new XYChart.Data("Saturday",3));
        series1.getData().add(new XYChart.Data("Sunday",5));
        series1.getData().add(new XYChart.Data("Monday",4));
        series1.getData().add(new XYChart.Data("Tuesday",10));

        XYChart.Series series2 = new XYChart.Series<>();
        series2.setName("Hassan");
        series2.getData().add(new XYChart.Data("Saturday",3));
        series2.getData().add(new XYChart.Data("Sunday",7));
        series2.getData().add(new XYChart.Data("Monday",4));
        series2.getData().add(new XYChart.Data("Tuesday",10));

        XYChart.Series series3 = new XYChart.Series<>();
        series3.setName("Tawfik");
        series3.getData().add(new XYChart.Data("Saturday",5));
        series3.getData().add(new XYChart.Data("Sunday",5));
        series3.getData().add(new XYChart.Data("Monday",2));
        series3.getData().add(new XYChart.Data("Tuesday",10));

        XYChart.Series series4 = new XYChart.Series<>();
        series4.setName("Usama");
        series4.getData().add(new XYChart.Data("Saturday",1));
        series4.getData().add(new XYChart.Data("Sunday",2));
        series4.getData().add(new XYChart.Data("Monday",4));
        series4.getData().add(new XYChart.Data("Tuesday",9));

        sysuser.getData().addAll(series1,series2,series3,series4);

    }
}
