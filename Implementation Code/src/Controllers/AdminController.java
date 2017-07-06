package Controllers;

import Animation.Transition.*;
import Models.Admin;
import Models.Item;
import Models.SystemUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

/**
 * Created by Elhosany on 3/23/2017.
 */
@FXMLController(value = "../Views/AdminView.fxml")
public class AdminController
{
    //Default Component
    @FXML
    StackPane root;
    @FXML
    private Label welcomeAdminName;
    @FXML
    ImageView profilePicture;
    @FXML
    MaterialDesignIconView full;
    @FXML
    FontAwesomeIconView search;
    @FXML
    JFXTextField search_bar;
    @FXML
    ImageView close;
    @FXML
    BorderPane layout;
    @FXML
    ImageView signout;

    @FXML
    VBox right_pane; //continue event pane and its label (Live Events)
    @FXML
    ScrollPane event_pane;



    // Navigation Buttons
    @FXML
    JFXButton stats;
    @FXML
    JFXButton Dashboard;
    @FXML
    JFXButton Manage_Category;
    @FXML
    JFXButton Manage_items;
    @FXML
    JFXButton Mang_auction_session;
    @FXML
    JFXButton Mang_users;
    @FXML
    JFXButton Manag_db;
    @FXML
    HBox welcomepane;
    @FXML
    Label AdminName;  //get from admin object to set this
    @FXML
    StackPane layoutContent;

    //Flow
    @FXMLViewFlowContext
    ViewFlowContext context;
    Flow flow;
    FlowHandler flowHandler;

    //Out-of-GUI Components
    private GUI_BAG bag;
    private Admin user;
    private Stage window;
    private JFXButton activebtn;
    private ArrayList<Notification> noticards;

    //notification
    @FXML
    private VBox notificationspane;

    @PostConstruct
    public void init() throws FlowException
    {
        bag = GUI_BAG.get_Bag();
        user = (Admin) bag.get_Registered_Component("user");
        window= (Stage) bag.get_Registered_Component("primaryStage");
        user.initializeAdmin();

          //Connect The Default View With The Layout
          flow = new Flow(DashboardController.class);
          flowHandler = flow.createHandler(context);
          layoutContent.getChildren().setAll(flowHandler.start());

          init_startup();
          init_basic_Events();        //FullScreen , Close ....
          init_user_view();
          init_Navigation_Events();  //Navigation between Views
          init_Notifications();
      }

    private void init_user_view() {
        welcomeAdminName.setText(user.getFname()+" "+user.getLname());
        AdminName.setText(welcomeAdminName.getText());
        profilePicture.setImage(new Image(getClass().getResource(user.getProfilePic()).toExternalForm()));
    }
    private void init_startup() {
        new BounceInTransition(welcomepane).play();
        new Timeline(new KeyFrame(Duration.millis(2000),event -> {
            layout.setVisible(true);
            welcomepane.setVisible(false);
        })).play();

        activebtn = Dashboard;
        refreshView(activebtn);
    }
    private void init_Navigation_Events() {
        Manage_Category.setOnAction(event -> {
            flow = new Flow(CategoryController.class);
            flowHandler = flow.createHandler(context);
            StackPane newView = null;
            try {
                newView = flowHandler.start();
            } catch (FlowException e) {
                e.printStackTrace();
            }
            //Animation Code
          //  new FadeInRightBigTransition(right_pane).play();
            layoutContent.getChildren().setAll(newView);
            new Timeline(new KeyFrame(Duration.millis(700), e -> {
                right_pane.setVisible(true);
            })).play();

            refreshView(Manage_Category);  //to colourful active btn
        });

        Dashboard.setOnAction(event -> {
            flow = new Flow(DashboardController.class);
            flowHandler = flow.createHandler(context);
            try {
                layoutContent.getChildren().setAll(flowHandler.start());
            } catch (FlowException e) {
                e.printStackTrace();
            }
            new FadeInRightBigTransition(right_pane).play();
            new Timeline(new KeyFrame(Duration.millis(700), e -> {
                right_pane.setVisible(true);
            })).play();
            refreshView(Dashboard);
        });

        Manage_items.setOnAction(event -> {
            flow = new Flow(ManageItemController.class);
            flowHandler = flow.createHandler(context);
            StackPane newView = null;
            try {
                newView = flowHandler.start();
            } catch (FlowException e) {
                e.printStackTrace();
            }
            layoutContent.getChildren().setAll(newView);
           // right_pane.setVisible(false);
            refreshView(Manage_items);
        });

        Mang_auction_session.setOnAction(event -> {
            flow = new Flow(SessionController.class);
            flowHandler = flow.createHandler(context);
            StackPane newView = null;
            try {
                newView = flowHandler.start();
            } catch (FlowException e) {
                e.printStackTrace();
            }
            layoutContent.getChildren().setAll(newView);
            refreshView(Mang_auction_session);
        });

        Mang_users.setOnAction(event -> {
            flow = new Flow(ManageUserController.class);
            flowHandler = flow.createHandler(context);
            StackPane newView = null;
            try {
                newView = flowHandler.start();
            } catch (FlowException e) {
                e.printStackTrace();
            }
            layoutContent.getChildren().setAll(newView);
            refreshView(Mang_users);
        });

        Manag_db.setOnAction(event -> {
            flow = new Flow(ManageDBController.class);
            flowHandler = flow.createHandler(context);
            StackPane newView = null;
            try {
                newView = flowHandler.start();
            } catch (FlowException e) {
                e.printStackTrace();
            }
            layoutContent.getChildren().setAll(newView);
            refreshView(Manag_db);
        });

        stats.setOnAction(event -> {
            flow = new Flow(DbStatisticsController.class);
            flowHandler = flow.createHandler(context);
            StackPane newView = null;
            try {
                newView = flowHandler.start();
            } catch (FlowException e) {
                e.printStackTrace();
            }
            layoutContent.getChildren().setAll(newView);
            refreshView(stats);
        });
    }
    private void init_basic_Events() {
        String path = getClass().getResource("../Resources/Sounds/like.mp3").toString();
        new MediaPlayer(new Media(path)).play();

        full.setOnMouseClicked(event -> {
            if (!window.isFullScreen())
            {
                window.setFullScreen(true);
            }
        });
        close.setOnMouseClicked(event -> {
            System.exit(0);
        });
        search.setOnMouseClicked(event -> {
            search_bar.setVisible(true);
        });

        signout.setOnMouseClicked(event -> {
            user.Logout(user);
            Flow flow = new Flow(StartupController.class);
            try {
                StackPane nextview = flow.createHandler().start();
                Stage window = (Stage) bag.get_Registered_Component("primaryStage");
                Scene scene = new Scene(nextview);
                window.setX(80);
                scene.getStylesheets().add(getClass().getResource("../Resources/CSS/style_Component.css").toExternalForm());
                scene.getStylesheets().add(getClass().getResource("../Resources/CSS/style.css").toExternalForm());
                window.setScene(scene);
                user.setState(user.getId(),0);
            } catch (FlowException e) {
                e.printStackTrace();
            }
        });

        bag.setDraggable(window,root);
    }
    private void refreshView(JFXButton btn) {
        activebtn.setStyle("-fx-background-color:  rgba(37, 54, 81, 1)");  //return old btn to default color
        activebtn = btn;  //point to active btn
        activebtn.setStyle("-fx-background-color: #4DA6F8");  //change color to active btn
    }
    private void init_Notifications() {
        ArrayList<Item> requesteditem = user.getRequestedItems();
        noticards = new ArrayList<>();

        for (Item x: requesteditem) {
            Notification n = new Notification(x.getId(),x.getSeller().getProfilePic(),x.getSeller().getFname());
            n.seen.setId(x.getId()+"");
            n.seen.setOnAction(this::seen);
            noticards.add(n);
        }
        notificationspane.getChildren().setAll(noticards);
    }
    private void seen(ActionEvent e){
        JFXButton seenselected = (JFXButton)e.getSource();
        String id = seenselected.getId();
        for (Notification x: noticards) {
             if (x.id == Integer.parseInt(id)){
                 noticards.remove(x);
                // init_Notifications();
                 updatenotipane();
                 break;
             }
        }
    }
    private void updatenotipane(){
        notificationspane.getChildren().setAll(noticards);
    }

