package Controllers;

import Animation.Transition.BounceInTransition;
import Animation.Transition.FadeOutLeftBigTransition;
import Models.*;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.Duration;
import sun.dc.pr.PRError;
import sun.util.resources.cldr.ta.CalendarData_ta_IN;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Elhosany on 4/13/2017.
 */
@FXMLController(value = "../Views/SellerView.fxml")
public class SellerController implements UsersController
{
    @FXML
    private BorderPane Parent;
    @FXML
    private FontAwesomeIconView setting;
    @FXML
    private Label full;
    @FXML
    private Label edit_info;
    @FXML
    private Label submitted_Items;
    @FXML
    private VBox settingpane;
    @FXML
    private JFXButton hide;
    @FXML
    private JFXButton exitSys;
    @FXML
    private JFXButton signout;
    @FXML
    private Label welcomesellername;
    @FXML
    private JFXButton closeDetail;
    @FXML
    private BorderPane parent;
    @FXML
    private StackPane root;

    //submitted item pane
    @FXML
    private Pane submittedPane;
    @FXML
    private TableView<Item> itemtable;
    @FXML
    private TableColumn<Item, Integer> ID;

    @FXML
    private TableColumn<Item, String> Name;

    @FXML
    private TableColumn<Item, String> Category;

    @FXML
    private TableColumn<Item, Double> price;
    @FXML
    private TableColumn<Item, String> state;
    @FXML
    private FontAwesomeIconView hidesubmittedpane;

    //Welcome pane
    @FXML
    private HBox welcomepane;
    @FXML
    private HBox welcmsg;

    //info pane
    @FXML
    private Pane edit_infoPlan;
    @FXML
    private ImageView profilePicture;
    @FXML
    private FontAwesomeIconView hideInfo;
    @FXML
    private JFXTextField Fname , Lname , email;
    @FXML
    private JFXPasswordField pass , reenterpass;
    @FXML
    private JFXButton updateinfo;
    @FXML
    private FontAwesomeIconView editpassicon;
    @FXML
    private Label sellername;


    //Contains Products of Seller
    @FXML
    private FlowPane ProductsPane;


    //Adding Product Form
    @FXML
    private JFXButton submitproduct;
    @FXML
    private JFXButton UploadImage;
    @FXML
    private Pane addproduct_pane;
    @FXML
    private ImageView p_image;
    @FXML
    private TextField p_name, p_price;


    //Participant people
    @FXML
    private VBox participate_pane;
    @FXML
    private VBox BidderListPane;
    @FXML
    private FontAwesomeIconView closeParticipant,minimize;


    //Notification Pane
    @FXML
    private BorderPane notify_pane;
    @FXML
    private JFXButton closeNotify;
    @FXML
    private FontAwesomeIconView showNotifyPane;

    //notification Mapping
    @FXML
    private Button simplenotifybtn;
    @FXML
    private JFXBadge notifierBadge;
    @FXML
    private JFXSnackbar snackbar;


    //notification pane
    @FXML
    private FlowPane notificationpane;
    //Timer
    @FXML
    private HBox TimerPane;
    private HBox Timer;

    @FXML
    private JFXComboBox<String> categories;
    @FXML
    private JFXComboBox<String> sessionDate;
    @FXML
    private JFXComboBox<String> sessionTime;



    //winner work
    @FXML
    private StackPane winnerpane;
    @FXML
    private ImageView wimg;
    @FXML
    private Label wname;
    @FXML
    private Label wphone;
    @FXML
    private Label wemail;
    @FXML
    private Label wsubprice;
    @FXML
    private Label itemprice;
    @FXML
    private Pane nowinnerpane;
    @FXML
    private FontAwesomeIconView closewinnerpane;


    //out of gui
    private GUI_BAG bag;
    private Member user;
    private int notificationNumber = 0;
    private Stage window;
    private SchedulerNotifier schedulerNotifier;
    private ArrayList<Sessions> sessionlist;
    private ArrayList<Category> categoryArrayList;
    private ArrayList<Item> userProducts;
    private ArrayList<item> productsview;
    private String selectedproductnameImage;

