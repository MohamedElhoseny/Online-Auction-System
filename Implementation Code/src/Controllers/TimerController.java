package Controllers;

import io.datafx.controller.FXMLController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import javax.annotation.PostConstruct;
import java.util.Calendar;

@FXMLController(value = "../Views/Timer.fxml")
public class TimerController
{
    @FXML
    private Label hour;
    @FXML
    private Label minute;
    @FXML
    private Label second;
    @FXML
    private Label am;
    @FXML
    private Label pm;
    @FXML
    private Label year;
    @FXML
    private Label nanosecond;

    @PostConstruct
    public void init()
    {
        System.out.println("Timer Initialized 100%");
    }

}