    private class Notification extends DialogPane {

        protected final ImageView userimg;
        protected final StackPane stackPane;
        protected final Label msg;
        protected final Label label;
        protected final Label username;
        public JFXButton seen;
        public int id;

        public Notification(int id,String uimg,String uname) {
            this.id = id;
            userimg = new ImageView();
            stackPane = new StackPane();
            msg = new Label();
            label = new Label();
            username = new Label();
            seen = new JFXButton();

            setNodeOrientation(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);

            userimg.setFitHeight(73.0);
            userimg.setFitWidth(76.0);
            userimg.setPickOnBounds(true);
            userimg.setPreserveRatio(true);
            setGraphic(userimg);

            stackPane.setPrefHeight(110.0);
            stackPane.setPrefWidth(233.0);

            StackPane.setAlignment(msg, javafx.geometry.Pos.TOP_LEFT);
            StackPane.setAlignment(msg, Pos.TOP_LEFT);
            msg.setStyle("-fx-text-fill: #42a5f5;");
            msg.setText("A Seller Request To Add his Item To Tv's Category and System Need Your Approve To Add This Item");
            msg.setWrapText(true);
            StackPane.setMargin(msg, new Insets(0.0));
            msg.setFont(new Font("System Bold", 12.0));

            StackPane.setAlignment(label, javafx.geometry.Pos.BOTTOM_LEFT);
            StackPane.setAlignment(label, Pos.BOTTOM_LEFT);
            label.setStyle("-fx-text-fill: rgba(13, 210, 125, 1);");
            label.setText("Committed by : ");
            label.setUnderline(true);
            StackPane.setMargin(label, new Insets(0.0));
            label.setFont(new Font("System Bold Italic", 13.0));

            StackPane.setAlignment(username, javafx.geometry.Pos.CENTER);
            StackPane.setAlignment(username, Pos.CENTER);
            username.setAlignment(javafx.geometry.Pos.CENTER);
            username.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
            username.setStyle("-fx-text-fill: rgba(13, 210, 125, 1);");
            username.setText("Hasan");
            username.setUnderline(true);
            username.setFont(new Font("System Bold Italic", 13.0));
            StackPane.setMargin(username, new Insets(70.0, 0.0, 0.0, 25.0));

            StackPane.setAlignment(seen, javafx.geometry.Pos.BOTTOM_RIGHT);
            StackPane.setAlignment(seen, Pos.BOTTOM_RIGHT);
            seen.setStyle("-fx-background-color: #F44336;");
            setContent(stackPane);

            userimg.setImage(new Image(getClass().getResource(uimg).toExternalForm()));
            username.setText(uname);
            seen.setText("Seen");
            seen.setTextFill(Paint.valueOf("#FFF"));

            stackPane.getChildren().add(msg);
            stackPane.getChildren().add(label);
            stackPane.getChildren().add(username);
            stackPane.getChildren().add(seen);

        }
    }
}
