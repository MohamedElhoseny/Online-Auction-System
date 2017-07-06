import Controllers.GUI_BAG;
import Controllers.StartupController;
import io.datafx.controller.flow.Flow;
import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Date;

/**
 * Created by Elhosany on 3/23/2017.
 */

public class MainClass extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
       /* String appId = "Online Auction System";
        boolean alreadyRunning;
        try {
            JUnique.acquireLock(appId);
            alreadyRunning = false;
        } catch (AlreadyLockedException e){
            alreadyRunning = true;
            Parent root = FXMLLoader.load(getClass().getResource("Views/lockedView.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setAlwaysOnTop(true);
            primaryStage.show();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        }
        if (!alreadyRunning) {*/
            Flow flow = new Flow(StartupController.class);
            StackPane layout = flow.createHandler().start();
            Scene scene = new Scene(layout);
            scene.getStylesheets().add(getClass().getResource("Resources/CSS/style_Component.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("Resources/CSS/style.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Online Auction System");
            primaryStage.getIcons().add(new Image("Resources/Images/App.png"));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
            GUI_BAG gui_bag = GUI_BAG.get_Bag();
            gui_bag.Register_Component("primaryStage", primaryStage);
            gui_bag.setDraggable(primaryStage, layout);
            System.out.println("System Started at : " + new Date(System.currentTimeMillis()));
      //  }
    }

    public static void main(String[] args){
        launch(args);
    }
}