    @PostConstruct
    public void init() {
        bag = GUI_BAG.get_Bag();
        user = (Member) bag.get_Registered_Component("user");
        window = (Stage) bag.get_Registered_Component("primaryStage");

        init_basicevent();
        init_userview();
        init_SellerProducts();
        init_SubmittedItems();
        init_Notification();
        init_TimerPane();
        init_ScheduleNotifier();
    }


    //initialization Methods
    private void init_basicevent(){
        //Welcome for User
        String path = getClass().getResource("../Resources/Sounds/like.mp3").toString();
        new MediaPlayer(new Media(path)).play();
        new BounceInTransition(welcmsg).play();
        new Timeline(new KeyFrame(Duration.millis(2000), event -> {
            welcomepane.setVisible(false);
            Parent.setVisible(true);
        })).play();

        //Init Lists of Category and Sessions in our System
        Category x = new Category();
        x.initializeCategories();
        Sessions y = new Sessions();
        y.initializeSessions();


        //init sessions
        sessionlist = y.getSessionList();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date inputDate;    Date current = new Date();
        String inputString = "11-11-2012";
        for (Sessions c:sessionlist) {
            inputString = c.getSession_date();
            try {
                inputDate = dateFormat.parse(inputString);
                if (inputDate.after(current) || isequal(current,inputDate)) {
                    if (!sessionDate.getItems().contains(c.getSession_date()))
                        sessionDate.getItems().add(c.getSession_date());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

           // sessionTime.getItems().add(c.getStart_time()+":00");  * not here must choose first date then choose this
        }
        sessionTime.setOnMouseClicked(event -> {
            sessionTime.getItems().clear();
            String selecteddate = sessionDate.getSelectionModel().getSelectedItem();

            for (Sessions s: sessionlist) {
                if (s.getSession_date().equals(selecteddate) && s.getReserved() == 0)
                    sessionTime.getItems().add(s.getStart_time()+"");
            }

            if (sessionTime.getItems().isEmpty())
                sessionTime.getItems().add("No Available Session");
        });


        categoryArrayList = x.CategoryList;
        for (Category c:categoryArrayList) {
            categories.getItems().add(c.getCat_Name());
        }



        snackbar.registerSnackbarContainer(root);
        showNotifyPane.setOnMouseClicked(event -> notify_pane.setVisible(true));
        closeNotify.setOnMouseClicked(event -> notify_pane.setVisible(false));
        submitproduct.setOnAction(event -> addproduct_pane.setVisible(true));
        closeDetail.setOnAction(event -> addproduct_pane.setVisible(false));


        closeParticipant.setOnMouseClicked(event -> participate_pane.setVisible(false));
        submitted_Items.setOnMouseClicked(event -> {
            submittedPane.setVisible(true);
        });
        hidesubmittedpane.setOnMouseClicked(event -> {
            submittedPane.setVisible(false);
        });
        setting.setOnMouseClicked(event -> {
            settingpane.setVisible(true);
        });
        signout.setOnAction(event -> {
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
                //Must Implemented before sign out
                user.setState(user.getId(),0);
               // schedulerNotifier.closeServices();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        exitSys.setOnAction(event -> {
            user.setState(user.getId(),0);
            System.exit(0);
        });
        hide.setOnMouseClicked(event -> {
            settingpane.setVisible(false);
        });

        edit_info.setOnMouseClicked(event -> {
            edit_infoPlan.setVisible(true);
        });
        hideInfo.setOnMouseClicked(event -> {
            edit_infoPlan.setVisible(false);
        });

        //full screen btn
        full.setOnMouseClicked(event ->
        {
            if (!window.isFullScreen())
            {
                window.setFullScreen(true);
            }
        });
        minimize.setOnMouseClicked(event -> window.setIconified(true));

        //for add product
        UploadImage.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
            try {
                selectedproductnameImage = file.getName();
            }catch (Exception e){
                System.out.println("error handled while choice file name");
            }

            //file.getName()  => get only the name of item
            //file.getAbsolutePath()    => get path of item on PC
            try
            {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                p_image.setImage(image);
            } catch (Exception ex) {
                System.out.println("error while reading product image");
            }
        });

        closewinnerpane.setOnMouseClicked(event -> winnerpane.setVisible(false));

        //Draggable
        bag.setDraggable(window,root);
    }
    private void init_userview() {
        welcomesellername.setText(user.getFname()+" "+user.getLname());
        profilePicture.setImage(new Image(getClass().getResource(user.getProfilePic()).toExternalForm()));
        sellername.setText(welcomesellername.getText());
        Fname.setText(user.getFname());
        Lname.setText(user.getLname());
        email.setText(user.getEmail());
        pass.setText(user.getPassword());
        reenterpass.setText(user.getPassword());

        updateinfo.setOnAction(event -> {
            if (pass.getText().equals(reenterpass.getText())){
                SetAcceptMsg(editpassicon);
                user.editInfo(user,Fname.getText(),Lname.getText(),pass.getText());
                sellername.setText(user.getFname()+" "+user.getLname());   //refresh
            }else{
                SetErrorMsg(editpassicon);
            }

        });
    }
    private void init_Notification(){
        ArrayList<String> Notifications = user.getNotifications(user.getId());
        for (String msg: Notifications) {
            notification n1 = new notification(msg);
            n1.seen.setOnAction(event -> seenNotification(n1.seen));
            notificationpane.getChildren().add(n1);
            notificationNumber++;
            notifierBadge.setText(String.valueOf(notificationNumber));
        }

    }
    private void init_SellerProducts() {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date current = new Date();
        Date sessiondate = null;
        int h = LocalDateTime.now().getHour();

        userProducts = user.membertype.viewSellerItems(user.getId());
        productsview = new ArrayList<>();
        for (Item x : userProducts){
            try {
                sessiondate = format.parse(x.getsession().getSession_date());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (x.getServed() == 0){
                if ( (sessiondate.before(current) && !isequal(current,sessiondate)) || (isequal(current,sessiondate) && x.getStartTime() < h)) {
                    x.setitemServed(x.getId());
                    x.setServed(1);
                }
            }

            if (x.getAccepted() == 0 || x.getAccepted() == 1) {  //check for accept and pending products only
                item itemview = new item(x.getId() + "", x.getItem_name(), x.getpicture(), x.getPrice() + "",
                        x.getsession().getStart_time() + "",x.getAccepted(),x.getServed());
                productsview.add(itemview);
            }
        }
        ProductsPane.getChildren().setAll(productsview);
    }
    private void init_SubmittedItems(){
        if (userProducts.isEmpty())
            userProducts = user.membertype.viewSellerItems(user.getId());

        //init property
        ID.setCellValueFactory(
                new PropertyValueFactory<Item,Integer>("ID")
        );
        Name.setCellValueFactory(
                new PropertyValueFactory<Item,String>("Item_name")
        );
        Category.setCellValueFactory(
                new PropertyValueFactory<Item,String>("CatName")
        );
        price.setCellValueFactory(
                new PropertyValueFactory<Item,Double>("Price")
        );
        state.setCellValueFactory(
                new PropertyValueFactory<Item,String>("state")
        );
        //init Data
        ObservableList<Item> list = FXCollections.observableArrayList(userProducts);
        itemtable.setItems(list);
    }
    private void init_TimerPane(){
        Flow flow = new Flow(TimerController.class);
        FlowHandler handler = flow.createHandler();
        StackPane pane = null;
        try {
            pane = handler.start();
            Timer = (HBox)pane.getChildren().get(0);
            TimerPane.getChildren().setAll(Timer);  //SET ALL => CONTINUE HBOX of Timer
        } catch (FlowException e) {
            System.out.println("Fatal error : Rendering Timer Pane ");
        }
    }
    private void init_ScheduleNotifier(){
        schedulerNotifier = new SchedulerNotifier();
        schedulerNotifier.initUser(this);
    }


    //Workers Methods
    @FXML
    public void showParticipants(ActionEvent e) {
        JFXButton btn_clicked = (JFXButton) e.getSource();
        int itemid = Integer.parseInt(btn_clicked.getId());
        //By Id of this btn [refer to item ID]

        ArrayList<Label> bidderview = new ArrayList<>();
        ArrayList<Object[]> bidders = user.membertype.getParticipatedBidder(itemid);
        if (!bidders.isEmpty()){
            for (Object[] o: bidders) {
                bidderview.add(new Label(o[0]+""+o[1]));
            }
        }else {
            bidderview.add(new Label("No One Participated"));
        }
        BidderListPane.getChildren().setAll(bidderview);
        participate_pane.setVisible(true);
    }
    @FXML
    public void showWinnerDetails(ActionEvent e){
        JFXButton btn_clicked = (JFXButton) e.getSource();
        int itemid = Integer.parseInt(btn_clicked.getId());
        ArrayList<Object[]> winnerinfo = new Sessions().GetWinnerinfo(itemid);

        if (!winnerinfo.isEmpty()){
            for (Item i:userProducts) {
                if (i.getId() == itemid){
                    itemprice.setText(""+i.getPrice());
                    break;
                }
            }
            wname.setText(winnerinfo.get(0)[0]+" "+winnerinfo.get(0)[1]);
            wphone.setText((String)winnerinfo.get(0)[2]);
            wemail.setText((String)winnerinfo.get(0)[3]);
            wsubprice.setText((Double)winnerinfo.get(0)[4] + "");
            wimg.setImage(new Image(getClass().getResource((String)winnerinfo.get(0)[5]).toExternalForm()));
            nowinnerpane.setVisible(false);
        }else
            nowinnerpane.setVisible(true);

        winnerpane.setVisible(true);
    }
    @FXML
    public void submitproduct(ActionEvent e)
    {
        try {
            //get category
            int cindex = categories.getSelectionModel().getSelectedIndex();
            Category c = categoryArrayList.get(cindex);
            //get session
            String date = sessionDate.getSelectionModel().getSelectedItem();
            String time = sessionTime.getSelectionModel().getSelectedItem();
            Integer selectedsessionID = 0;
            Sessions y = new Sessions(); y.initializeSessions();
            for (Sessions s: y.getSessionList()){
                if (s.getSession_date().equals(date) && s.getStart_time() == Integer.parseInt(time)){
                    selectedsessionID = s.getId();
                    break;
                }
            }
            //must handle ?
            user.membertype.submitItem(user.getId(),p_name.getText(),"../Resources/Images/shop/"+ c.getCat_Name()
                            +"/"+selectedproductnameImage,"new product", Double.valueOf(p_price.getText()),
                    selectedsessionID,c.getID(),1,0,0);

            addproduct_pane.setVisible(false);
        }catch (Exception ex){
            addproduct_pane.setVisible(false);
        }finally {
            reset_submitform();
            init_SellerProducts();
            init_SubmittedItems();
        }

    }
    private void reset_submitform() {
        p_name.setText("");
        p_price.setText("");
        categories.getSelectionModel().clearSelection();
        sessionDate.getSelectionModel().clearSelection();
        sessionTime.getSelectionModel().clearSelection();
        p_image.setImage(new Image(getClass().getResource("..\\Resources\\Images\\relogin.png").toExternalForm()));
    }
    void seenNotification(JFXButton seenbtn) {
        //JFXButton seenbtn = (JFXButton)event.getSource();
        new FadeOutLeftBigTransition(seenbtn.getParent()).play();
        new Timeline(new KeyFrame(Duration.millis(1000),event1 -> {
            notificationpane.getChildren().remove(seenbtn.getParent());
        })).play();
        notificationNumber--;
        notifierBadge.setText(String.valueOf(notificationNumber));
    }
    private void notifiyuser() {
        notificationNumber++;
        notifierBadge.setText(String.valueOf(notificationNumber));
        //SnackBar
        snackbar.fireEvent(new JFXSnackbar.SnackbarEvent("You received a new Notification ! ", "Show", 3000, b -> {
            notify_pane.setVisible(true);
        }));
    }
    private void SetAcceptMsg(FontAwesomeIconView e) {
        e.setVisible(true);
        e.setStyle("-fx-fill: #44B449; -fx-font-weight: bolder;-fx-font-size: 1.5em");
        e.setGlyphName("CHECK_CIRCLE");  //DEH FUNCTION BE CHANGE EL ICON BE EL NAME
    }
    private void SetErrorMsg(FontAwesomeIconView e){
        e.setVisible(true);
        e.setStyle("-fx-fill: red; -fx-font-weight: bolder;-fx-font-size: 1.5em");
        e.setGlyphName("TIMES_CIRCLE");
    }
    public boolean isequal(Date current, Date sessiondate){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal1.setTime(current);
        cal2.setTime(sessiondate);
        int year = cal1.get(Calendar.YEAR) ,
                month = cal1.get(Calendar.MONTH) + 1,
                day = cal1.get(Calendar.DAY_OF_MONTH);

        int y = cal2.get(Calendar.YEAR) , m = cal2.get(Calendar.MONTH) +1, d = cal2.get(Calendar.DAY_OF_MONTH);

        return year == y && month == m && day == d;
    }




    //<editor-fold desc = "Implemented Methods">
    @Override
    public int getminute() {
        return 0;
    }
    @Override
    public int getnanosecond() {
        return 0;
    }

    @Override
    public void sendChatMsg(String msg) {

    }

    @Override
    public void receiveChatMsg(int id, String profilepic, String msg) {

    }

    @Override
    public int getsecond() {
        return 0;
    }
    @Override
    public void Notifyuser(String msg) {
        notification n = new notification(msg);
        n.seen.setOnAction(event -> seenNotification(n.seen));
        notificationpane.getChildren().add(n);
        notifiyuser();
    }
    @Override
    public int evaluateBids(int currenthour) {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date current = new Date();
        Date sessiondate = null;
        System.out.println("Evaluation Sessions ...");
        for (Sessions s: sessionlist) {
            try {
                sessiondate = format.parse(s.getSession_date());
            } catch (ParseException e) {e.printStackTrace(); }

            if (isequal(current,sessiondate))
            {
                System.out.println("FOUND ! \n SessionStartTime = "+s.getStart_time()+ " currenthour = "+currenthour);
               if(s.getStart_time() == currenthour) {
                   System.out.println("There's a Session Work Starting Now !");
                   //any action when item session start
                    return 7;
               }else if (s.getEnd_time() == currenthour) {     /*  also logical error */
                    System.out.println("There's a Session Work Ending Now !");
                    return 8;
               }
               else {
                   System.out.println("No Start or End at this time !");
               }
            }
        }
        return 0;   //will not evaluate results
    }
    @Override
    public void setYear(int y) {
        Label second = (Label)Timer.getChildren().get(8);
        second.setText(y+"");
    }
    @Override
    public int evaluateResults(int currenthour) {
        init_SellerProducts();     //as notifier will not call this if there's a result
        return 9;
    }
    @Override
    public void setnanosecond(int n){
        Label hour = (Label)Timer.getChildren().get(6);
        hour.setText(n+"");
    }
    @Override
    public void sethour(int h){
        Label hour = (Label)Timer.getChildren().get(1);
        hour.setText(h+"");
    }
    @Override
    public void setminute(int m){
        Label minute = (Label)Timer.getChildren().get(3);
        minute.setText(m+"");
    }
    @Override
    public void setsecond(int s){
        Label second = (Label)Timer.getChildren().get(5);
        second.setText(s+"");
    }
    @Override
    public void setam_pm(int x) {
        VBox ampm_pane = (VBox)Timer.getChildren().get(7);
        Label am = (Label)ampm_pane.getChildren().get(0);
        Label pm = (Label)ampm_pane.getChildren().get(1);
        if (x == 0){
            am.setTextFill(Paint.valueOf("#FFF"));
            pm.setTextFill(Paint.valueOf("#837f7f"));
        } else {
            pm.setTextFill(Paint.valueOf("#FFF"));
            am.setTextFill(Paint.valueOf("#837f7f"));
        }
    }
    @Override
    public SystemUser getCurrentUser() {
        return this.user;
    }
   //</editor-fold>



    //Dynamic Classes
    private class item extends VBox {
        private final Label ProductName;
        private final Label SessionTime;
        private final ImageView ProductImage;
        private final Label ProductPrice;
        public  final JFXButton ShowParticipated;
        public int productstate;
        public int reservedstate;
        public int itemid;

        // ! Important Components
        private final Label label0;
        private final HBox hBox;
        private final Label label1;
        private final Label ampm;
        private final HBox hBox0;
        private final FontAwesomeIconView fontAwesomeIconView;
        private final DropShadow dropShadow0;
        private final DropShadow dropShadow;
        private final MaterialDesignIconView materialDesignIconView;

        public item(String itemID, String name ,String productimagepath ,
                    String productprice, String sessiontime , int state,int rs)    //state = 0 'pending' , 1 'accept'
        {
            productstate = state;
            reservedstate = rs;
            itemid = Integer.parseInt(itemID);
            ProductName = new Label();
            SessionTime = new Label();
            dropShadow = new DropShadow();
            materialDesignIconView = new MaterialDesignIconView();
            ProductImage = new ImageView();
            label0 = new Label();
            hBox = new HBox();
            label1 = new Label();
            ProductPrice = new Label();
            hBox0 = new HBox();
            ShowParticipated = new JFXButton();
            fontAwesomeIconView = new FontAwesomeIconView();
            dropShadow0 = new DropShadow();


            ProductName.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            ProductName.setTextFill(javafx.scene.paint.Color.valueOf("#FFF"));
            ProductName.setFont(new Font("SansSerif Bold", 20.0));


            VBox.setVgrow(SessionTime, javafx.scene.layout.Priority.NEVER);
            SessionTime.setPrefHeight(37.0);
            SessionTime.setMinWidth(120);
            SessionTime.setRotate(-20.6);
            SessionTime.setStyle("-fx-background-color: rgba(65, 184, 230, 1);");
            SessionTime.setText("5 : 30 AM");
            SessionTime.setTextFill(javafx.scene.paint.Color.WHITE);
            SessionTime.setFont(new Font(16.0));
            VBox.setMargin(SessionTime, new Insets(25.0, 125.0, 10.0, 10.0));
            int time = Integer.parseInt(sessiontime);
            if (time > 12)
                ampm = new Label("PM");
            else
                ampm = new Label("AM");

            ampm.setTextFill(javafx.scene.paint.Color.WHITE);
            ampm.setFont(new Font(16.0));
            ampm.setRotate(-20.6);
            ampm.setFont(new Font(13.0));
            ampm.setTranslateY(-57);  ampm.setTranslateX(-18);

            dropShadow.setHeight(26.93);
            dropShadow.setRadius(11.4825);
            SessionTime.setEffect(dropShadow);
            SessionTime.setPadding(new Insets(1.0, 3.0, 1.0, 3.0));

            materialDesignIconView.setIcon(MaterialDesignIcon.ALARM);
            materialDesignIconView.setSize("40");
            materialDesignIconView.setFill(Paint.valueOf("#FFFF"));
            SessionTime.setGraphic(materialDesignIconView);

            //For set pending or show participate
            this.setstateview();

            VBox.setVgrow(ProductImage, javafx.scene.layout.Priority.NEVER);
            ProductImage.setFitHeight(121.0);
            ProductImage.setFitWidth(180.0);
            ProductImage.setPreserveRatio(true);
            VBox.setMargin(ProductImage, new Insets(10.0, 0.0, 0.0, 0.0));

            VBox.setVgrow(label0, javafx.scene.layout.Priority.NEVER);
            label0.setText("Starting Prize");
            label0.setTextFill(javafx.scene.paint.Color.WHITE);
            label0.setFont(new Font(16.0));
            VBox.setMargin(label0, new Insets(0.0, 75.0, 0.0, 0.0));

            hBox.setAlignment(javafx.geometry.Pos.CENTER);
            hBox.setPrefHeight(46.0);
            hBox.setPrefWidth(269.0);
            hBox.setSpacing(10.0);

            label1.setText("$");
            label1.setTextFill(javafx.scene.paint.Color.valueOf("#ffe7e7"));
            label1.setFont(new Font("System Bold", 18.0));

            ProductPrice.setText("0.0");
            ProductPrice.setTextFill(javafx.scene.paint.Color.valueOf("#ffe4e4"));
            ProductPrice.setFont(new Font("System Bold", 16.0));

            hBox0.setAlignment(javafx.geometry.Pos.CENTER);
            hBox0.setPrefHeight(42.0);
            hBox0.setPrefWidth(199.0);


            VBox.setMargin(hBox0, new Insets(0.0, 0.0, 20.0, 0.0));
            dropShadow0.setBlurType(javafx.scene.effect.BlurType.ONE_PASS_BOX);
            dropShadow0.setHeight(36.58);
            dropShadow0.setRadius(19.0225);
            dropShadow0.setSpread(0.41);
            dropShadow0.setWidth(41.51);
            setEffect(dropShadow0);

//////////////////////////////////////////////////////////////////////////

            //item Initialization
            setAlignment(javafx.geometry.Pos.TOP_CENTER);
            setMinSize(240.0,350.0);
            setMaxSize(240.0,350.0);
            setStyle("-fx-background-color: rgba(204, 220, 246, 1); -fx-background-color: rgba(79, 122, 130, 1);");

            this.ProductName.setText(name);
            this.ProductImage.setImage(new Image(getClass().getResource(productimagepath).toExternalForm()));
            this.ProductPrice.setText(productprice);
            this.SessionTime.setText(sessiontime+" : 00");
            ShowParticipated.setId(itemID);  //init bottom with id same as item name


///////////////////////////////////////////////////////////////////////////

            getChildren().add(SessionTime);
            getChildren().add(ampm);
            getChildren().add(ProductName);
            getChildren().add(ProductImage);
            getChildren().add(label0);
            hBox.getChildren().add(label1);
            hBox.getChildren().add(ProductPrice);
            getChildren().add(hBox);
            hBox0.getChildren().add(ShowParticipated);
            getChildren().add(hBox0);
        }

        public void setstateview(){
            ShowParticipated.setFont(new Font(16.0));
            ShowParticipated.setGraphic(fontAwesomeIconView);
            ShowParticipated.setStyle("-fx-background-color: limegreen");

            if (this.productstate == 0){   //pending
                fontAwesomeIconView.setIcon(FontAwesomeIcon.REFRESH);
                fontAwesomeIconView.setSize("17");
                ShowParticipated.setPrefWidth(200.0);
                ShowParticipated.setText("  Pending");
            }else if(this.productstate == 1 && this.reservedstate == 0){   //accept and not finished
                ShowParticipated.setStyle("-fx-background-color: rgba(241, 214, 35, 1);");
                fontAwesomeIconView.setIcon(FontAwesomeIcon.GROUP);
                fontAwesomeIconView.setSize("15");
                ShowParticipated.setText("Show Participated");
                ShowParticipated.setOnAction(SellerController.this::showParticipants);
            }else if (this.reservedstate == 1){     //accepted and finished its session [need to know winner]
                fontAwesomeIconView.setIcon(FontAwesomeIcon.CHECK);
                fontAwesomeIconView.setSize("17");
                ShowParticipated.setPrefWidth(200.0);
                ShowParticipated.setText("  Sold [See Winner]");
                ShowParticipated.setOnAction(SellerController.this::showWinnerDetails);
            }
        }
    }
    private class notification extends VBox {

        protected final Label label;
        protected final JFXButton seen;

        public notification(String msg) {

            label = new Label();
            seen = new JFXButton();

            setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            setPrefHeight(86.0);
            setPrefWidth(485.0);
            setStyle("-fx-background-color: white;");

            VBox.setVgrow(label, javafx.scene.layout.Priority.ALWAYS);
            label.setAlignment(javafx.geometry.Pos.TOP_LEFT);
            label.setPrefHeight(66.0);
            label.setPrefWidth(483.0);
            label.setText(msg);
            label.setPadding(new Insets(2,0,0,2));
            label.setTextAlignment(TextAlignment.LEFT);
            label.setWrapText(true);
            label.setFont(new Font("Arial Narrow", 18.0));

            seen.setStyle("-fx-background-color: #409ae4;");
            seen.setPrefWidth(70);
            seen.setTextFill(Color.WHITE);
            seen.setText("Seen");
            VBox.setMargin(seen, new Insets(0.0, 0.0, 0.0, 400.0));
            setPadding(new Insets(5.0, 0.0, 5.0, 0.0));
            getChildren().add(label);
            getChildren().add(seen);

        }
    }


}
