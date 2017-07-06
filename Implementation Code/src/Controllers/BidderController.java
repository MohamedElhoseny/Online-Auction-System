package Controllers;

import Animation.Transition.*;
import Models.*;
import com.jfoenix.controls.*;
import com.sun.javafx.font.freetype.HBGlyphLayout;
import com.sun.org.apache.xml.internal.security.signature.reference.ReferenceNodeSetData;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Elhosany on 4/8/2017.
 */
@FXMLController(value = "../Views/BidderView.fxml")
public class BidderController implements UsersController
{
    //<editor-fold desc="FXML References">
    @FXML
    private FontAwesomeIconView setting;
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
    private StackPane background;
    @FXML
    private JFXButton signout;
    @FXML
    private Pane submittedPane;
    @FXML
    private FontAwesomeIconView hidesubmittedpane;
    @FXML
    AnchorPane slider;

    //ScrollPane
    @FXML
    AnchorPane body;
    @FXML
    ScrollPane scrollpane;
    @FXML
    JFXButton scrollup;
    @FXML
    JFXButton scrolldown;

    //info Pane
    @FXML
    Label UserName;
    @FXML
    ImageView profilePicture;
    @FXML
    private Pane edit_infoPlan;
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
    private FontAwesomeIconView editname , minimize;


    //Welcome Pane
    @FXML
    private HBox welcomepane;
    @FXML
    private HBox welcmsg;
    @FXML
    private BorderPane Parent;
    @FXML
    private Label welcomeUserName;



    //Product Details
    @FXML
    private Pane detail_pane;
    @FXML
    private FontAwesomeIconView closeDetail;

    //search bar
    @FXML
    private HBox searchbar;
    @FXML
    private JFXButton resetsearch;
    @FXML
    private HBox menubar;
    @FXML
    private JFXButton search;
    @FXML
    private JFXButton executeSearch;  /* for execute search function*/
    @FXML
    private JFXTabPane tabmenu;
    @FXML
    private JFXButton hideSearchbar;
    @FXML
    private JFXTextField searchInput;
    @FXML
    private JFXComboBox<String> categories;
    @FXML
    private JFXComboBox<String> sessions;
    @FXML
    private JFXComboBox<String> dates;


    //Notification Pane
    @FXML
    private BorderPane notify_pane;
    @FXML
    private JFXButton closeNotify;
    @FXML
    private JFXButton showNotifyPane;
    @FXML
    private VBox notification;     //need to generate to use
    @FXML
    private FlowPane notificationContainer;

    //notification Mapping
    @FXML
    private JFXBadge notifierBadge;
    @FXML
    private JFXSnackbar snackbar;
    @FXML
    private StackPane root;
    @FXML
    private TextFlow textflowpane;

    //Currently bid
    @FXML
    private StackPane Currentbid_Pane;
    @FXML
    private FontAwesomeIconView hide_currentbidpane;
    @FXML
    private FlowPane ProductsPane;

    //Bid
    @FXML
    private AnchorPane bidpane;

    //no bid
    @FXML
    VBox nobidpane;
    @FXML
    private ImageView b_img;
    @FXML
    private JFXButton b_join;
    @FXML
    private Label b_name;
    @FXML
    private Label b_seller;
    @FXML
    private Label b_end;
    @FXML
    JFXButton close_bid;

    //view Details
    @FXML
    private Label s_name;
    @FXML
    private Label s_starttime;
    @FXML
    private Label s_endtime;
    @FXML
    private Label s_category;
    @FXML
    private Label s_seller;
    @FXML
    private Label s_price;
    @FXML
    private Label s_date;
    @FXML
    private JFXButton s_participate;
    @FXML
    private ImageView s_img;

    //Time Pane
    @FXML
    private HBox TimerPane;
    private HBox Timer;


    //SessionRoom
    @FXML
    private AnchorPane sessionRoom,inbid,bidresult,resultdetail;
    @FXML
    private JFXButton leavebtn,submitbtn,rdetbtn;
    @FXML
    public Label s_min , s_sec,s_itemname,s_itemseller,s_itemprice,handleinput ,rankresult,resultmsg;
    @FXML
    public Label pname,pprice,pseller,pcat,wname,wphone,wsubmitprice;
    @FXML
    public ImageView s_itemimg , pimg,wimg;
    @FXML
    public JFXTextField submitinput;
    /** Ranks Cards Pane*/
    @FXML
    public HBox RANK1,RANK2,RANK3;
    @FXML
    public VBox under3;
    @FXML
    public FlowPane under1;
    //Chat
    @FXML
    private AnchorPane ChatPane , chatadvertise;
    @FXML
    private Label biddername , chat1,chat2,chat3;
    @FXML
    private JFXButton closechat , sendbtn;
    @FXML
    private JFXTextArea messageArea;
    @FXML
    private VBox chatroom;
    @FXML
    private FontAwesomeIconView chatstate1,chatstate2,chatstate3;

    //</editor-fold>

    //out-of-gui
    private GUI_BAG bag;
    private Member user;
    private Stage window ;
    private int notificationNumber = 0;
    private SchedulerNotifier schedulerNotifier;
    //Static Data
    private ArrayList<Category> categoryArrayList;
    private ArrayList<Sessions> sessionsesArratList;
    private ArrayList<Sessions> AllSessions;
    private ArrayList<item> ProductsView;
    private ArrayList<Item> Products;
    private ArrayList<topRank> topcards = null;
    private ArrayList<Object[]> incurrentSession = null;  //ID,Fname,Lname,profilePic,phone,price
    private Item currentinSession = null;

    @PostConstruct
    public void init()
    {
        bag = GUI_BAG.get_Bag();
        window = (Stage) bag.get_Registered_Component("primaryStage");
        user = (Member) bag.get_Registered_Component("user");

        init_basic_event();
        init_Timer();
        init_user_view();
        init_ProductsItem();
        init_NavigationPane();
        init_Notification();
        init_ScheduleNotifier();
    }

    //<editor-fold desc="init Methods">
    private void init_basic_event()
    {
        String path = getClass().getResource("../Resources/Sounds/like.mp3").toString();
        new MediaPlayer(new Media(path)).play();
        //init drop down list for categories and sessions
        Category x = new Category();
        x.initializeCategories();
        categoryArrayList = new ArrayList<>();
        categoryArrayList = x.CategoryList;
        for (Category c:categoryArrayList) {
            categories.getItems().add(c.getCat_Name());
        }


        Sessions y = new Sessions();
        y.initializeSessions();
        AllSessions = y.getSessionList();

        sessionsesArratList = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date inputDate;    Date current = new Date();
        String inputString = "11-11-2012";
        for (Sessions c:y.getSessionList()) {        //100%
            inputString = c.getSession_date();
            try {
                inputDate = dateFormat.parse(inputString);
                if (inputDate.after(current) || isequal(current,inputDate)) {    //as no need to add others [logical error]
                    if (!dates.getItems().contains(c.getSession_date())) {
                        dates.getItems().add(c.getSession_date());
                        sessionsesArratList.add(c);    //fe el a5r htl3 be dates msh mtkrren 3shan search h7tago
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        sessions.setOnMouseClicked(event -> {      //100%
            sessions.getItems().clear();
            String selecteddate = dates.getSelectionModel().getSelectedItem();
            System.out.println("selected date was = "+selecteddate);
            for (Sessions s: AllSessions) {
                if (s.getSession_date().equals(selecteddate))
                    sessions.getItems().add(s.getStart_time()+":00");
            }
            if (sessions.getItems().isEmpty())
                sessions.getItems().add("No Available Session !");
        });



        //Scroll work
        scrolldown.setOnAction(event -> {
            double newval = scrollpane.getVvalue();
            scrollpane.setVvalue(newval+=0.34315612);
        });
        scrollup.setOnAction(event -> {
            double newval = scrollpane.getVvalue();
            scrollpane.setVvalue(newval-=0.34315612);
        });


        hide_currentbidpane.setOnMouseClicked(event -> Currentbid_Pane.setVisible(false));

        showNotifyPane.setOnMouseClicked(event -> {
            new FadeInLeftBigTransition(notify_pane).play();
            new Timeline(new KeyFrame(Duration.millis(500),event1 -> notify_pane.setVisible(true))).play();
        });
        closeNotify.setOnMouseClicked(event -> {
            //new FadeOutLeftBigTransition(notify_pane).play();
            //new Timeline(new KeyFrame(Duration.millis(500),event1 -> notify_pane.setVisible(false))).play();
            notify_pane.setVisible(false);
        });

        snackbar.registerSnackbarContainer(root);

        search.setOnMouseClicked(event -> {
            searchbar.setVisible(true);
            menubar.setVisible(false);
        });
        hideSearchbar.setOnAction(event -> {
            searchbar.setVisible(false);
            menubar.setVisible(true);
            //use the static data not to looping again to init
            ProductsPane.getChildren().setAll(ProductsView);
        });

        //welcome pane
        new BounceInTransition(welcmsg).play();
        new Timeline(new KeyFrame(Duration.millis(2000), event -> {
            welcomepane.setVisible(false);
            Parent.setVisible(true);
        })).play();

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
              //  schedulerNotifier.closeServices();
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
            updateinfo.setStyle("-fx-background-color:  #3099d7");
            updateinfo.setText("Save");
            edit_infoPlan.setVisible(true);
        });
        hideInfo.setOnMouseClicked(event -> {
            edit_infoPlan.setVisible(false);
        });


        closeDetail.setOnMouseClicked(event -> {
            detail_pane.setVisible(false);
            Parent.setEffect(new InnerShadow());
        });

        close_bid.setOnAction(event -> Currentbid_Pane.setVisible(false));

        /** Searching process */
        searchInput.textProperty().addListener(event -> SearchAlgorithmFilteration() );
        categories.setOnAction(event -> SearchAlgorithmFilteration());
        sessions.setOnAction(event -> SearchAlgorithmFilteration());
        dates.setOnAction(event -> SearchAlgorithmFilteration());


        resetsearch.setOnAction(event -> {
            categories.getSelectionModel().clearSelection();
            sessions.getSelectionModel().clearSelection();
            dates.getSelectionModel().clearSelection();
        });

        //Draggable
        bag.setDraggable(window,root);

        minimize.setOnMouseClicked(event -> window.setIconified(true));

        // startAnimation(background);
        new Timeline(
                new KeyFrame(Duration.millis(10),event -> new FadeInDownBigTransition(ProductsPane).play())
        ).play();

        /** session room basic btn   */
        b_join.setOnAction(event -> {
            sessionRoom.setVisible(true);
            init_SessionRoom();
            ManageSessionRoom();
            RenderView();
        });
        leavebtn.setOnAction(event -> {
            sessionRoom.setVisible(false);
            Currentbid_Pane.setVisible(false);
            schedulerNotifier.closeChatService();
        });
        submitbtn.setOnAction(event -> {
            String in = submitinput.getText();
            double newprice;
            if (!in.isEmpty()){
                try {
                    newprice = Double.parseDouble(in);
                    if (newprice > currentinSession.getPrice()){  //So Constraint with seller price not max price
                        //////////////////// Submitting New Price and update View
                        currentinSession.submitnewPrice(currentinSession.getSession_ID(),currentinSession.getId(),
                                user.getId(),in);   //update db
                        RenderView();        //refresh view
                        handleinput.setVisible(false);       //hide error msg 'no error'
                    }else
                        handleinput.setVisible(true);
                }catch (Exception e){
                    handleinput.setVisible(true);
                }
            }else
                handleinput.setVisible(true);
        });

        rdetbtn.setOnAction(event -> {
            new Timeline(
                    new KeyFrame(Duration.millis(500),
                            new KeyValue(bidresult.layoutXProperty(),97, Interpolator.EASE_BOTH),
                            new KeyValue(bidresult.layoutYProperty(),200,Interpolator.EASE_BOTH)),
                    new KeyFrame(Duration.millis(1000),
                            new KeyValue(bidresult.layoutXProperty(),103, Interpolator.EASE_BOTH),
                            new KeyValue(bidresult.layoutYProperty(),244,Interpolator.EASE_BOTH)),
                    new KeyFrame(Duration.millis(1000),event1 -> resultdetail.setVisible(true)),
                    new KeyFrame(Duration.millis(500),event1 -> new FadeInRightBigTransition(resultdetail).play())
            ).play();
        });

        //start bg slider
        bgslider(background);

        //chat
        closechat.setOnAction(event -> {
            new FadeOutRightBigTransition(ChatPane).play();
            new FadeInTransition(chatadvertise).play();
        });
    }
    private void init_user_view()
    {
        welcomeUserName.setText(user.getFname() +" "+user.getLname());
        profilePicture.setImage(new Image(getClass().getResource(user.getProfilePic()).toExternalForm()));
        UserName.setText(welcomeUserName.getText());
        Fname.setText(user.getFname());
        Lname.setText(user.getLname());
        email.setText(user.getEmail());
        pass.setText(user.getPassword());
        reenterpass.setText(user.getPassword());

        updateinfo.setOnAction(event -> {

            if (pass.getText().equals(reenterpass.getText()) && !pass.getText().isEmpty()){
                SetAcceptMsg(editpassicon);
                if (Fname.getText().isEmpty() || Lname.getText().isEmpty())
                    SetErrorMsg(editname);
                else {
                    SetAcceptMsg(editname);
                    user.editInfo(user,Fname.getText(),Lname.getText(),pass.getText());
                    UserName.setText(user.getFname()+" "+user.getLname());   //refresh
                    updateinfo.setText("Saved");
                    updateinfo.setStyle("-fx-background-color: limegreen");
                }
            }else{
                updateinfo.setText("Save");
                updateinfo.setStyle("-fx-background-color:  #3099d7");
                SetErrorMsg(editpassicon);
            }
        });
    }
    private void init_ProductsItem()
    {
        int currenthour = LocalDateTime.now().getHour();
        ProductsPane.getChildren().clear();
        Products = user.membertype.viewProducts();   //get accept and not served item
        ProductsView = new ArrayList<>();

        //to get only items which session time is equal to current or after not before
        Date current = new Date();
        Date sessiondate = null;
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        for (Item item : Products){
            try {
                sessiondate = format.parse(item.getsession().getSession_date());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (sessiondate.after(current) || (isequal(current,sessiondate) && item.getsession().getStart_time() >= currenthour) ){    //will add only them ^_^
                //init
                item viewitem = new item(item.getId()+"",item.getItem_name(),item.getCatName(),item.getpicture(),item.getPrice()+"",4 ,item.getsession().getStart_time(),item.getsession().getSession_date());
                //Set Events
                viewitem.participate.setOnAction(event -> {
                    viewitem.participate_item();
                    try { participate(event); } catch (IOException e) {e.printStackTrace();}
                });
                viewitem.details.setOnAction(this::_ShowProductDetails);
                //Check if this bidder participate to this item
                if (user.membertype.isParticipated(item.getId())) {
                    viewitem.participate_item();
                }

                if (item.getsession().getStart_time() == currenthour && isequal(current,sessiondate)) //same time and date
                    viewitem.setactive();
                else
                    viewitem.setinactive();


                //Add to Products List
                ProductsView.add(viewitem);
            }
        }
        ProductsPane.getChildren().addAll(ProductsView);
    }
    private void init_NavigationPane()
    {
        //initialize the contents of Tabs
        for (Category c: categoryArrayList) {
            String catname = c.getCat_Name();
            Tab newtab = new Tab(catname);
            newtab.setId(catname);
            tabmenu.getTabs().add(newtab);
        }
        tabmenu.setOnMouseClicked(event -> NavContentChange());
    }
    private void init_Notification()   //Offline Notification Done 100%
    {
        ArrayList<String> Notifications = user.getNotifications(user.getId());
        for (String msg: Notifications) {
            NotificationMessage n1 = new NotificationMessage(msg);
            n1.seen.setOnAction(event -> seenNotification(n1.seen));
            notificationContainer.getChildren().add(n1);
            notificationNumber++;
            notifierBadge.setText(String.valueOf(notificationNumber));
            ShowNavigationNotify(msg);
        }

    }
    private void init_Timer()
    {
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
    private void init_ScheduleNotifier()
    {
        schedulerNotifier = new SchedulerNotifier();
        schedulerNotifier.initUser(this);
    }
    //</editor-fold>

    //<editor-fold desc="Global Methods">
    @FXML private void _ShowProductDetails(ActionEvent e) {
        //Set effect
        Parent.setEffect(new GaussianBlur());
        //Get Selected Button
        JFXButton btn = (JFXButton)e.getSource();
        //Get Product Data
        int id = Integer.valueOf(btn.getId());
        Item selecteditem = user.viewDetails(id);
        //Set Data to Pane
        s_category.setText(selecteditem.getcategory().getCat_Name());
        s_endtime.setText(selecteditem.getsession().getEnd_time()+"");
        s_img.setImage(new Image(getClass().getResource(selecteditem.getpicture()).toExternalForm()));
        s_name.setText(selecteditem.getItem_name());
        s_price.setText(selecteditem.getPrice()+"");
        s_date.setText(selecteditem.getsession().getSession_date());
        s_starttime.setText(selecteditem.getsession().getStart_time()+"");
        s_seller.setText(selecteditem.getSeller().getFname());
        if (user.membertype.isParticipated(id)){
            s_participate.setDisable(true);
            s_participate.setStyle("-fx-background-color: lime");
        }else{
            s_participate.setId(btn.getId());  //set same id to this btn to handle action from it in participate fun
            s_participate.setStyle("-fx-background-color:  #249AF4");
            s_participate.setDisable(false);
        }
        //Show Pane to User
        detail_pane.setVisible(true);
    }
    @FXML private void participate(ActionEvent e) throws IOException {
        JFXButton btn = (JFXButton)e.getSource();
        int selecteditemID = Integer.parseInt(btn.getId());
        user.membertype.participate(user.getId(),selecteditemID);   //save to db

     /*   Item temp = new Item();
        temp.initializeItems();
        SystemUser itemSeller = null;
        for (Item i: temp.getItemList()) {
            if (i.getId() == selecteditemID){
                itemSeller = i.getSeller();
                break;
            }
        }
        if (itemSeller != null){
            ArrayList<SystemUser> notifiers = new ArrayList<>();
            notifiers.add(itemSeller);
          //  schedulerNotifier.Sender(notifiers,"New Bidder Participated to ur item");   comment when comment socket
        }

      */
        //as if participated from details pane
        if (detail_pane.isVisible()){
            detail_pane.setVisible(false);
            Parent.setEffect(new InnerShadow());
        }
        refreshProductItems(selecteditemID);
    }
    @FXML private void check_currently_bids(){
        System.out.println("Checking currently in bid !!");
        Label h = (Label)Timer.getChildren().get(1);
        currentinSession = null;

        int currenthour = Integer.parseInt(h.getText());
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date current = new Date();
        Date sessiondate = null;


        for (Item i: Products) {   /** Not check for product check for user partic product only */
            try {
                sessiondate = format.parse(i.getsession().getSession_date());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (user.membertype.isParticipated(i.getId())){
               if (isequal(current,sessiondate) && i.getsession().getStart_time() == currenthour){   //get item which session start at this hour
                   currentinSession = i;
                   break;
               }
           }
        }
        if (currentinSession != null){  //must be null after session finished
            bidpane.setVisible(true);
            b_img.setImage(new Image(getClass().getResource(currentinSession.getpicture()).toExternalForm()));
            b_name.setText(currentinSession.getItem_name());
            b_seller.setText(currentinSession.getSellerName());
            b_end.setText(currentinSession.getsession().getEnd_time()+"");
        }else
            bidpane.setVisible(false);


        Currentbid_Pane.setVisible(true);
    }
    @FXML private void setFullscreen(){
        if (!window.isFullScreen()) {
            window.setFullScreen(true);
        }
    }

    //<editor-fold desc="Session Room">
    private void init_SessionRoom(){
        bidresult.setLayoutX(341.0);
        bidresult.setLayoutY(182.0);
        new FadeInTransition(inbid).play();
        bidresult.setVisible(false);
        resultdetail.setVisible(false);
        //init submit
        s_itemname.setText(currentinSession.getItem_name());
        s_itemseller.setText(currentinSession.getSellerName());
        s_itemprice.setText(currentinSession.getPrice() +"");
        s_itemimg.setImage(b_img.getImage());  //as b_img is same and its loaded so dont need to load again;
        schedulerNotifier.startChatServices();
    }
    private void ManageSessionRoom(){
        s_min.setText((59 - getminute())+"");
        s_sec.setText(59 - getsecond()+"");
        AnimationTimer timer = new AnimationTimer() {
            int minute = 59 - getminute();  //to get the remaining minute for closing session
            int second = Integer.parseInt(s_sec.getText());
            int nanosecond = getnanosecond();

            @Override
            public void handle(long now) {
                nanosecond++;
                if (nanosecond >= 60) {
                    nanosecond = 0;
                    if (minute == 0 && second == 0) {   //basecase
                        setmin(00);
                        setsec(00);
                        HandleAction();
                        this.stop();
                    }else if (second == 0){
                        second = 60;
                        setsec(60);
                        setmin(--minute);
                      //  HandleAction();  //for test
                      //  this.start();   //for test
                    }else
                        setsec(--second);
                }
            }
            public void setmin(int min){
                s_min.setText(min+"");
            }
            public void setsec(int sec){
                s_sec.setText(sec+"");
            }
            public void HandleAction(){
                System.out.println("******* Current Session was Ended ! *******");
                evaluateSession();
            }
        };
        timer.start();
    }
    private void RenderView(){
        ClearbordersandPane();
        topcards = new ArrayList<>();
        incurrentSession = currentinSession.getParticipatedBidders(currentinSession.getSession_ID(),currentinSession.getId());
        // incurrentSession   'ID,Fname,Lname,profilePic,phone,price'
        int size = incurrentSession.size();
        int count = 0;
        //attr
        String name,profilepic,phone;
        double price;
        int state;
        int id;

        for (int i = 0; i <size; i++) {
            id = (Integer) incurrentSession.get(i)[0];
            name = (String)incurrentSession.get(i)[1] +" "+(String)incurrentSession.get(i)[2];
            profilepic = (String)incurrentSession.get(i)[3];
            phone = (String)incurrentSession.get(i)[4];
            price = (Double) incurrentSession.get(i)[5];
            state = (Integer) incurrentSession.get(i)[6];
            topRank card = new topRank(i+1, profilepic,name,phone,state,price);
            if (user.getId() == id)
                card.setasuser();
            topcards.add(card);
            initChatSystem(card);  //to init chatting system
            count++;
            if (i == 2)
                break;
        }
        if (count < size){
            for (int i = count; i< size; i++){
                if (i == 7)
                    break;
                id = (Integer) incurrentSession.get(i)[0];
                name = (String)incurrentSession.get(i)[1] +" "+(String)incurrentSession.get(i)[2];
                profilepic = (String)incurrentSession.get(i)[3];
                phone = (String)incurrentSession.get(i)[4];
                price = (Double) incurrentSession.get(i)[5];
                state = (Integer) incurrentSession.get(i)[6];
                UnderRank card = new UnderRank(i+1,name,price);
                under3.getChildren().add(card);
                if (user.getId() == id)
                    card.setasuser();
            }
        }
    }
    private void evaluateSession(){
        new FlipOutXTransition(inbid).play();
        //init result details
        int size = incurrentSession.size();
        String name ; double price;
        int resultrank = 0;
        under1.getChildren().clear();

        for (int i=0; i<size; i++){
            name = incurrentSession.get(i)[1] +" "+incurrentSession.get(i)[2];
            price = (Double) incurrentSession.get(i)[5];

            UnderRank card = new UnderRank(i+1,name,price);
            if (user.getId() == (Integer) incurrentSession.get(i)[0]) {
                card.setasuser();
                resultrank = i + 1;
            }
            under1.getChildren().add(card);
            if (i == 6)
                break;
        }
        //for item
        pimg.setImage(new Image(getClass().getResource(currentinSession.getpicture()).toExternalForm()));
        pname.setText(currentinSession.getItem_name());
        pseller.setText(currentinSession.getSellerName());
        pcat.setText(currentinSession.getCatName());
        pprice.setText(currentinSession.getPrice()+"");
        //for winner
        wname.setText(incurrentSession.get(0)[1] +" "+incurrentSession.get(0)[2]);
        wimg.setImage(new Image(getClass().getResource((String)incurrentSession.get(0)[3]).toExternalForm()));
        wphone.setText((String)incurrentSession.get(0)[4]);
        wsubmitprice.setText((Double)incurrentSession.get(0)[5]+"");

        //init result to user
        rankresult.setText(resultrank+"");
        if (resultrank == 1)
            resultmsg.setText("Configuration ! For Winning Bid Session , We Will Contact You  , " +
                    "Best Wishes for next one.");
        else
            resultmsg.setText("Bad News !  You  Lose Your Bid Session , We Hope best for You in Next Time ");

        new Timeline(new KeyFrame(Duration.millis(700),event -> bidresult.setVisible(true))).play();
        new BounceInTransition(bidresult).play();
        UpdateDatabase();
    }
    private void UpdateDatabase() {
        int sessionID = currentinSession.getSession_ID();
        int winnerID = (Integer)incurrentSession.get(0)[0];
        //check if price of first is more than item set winner else will set null by default
        currentinSession.getsession().setWinnerUser(sessionID,winnerID);   //set winnerID
        currentinSession.setitemServed(currentinSession.getId());

        init_ProductsItem();  //to remove item [session finished]
    }
    //</editor-fold>

    //<editor-fold desc="Chat Room">
    private void initChatSystem(topRank rankcard){
        //used to know whose offline [not support to chat] and if user cannot chat with him self
        switch (rankcard.rank){
            case 1:
                if (rankcard.isuser)
                    chat1.setVisible(false);
                if (rankcard.isOnline)
                    chatstate1.setFill(Paint.valueOf("#30c944"));
                else
                    chatstate1.setFill(Paint.valueOf("#ff2e1f"));
                break;
            case 2:
                if (rankcard.isuser)
                    chat2.setVisible(false);
                if (rankcard.isOnline)
                    chatstate2.setFill(Paint.valueOf("#30c944"));
                else
                    chatstate2.setFill(Paint.valueOf("#ff2e1f"));
                break;
            case 3:
                if (rankcard.isuser)
                    chat3.setVisible(false);
                if (rankcard.isOnline)
                    chatstate3.setFill(Paint.valueOf("#30c944"));
                else
                    chatstate3.setFill(Paint.valueOf("#ff2e1f"));
                break;
            default:
                break;
        }
    }
    @FXML private void startChatting(MouseEvent e){
        Label clicked = (Label)e.getSource();
        String id = clicked.getId();

        topRank x = null;
        //if available [online] init chat pane with this biddername
        switch (id) {
            case "chat1":
                System.out.println("Chatting with Rank 1 Bidder");
                x = topcards.get(0);
                break;
            case "chat2":
                System.out.println("Chatting with Rank 2 Bidder");
                x = topcards.get(1);
                break;
            case "chat3":
                System.out.println("Chatting with Rank 3 Bidder");
                x = topcards.get(2);
                break;
        }
        if (x != null) {
            if (x.isOnline) {
                new FadeOutTransition(chatadvertise).play();
                if (!ChatPane.isVisible()) {
                    new Timeline(new KeyFrame(Duration.millis(800), event -> ChatPane.setVisible(true))).play();
                }
                new FadeInRightBigTransition(ChatPane).play();
            }
        }
    }
    @FXML private void sendChatMsg(ActionEvent e){
        String msg = messageArea.getText();
        messageArea.clear();
        schedulerNotifier.releaselock(msg);
    }

    @Override
    public void sendChatMsg(String msg){
        chatroom.getChildren().add(new Sendmsgpane(msg));
    }
    @Override
    public void receiveChatMsg(int id ,String profilepic ,String msg){
        chatroom.getChildren().add(new recievedmsgpane(id,profilepic,msg));
        //any thing not notify user
    }
    //</editor-fold>

    private void NavContentChange(){
        Tab selectedtab = tabmenu.getSelectionModel().getSelectedItem();
        String catname = selectedtab.getText();

        if (catname.equals("Home")) {
            ProductsPane.getChildren().setAll(ProductsView);
        }else {
            ArrayList<item> items = new ArrayList<>();
            for (item i: ProductsView) {
                if (i.category.equals(catname))
                    items.add(i);
            }
            ProductsPane.getChildren().setAll(items);
        }
    }
    private void SearchAlgorithmFilteration(){
        ArrayList<item> reqitem = new ArrayList<>();
        reqitem.clear();
        ProductsPane.setVisible(false);
        //get input string
        String input = searchInput.getText();

        if (input.isEmpty()){
            reqitem.addAll(ProductsView);
        }else{
            //listen to drop down lists
            int catindex = categories.getSelectionModel().getSelectedIndex();
            int ddindex = dates.getSelectionModel().getSelectedIndex();
            int sessindex = sessions.getSelectionModel().getSelectedIndex();


            Category cinput = null;
            Sessions sinput = null;
            Sessions dinput = null;

            if (catindex >= 0)
                cinput = categoryArrayList.get(catindex);

            if (ddindex >= 0) {
                System.out.println("selected index = "+ddindex);
                dinput = sessionsesArratList.get(ddindex);
                System.out.println("selected date = "+dinput);
            }

            if (sessindex >= 0)
                sinput = AllSessions.get(sessindex);



            //get items with selected conditions
            for (item s: ProductsView) {
                if (catindex >= 0 && sessindex >= 0 && ddindex >= 0) {   //by category , sessiontime , date
                    if (s.ProductName.getText().startsWith(input) && s.Startsession == sinput.getStart_time() && s.date.equals(dinput.getSession_date())
                            && s.category.equals(cinput.getCat_Name()))
                        reqitem.add(s);
                }
                else if (catindex >= 0 && sessindex < 0 && ddindex < 0){   //by category only
                    if (s.ProductName.getText().startsWith(input) && s.category.equals(cinput.getCat_Name()))
                        reqitem.add(s);
                }else if (catindex < 0 && sessindex >= 0  && ddindex < 0 ) {  //by start session only
                    if (s.ProductName.getText().startsWith(input) && s.Startsession == sinput.getStart_time())
                        reqitem.add(s);
                }else if (catindex < 0 && sessindex < 0 && ddindex >= 0 ) {   //by date only
                    if (s.ProductName.getText().startsWith(input) && s.date.equals(dinput.getSession_date()))
                        reqitem.add(s);
                }else if (catindex >= 0 && sessindex >= 0 && ddindex < 0 ) {   //by category with session time only
                    if (s.ProductName.getText().startsWith(input) && s.Startsession == sinput.getStart_time() && s.category.equals(cinput.getCat_Name()))
                        reqitem.add(s);
                }else if (catindex >= 0 && sessindex < 0 && ddindex >= 0 ) {   //by category with date only
                    if (s.ProductName.getText().startsWith(input) && s.date.equals(dinput.getSession_date()) && s.category.equals(cinput.getCat_Name()))
                        reqitem.add(s);
                }else if (catindex < 0 && sessindex >= 0 && ddindex >= 0 ) {   //by session with data only
                    if (s.ProductName.getText().startsWith(input) && s.date.equals(dinput.getSession_date()) && s.Startsession == sinput.getStart_time())
                        reqitem.add(s);
                }
                else if (s.ProductName.getText().startsWith(input))     //by input string only without filtering
                    reqitem.add(s);
            }
        }
        //add items to Pane
        ProductsPane.getChildren().setAll(reqitem);
        ProductsPane.setVisible(true);
    }
    private void refreshProductItems(int itemid){
        for (item i: ProductsView) {
            if (i.itemID == itemid){
                i.participate_item();
                break;
            }
        }
    }
    private void seenNotification(JFXButton seenbtn) {
        //JFXButton seenbtn = (JFXButton)event.getSource();
        new FadeOutLeftBigTransition(seenbtn.getParent()).play();
        new Timeline(new KeyFrame(Duration.millis(1000),event1 -> {
            notificationContainer.getChildren().remove(seenbtn.getParent());
        })).play();
        notificationNumber--;
        notifierBadge.setText(String.valueOf(notificationNumber));
    }
    private void ShowNavigationNotify(String notificationText) {
        textflowpane.getChildren().clear();
        Label text = new Label(notificationText);
        text.setFont(new Font(15));
        Reflection reflection = new Reflection(0.25,0.25,0.25, 0.25);
        text.setEffect(reflection);
        text.setTextFill(Paint.valueOf("#FFF"));
        text.setTranslateX(500);
        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.WARNING);
        icon.setFill(Paint.valueOf("#fada69"));   icon.setSize("20");
        text.setGraphic(icon);
        text.setLineSpacing(5.0);
        textflowpane.getChildren().addAll(text);


        List<KeyFrame> keyFrames = new ArrayList<>();
        keyFrames.add(new KeyFrame(Duration.seconds(1/8), new KeyValue(text.translateXProperty(), 150)));
        keyFrames.add(new KeyFrame(Duration.seconds(1/4), new KeyValue(text.translateXProperty(), 100)));
        keyFrames.add(new KeyFrame(Duration.seconds(1/2), new KeyValue(text.translateXProperty(), 50)));
        keyFrames.add(new KeyFrame(Duration.seconds(0), new KeyValue(text.translateXProperty(), 25)));
        keyFrames.add(new KeyFrame(Duration.seconds(2), new KeyValue(text.translateXProperty(), 0)));
        keyFrames.add(new KeyFrame(Duration.seconds(4), new KeyValue(text.translateXProperty(), -100)));
        keyFrames.add(new KeyFrame(Duration.seconds(6), new KeyValue(text.translateXProperty(), -200)));
        keyFrames.add(new KeyFrame(Duration.seconds(8), new KeyValue(text.translateXProperty(), -300)));
        keyFrames.add(new KeyFrame(Duration.seconds(10), new KeyValue(text.translateXProperty(), -400)));
        keyFrames.add(new KeyFrame(Duration.seconds(12), new KeyValue(text.translateXProperty(), -500)));
        keyFrames.add(new KeyFrame(Duration.seconds(14), new KeyValue(text.translateXProperty(), -600)));
        keyFrames.add(new KeyFrame(Duration.seconds(16), new KeyValue(text.translateXProperty(), -700)));
        keyFrames.add(new KeyFrame(Duration.seconds(18), new KeyValue(text.translateXProperty(), -800)));
        keyFrames.add(new KeyFrame(Duration.seconds(20), new KeyValue(text.translateXProperty(), -900)));
        keyFrames.add(new KeyFrame(Duration.seconds(22), new KeyValue(text.translateXProperty(), -1000)));
        keyFrames.add(new KeyFrame(Duration.seconds(24), new KeyValue(text.translateXProperty(), -1100)));
        keyFrames.add(new KeyFrame(Duration.seconds(26), new KeyValue(text.translateXProperty(), -1200)));

        Timeline anim = new Timeline(keyFrames.toArray(new KeyFrame[keyFrames.size()]));
        anim.play();
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
    private void ClearbordersandPane() {
        under3.getChildren().clear();
        RANK1.setStyle("-fx-border-color: black; -fx-border-width: 1");
        RANK2.setStyle("-fx-border-color: black; -fx-border-width: 1");
        RANK3.setStyle("-fx-border-color: black; -fx-border-width: 1");
    }
    //animator
    private void bgslider(StackPane root){
        double IMG_WIDTH = 1071;
        double IMG_HEIGHT = 200;

        Pane clipPane = new Pane();
        // To center the slide show incase maximized
        clipPane.setMaxSize(IMG_WIDTH, IMG_HEIGHT);
        clipPane.setClip(new Rectangle(IMG_WIDTH, IMG_HEIGHT));

        HBox imgContainer = new HBox();
        //image view

        ImageView img1  = new ImageView(new Image(getClass().getResource("../Resources/Images/bg/bg3.png").toExternalForm()));
        img1.setFitHeight(200);
        img1.setFitWidth(1071);
        img1.setStyle("-fx-background-position: left; -fx-background-size: auto");
        ImageView img2  = new ImageView(new Image(getClass().getResource("../Resources/Images/bg/bg2.png").toExternalForm()));
        img2.setFitHeight(200);
        img2.setFitWidth(1071);
        img2.setStyle("-fx-background-position: left; -fx-background-size: auto");

        ImageView img3  = new ImageView(new Image(getClass().getResource("../Resources/Images/bg/bg1.png").toExternalForm()));
        img3.setFitHeight(200);
        img3.setFitWidth(1071);
        img3.setStyle("-fx-background-position: left; -fx-background-size: auto");

        ImageView img4  = new ImageView(new Image(getClass().getResource("../Resources/Images/bg/bg4.png").toExternalForm()));
        img4.setFitHeight(200);
        img4.setFitWidth(1071);
        img4.setStyle("-fx-background-position: left; -fx-background-size: auto");

        imgContainer.getChildren().addAll(img1,img2,img3,img4);
        clipPane.getChildren().add(imgContainer);
        root.getChildren().add(clipPane);

        startAnimation(imgContainer,IMG_WIDTH, 4, 5 );
    }
    private void startAnimation(final HBox hbox , double IMG_WIDTH , int NUM_OF_IMGS , int SLIDE_FREQ) {
        //error occured on (ActionEvent t) line
        //slide action
        EventHandler<ActionEvent> slideAction = (ActionEvent t) -> {
            TranslateTransition trans = new TranslateTransition(Duration.seconds(1.5), hbox);
            trans.setByX(-IMG_WIDTH);
            trans.setInterpolator(Interpolator.EASE_BOTH);
            trans.play();
        };
        //eventHandler
        EventHandler<ActionEvent> resetAction = (ActionEvent t) -> {
            TranslateTransition trans = new TranslateTransition(Duration.seconds(1), hbox);
            trans.setByX((NUM_OF_IMGS - 1) * IMG_WIDTH);
            trans.setInterpolator(Interpolator.EASE_BOTH);
            trans.play();
        };
        //Create KeyFrame
        List<KeyFrame> keyFrames = new ArrayList<>();
        for (int i = 1; i <= NUM_OF_IMGS; i++) {
            if (i == NUM_OF_IMGS) {
                keyFrames.add(new KeyFrame(Duration.seconds(i * SLIDE_FREQ), resetAction));
            } else {
                keyFrames.add(new KeyFrame(Duration.seconds(i * SLIDE_FREQ), slideAction));
            }
        }
        //add timeLine
        Timeline anim = new Timeline(keyFrames.toArray(new KeyFrame[NUM_OF_IMGS]));
        anim.setCycleCount(Timeline.INDEFINITE);
        anim.playFromStart();
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

    //</editor-fold>

    //<editor-fold desc="Implemented methods [Notifier Methods]">
    @Override
    public void Notifyuser(String msg){
        NotificationMessage n = new NotificationMessage(msg);
        n.seen.setOnAction(event -> seenNotification(n.seen));
        notificationContainer.getChildren().add(n);
        notificationNumber++;
        notifierBadge.setText(String.valueOf(notificationNumber));
        ShowNavigationNotify(msg);
    }
    @Override
    public void setnanosecond(int n){
        Label hour = (Label)Timer.getChildren().get(6);
        hour.setText(n+"");
    }

    @Override
    public void setYear(int y) {
        Label second = (Label)Timer.getChildren().get(8);
        second.setText(y+"");
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
    public int getminute() {
        Label m = (Label)Timer.getChildren().get(3);
        return Integer.parseInt(m.getText());
    }
    @Override
    public int getsecond() {
        Label m = (Label)Timer.getChildren().get(5);
        return Integer.parseInt(m.getText());
    }
    @Override
    public int getnanosecond() {
        Label m = (Label)Timer.getChildren().get(6);
        return Integer.parseInt(m.getText());
    }
    @Override
    public int evaluateBids(int currenthour){
        Date current = new Date();
        Date sessiondate = null;
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        for (Item i : Products) {
            try {
                sessiondate = format.parse(i.getsession().getSession_date());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //notify bidder only if one of his participated items starts or ends
            if (isequal(current,sessiondate)  && user.membertype.isParticipated(i.getId())){
                if (i.getsession().getStart_time() == currenthour) {
                    init_ProductsItem();   //to show how products item changes
                    return 3;
                }else if(i.getsession().getEnd_time() == currenthour) {
                    init_ProductsItem();   //to show how products item changes
                    return 4;
                }
            }
        }
        /* Logical error if bidder is participated in two sessions as 3 - 4 and 4 - 5
        *
        *  as second notification for start session bid [4-5] not send !
        *
        *  Can solved by adjust sessions of bid 1-2 , 3-4 , 5-6  and so on
        *
        * */
        return 0;
    }
    @Override
    public int evaluateResults(int currenthour){
        // 3- Send invoice to bidder if he win or not
        SystemUser winner;
        Date current = new Date();
        Date sessiondate = null;
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        Sessions x = new Sessions();
        x.initializeSessions();
        for (Sessions s: x.getSessionList()) {   //mst5dmtsh sessionarraylist 3shan msh 7att feha kolo
            try {
                sessiondate = format.parse(s.getSession_date());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (isequal(current,sessiondate) && s.getEnd_time() == currenthour){
                winner = s.GetWinnerUser(currenthour);
                if (winner != null){
                    if (user.getId() == winner.getId())
                        return 5;
                    else
                        return 6;
                }
            }
        }
        return 0;
    }
    @Override
    public SystemUser getCurrentUser() {
        return this.user;
    }
    //</editor-fold>

    //<editor-fold desc="Dynamic Classes">
    private class item extends AnchorPane {
        private final ImageView ProductImage;
        private final HBox ProductRatePane;
        private final Label ProductPrice;
        public final JFXButton details;
        public final JFXButton participate;
        private FontAwesomeIconView Star;
        private final HBox hBox; //participate btn pane

        // ! Important Components
        private final Label label0;
        private final Label label;
        private final FontAwesomeIconView fontAwesomeIconView;
        private final Label label1;
        private final FontAwesomeIconView fontAwesomeIconView0;
        private final DropShadow dropShadow;

        //important Components Need for Searching process
        public final Label ProductName;
        public int itemID;
        public int Startsession;
        public String date;
        public String category;


        //for active
        protected final AnchorPane activepane;
        protected final AnchorPane nonactive;
        protected final StackPane circle;
        protected final MaterialDesignIconView materialDesignIconView;
        protected final Label activelabel;
        protected final MaterialDesignIconView materialDesignIconView0;

        public item(String id ,String name,String category, String imagepath , String price, int rate, int startsession,String date)
        {
            label = new Label();
            ProductName = new Label();
            ProductRatePane = new HBox();
            ProductImage = new ImageView();
            label0 = new Label();
            ProductPrice = new Label();
            details = new JFXButton();
            fontAwesomeIconView = new FontAwesomeIconView();
            hBox = new HBox();
            label1 = new Label();
            fontAwesomeIconView0 = new FontAwesomeIconView();
            participate = new JFXButton();
            dropShadow = new DropShadow();

            //for active
            activepane = new AnchorPane();
            circle = new StackPane();
            materialDesignIconView = new MaterialDesignIconView();
            materialDesignIconView0 = new MaterialDesignIconView();
            activelabel = new Label();
            nonactive = new AnchorPane();

            activepane.setPrefHeight(337.0);
            activepane.setPrefWidth(253.0);
            activepane.setStyle("-fx-background-color: black; -fx-opacity: 0.7; -fx-border-color: #41b8e6");
            circle.setLayoutX(40.0);
            circle.setLayoutY(45.0);
            circle.setPrefHeight(162.0);
            circle.setPrefWidth(167.0);
            circle.setStyle("-fx-border-color: rgba(65, 184, 230, 1); -fx-border-radius: 10em; -fx-border-width: 3;");
            materialDesignIconView.setIcon(MaterialDesignIcon.CHECK);
            materialDesignIconView.setSize("100");
            materialDesignIconView.setFill(Paint.valueOf("#41b8e6"));
            circle.getChildren().add(materialDesignIconView);


            activelabel.setAlignment(javafx.geometry.Pos.CENTER);
            activelabel.setContentDisplay(javafx.scene.control.ContentDisplay.RIGHT);
            activelabel.setLayoutX(40.0);
            activelabel.setLayoutY(250.0);
            activelabel.setPrefHeight(17.0);
            activelabel.setPrefWidth(169.0);
            activelabel.setText("Currently in Bid");
            activelabel.setTextFill(javafx.scene.paint.Color.valueOf("#41b8e6"));
            activelabel.setFont(new Font("Comic Sans MS", 19.0));
            materialDesignIconView0.setIcon(MaterialDesignIcon.RUN);
            materialDesignIconView0.setSize("20");
            materialDesignIconView0.setFill(Paint.valueOf("#41b8e6"));
            activelabel.setGraphic(materialDesignIconView0);
            //ADD
            activepane.getChildren().add(circle);
            activepane.getChildren().add(activelabel);





            //need for search
            this.itemID = Integer.parseInt(id);   //set id for item
            this.Startsession = startsession;
            this.date = date;
            this.category = category;

            fontAwesomeIconView.setIcon(FontAwesomeIcon.INFO);
            fontAwesomeIconView.setSize("20.0");
            fontAwesomeIconView.setFill(Paint.valueOf("#c6bebe"));

            fontAwesomeIconView0.setIcon(FontAwesomeIcon.CHECK);
            fontAwesomeIconView0.setSize("18.0");
            fontAwesomeIconView0.setFill(Paint.valueOf("#FFF"));

            setPrefHeight(337.0);
            setPrefWidth(253.0);
            setStyle("-fx-background-color: white;");

            nonactive.setPrefHeight(337.0);
            nonactive.setPrefWidth(253.0);

            AnchorPane.setLeftAnchor(label, 0.0);
            AnchorPane.setRightAnchor(label, 0.0);
            AnchorPane.setTopAnchor(label, 0.0);
            label.setContentDisplay(javafx.scene.control.ContentDisplay.GRAPHIC_ONLY);
            label.setLayoutX(6.0);
            label.setLayoutY(6.0);
            label.setPrefHeight(5.0);
            label.setPrefWidth(253.0);
            label.setStyle("-fx-background-color: #A4D662;");
            label.setFont(new Font(0.1));


            ProductName.setLayoutX(27);
            ProductName.setLayoutY(27.0);
            ProductName.setAlignment(Pos.CENTER);
            ProductName.setTextFill(javafx.scene.paint.Color.valueOf("#1e3d63"));
            ProductName.setFont(new Font("SansSerif Bold", 20.0));
            AnchorPane.setLeftAnchor(ProductName,1.0);
            AnchorPane.setRightAnchor(ProductName,1.0);

            ProductRatePane.setAlignment(javafx.geometry.Pos.CENTER);
            ProductRatePane.setLayoutX(59.0);
            ProductRatePane.setLayoutY(50.0);
            ProductRatePane.setPrefHeight(20.0);
            ProductRatePane.setPrefWidth(136.0);
            ProductRatePane.setSpacing(5.0);

            ProductImage.setFitHeight(150.0);
            ProductImage.setFitWidth(194.0);
            ProductImage.setLayoutX(30.0);
            ProductImage.setLayoutY(81.0);
            ProductImage.setPickOnBounds(true);
            ProductImage.setPreserveRatio(true);
            //   ProductImage.setImage(new Image(getClass().getResource("../Resources/Images/shop/7.jpeg").toExternalForm()));

            label0.setLayoutX(35.0);
            label0.setLayoutY(242.0);
            label0.setText("$ ");
            label0.setTextFill(javafx.scene.paint.Color.valueOf("#82b520"));
            label0.setFont(new Font("System Bold", 17.0));

            ProductPrice.setLayoutX(50.0);
            ProductPrice.setLayoutY(242.0);
            // ProductPrice.setText("35,299");
            ProductPrice.setTextFill(javafx.scene.paint.Color.valueOf("#82b520"));
            ProductPrice.setFont(new Font("System Bold", 17.0));

            details.setLayoutX(147.0);
            details.setLayoutY(240.0);

            details.setGraphic(fontAwesomeIconView);
            details.setFont(new Font("System Bold", 14.0));

            AnchorPane.setBottomAnchor(hBox, 0.0);
            AnchorPane.setLeftAnchor(hBox, 0.0);
            AnchorPane.setRightAnchor(hBox, 0.0);
            hBox.setLayoutY(274.0);
            hBox.setPrefHeight(44.0);
            hBox.setPrefWidth(254.0);
            hBox.setStyle("-fx-background-color: #21649B;");

            label1.setAlignment(javafx.geometry.Pos.CENTER);
            label1.setContentDisplay(javafx.scene.control.ContentDisplay.GRAPHIC_ONLY);
            label1.setPrefHeight(44.0);
            label1.setPrefWidth(51.0);
            label1.setStyle("-fx-background-color: #145595;");

            label1.setGraphic(fontAwesomeIconView0);

            participate.setStyle("-fx-background-color: #21649B;");
            participate.setFont(new Font("System Bold", 17.0));
            participate.setTextFill(Paint.valueOf("#FFF"));
            participate.setAlignment(Pos.CENTER);
            HBox.setMargin(participate,new Insets(2.0,0.0,0.0,0.0));
            participate.setPrefWidth(199);
            participate.setPrefHeight(41);

            dropShadow.setBlurType(javafx.scene.effect.BlurType.GAUSSIAN);
            dropShadow.setHeight(15.78);
            dropShadow.setRadius(7.952500000000001);
            dropShadow.setWidth(18.03);
            setEffect(dropShadow);
//////////////////////////////////////////////////////////////////////////

            //item Initialization
            this.ProductName.setText(name);
            this.ProductImage.setImage(new Image(getClass().getResource(imagepath).toExternalForm()));
            this.ProductPrice.setText(price);
            participate.setText("Participate");
            details.setText("Details");
            details.setTextFill(Paint.valueOf("#a89e9e"));
            participate.setId(id);  //init bottom with id same as item name
            details.setId(id);   //same
            init_Rate(rate);
            activepane.setVisible(false);

///////////////////////////////////////////////////////////////////////////
            nonactive.getChildren().add(label);
            nonactive.getChildren().add(ProductName);
            nonactive.getChildren().add(ProductRatePane);
            nonactive.getChildren().add(ProductImage);
            nonactive.getChildren().add(label0);
            nonactive.getChildren().add(ProductPrice);
            nonactive.getChildren().add(details);
            hBox.getChildren().add(label1);
            hBox.getChildren().add(participate);
            nonactive.getChildren().add(hBox);
            this.getChildren().add(nonactive);
            this.getChildren().add(activepane);
        }
        public void init_Rate(int rate){
            for (int i=0; i<rate; i++)
            {
                Star = new FontAwesomeIconView();
                Star.setIcon(FontAwesomeIcon.STAR);
                Star.setSize("12.0");
                Star.setFill(Paint.valueOf("#f69a1e"));
                ProductRatePane.getChildren().add(Star);
            }
        }
        public void participate_item(){
            label1.setStyle("-fx-background-color: rgba(44, 204, 135, 1);");
            hBox.setStyle("-fx-background-color: rgba(44, 204, 135, 1);");
            participate.setStyle("-fx-background-color: rgba(44, 204, 135, 1);");
            hBox.setDisable(true);
        }
        public void setItemID(int id){ this.itemID = id;}
        public int getItemID() {return this.itemID;}

        public void setactive(){
            activepane.setVisible(true);
            nonactive.setStyle("-fx-opacity: 0.5");
        }
        public void setinactive(){
            activepane.setVisible(false);
            nonactive.setStyle("-fx-opacity: 1.0");
        }
    }
    private class UnderRank extends HBox {
        protected final Label ranknumber;
        protected final VBox vBox;
        protected final HBox hBox;
        protected final Label label0;
        protected final HBox hBox0;
        protected final Label label2;
        protected final ImageView imageView;
        protected final Label label4;
        // important
        protected final Label biddername;
        protected final Label price;
        public UnderRank(int rankno , String name, double Price) {
            biddername = new Label(name);
            price = new Label(Price+"");
            ranknumber = new Label(rankno+"");

            vBox = new VBox();
            hBox = new HBox();
            label0 = new Label();
            hBox0 = new HBox();
            label2 = new Label();
            imageView = new ImageView();
            label4 = new Label();

            setPrefHeight(65.0);
            setPrefWidth(286.0);
            setStyle("-fx-border-color: silver;");

            ranknumber.setAlignment(javafx.geometry.Pos.CENTER);
            ranknumber.setPrefHeight(79.0);
            ranknumber.setPrefWidth(47.0);
            ranknumber.setStyle("-fx-background-color: #2178bf;");
            ranknumber.setTextFill(javafx.scene.paint.Color.WHITE);
            ranknumber.setFont(new Font(30.0));

            vBox.setPrefHeight(63.0);
            vBox.setPrefWidth(244.0);

            hBox.setPrefHeight(100.0);
            hBox.setPrefWidth(200.0);
            hBox.setSpacing(20.0);

            label0.setText("Name :");
            biddername.setFont(new Font("System Bold", 13.0));

            hBox0.setPrefHeight(100.0);
            hBox0.setPrefWidth(200.0);
            hBox0.setSpacing(20.0);

            label2.setText("Submitted Price :");
            price.setTextFill(javafx.scene.paint.Color.valueOf("#04ab25"));
            price.setFont(new Font("System Bold", 12.0));

            imageView.setFitHeight(11.0);
            imageView.setFitWidth(17.0);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(true);
            imageView.setImage(new Image(getClass().getResource("../Resources/Images/coins.png").toExternalForm()));
            price.setGraphic(imageView);

            label4.setText("$");
            label4.setTextFill(javafx.scene.paint.Color.valueOf("#5dc63c"));
            vBox.setPadding(new Insets(5.0, 0.0, 0.0, 10.0));

            getChildren().add(ranknumber);
            hBox.getChildren().add(label0);
            hBox.getChildren().add(biddername);
            vBox.getChildren().add(hBox);
            hBox0.getChildren().add(label2);
            hBox0.getChildren().add(price);
            hBox0.getChildren().add(label4);
            vBox.getChildren().add(hBox0);
            getChildren().add(vBox);
        }
        public void setasuser(){
            this.setStyle("-fx-border-color:  #2178bf; -fx-border-width: 3 ;-fx-background-color: #EEEEEE");
        }
        public void setprice(double newprice){
            price.setText(newprice+"");
        }
    }
    private class topRank extends HBox {
        protected final Label label;
        protected final VBox vBox;
        protected final HBox hBox;
        protected final ImageView imageView;
        protected final AnchorPane anchorPane;
        protected final Label label0;
        protected final Label label1;
        protected final Label label2;
        protected final Label label3;
        protected final Label label4;
        protected final Label label5;
        protected final HBox hBox0;
        protected final Label label6;
        protected final Label label7;
        protected final ImageView imageView0;
        protected final Label label8;
        public int rank;
        public HBox parent;
        public boolean isOnline = false;
        public boolean isuser = false;

        public topRank(int rankno , String bidderIMG,String name,String Phone,int state,double price) {
            rank = rankno;
            label = new Label();
            vBox = new VBox();
            hBox = new HBox();
            imageView = new ImageView();
            anchorPane = new AnchorPane();
            label0 = new Label();
            label1 = new Label();
            label2 = new Label();
            label3 = new Label();
            label4 = new Label();
            label5 = new Label();
            hBox0 = new HBox();
            label6 = new Label();
            label7 = new Label();
            imageView0 = new ImageView();
            label8 = new Label();

            label.setAlignment(javafx.geometry.Pos.CENTER);
            label.setPrefHeight(114.0);
            label.setPrefWidth(28.0);
            label.setStyle("-fx-background-color: #2178bf;");
            label.setText(rankno+"");
            label.setTextFill(javafx.scene.paint.Color.WHITE);
            label.setFont(new Font(41.0));

            vBox.setLayoutX(75.0);
            vBox.setLayoutY(272.0);
            vBox.setPrefHeight(110.0);
            vBox.setPrefWidth(270.0);

            hBox.setPrefHeight(82.0);
            hBox.setPrefWidth(200.0);

            imageView.setFitHeight(81.0);
            imageView.setFitWidth(99.0);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(true);
            imageView.setImage(new Image(getClass().getResource(bidderIMG).toExternalForm()));

            anchorPane.setPrefHeight(200.0);
            anchorPane.setPrefWidth(200.0);

            label0.setLayoutX(14.0);
            label0.setLayoutY(6.0);
            label0.setText("Name :");

            label1.setLayoutX(14.0);
            label1.setLayoutY(33.0);
            label1.setText("Phone :");

            label2.setLayoutX(14.0);
            label2.setLayoutY(60.0);
            label2.setText("State :");

            label3.setLayoutX(61.0);
            label3.setLayoutY(6.0);
            label3.setText(name);
            label3.setFont(new Font("System Bold", 13.0));

            label4.setLayoutX(61.0);
            label4.setLayoutY(33.0);
            label4.setText(Phone);
            label4.setFont(new Font("System Bold", 13.0));

            label5.setLayoutX(61.0);
            label5.setLayoutY(59.0);
            setstate(state);  //////
            label5.setTextFill(javafx.scene.paint.Color.valueOf("#f21111"));
            label5.setFont(new Font(13.0));

            hBox0.setPrefHeight(31.0);
            hBox0.setPrefWidth(268.0);

            label6.setPrefHeight(27.0);
            label6.setPrefWidth(119.0);
            label6.setText(" Currently Submit   :");
            label6.setFont(new Font(13.0));

            label7.setText(price+"");
            label7.setTextFill(javafx.scene.paint.Color.valueOf("#4f8d19"));

            imageView0.setFitHeight(22.0);
            imageView0.setFitWidth(17.0);
            imageView0.setPickOnBounds(true);
            imageView0.setPreserveRatio(true);
            imageView0.setImage(new Image(getClass().getResource("../Resources/Images/coins.png").toExternalForm()));
            label7.setGraphic(imageView0);
            label7.setFont(new Font(18.0));
            label7.setPadding(new Insets(0.0, 0.0, 0.0, 5.0));

            label8.setPrefHeight(28.0);
            label8.setPrefWidth(28.0);
            label8.setText(" $");
            label8.setTextFill(javafx.scene.paint.Color.valueOf("#28943c"));
            label8.setFont(new Font(16.0));
            vBox.setPadding(new Insets(0.0, 5.0, 0.0, 1.0));


            hBox.getChildren().add(imageView);
            anchorPane.getChildren().add(label0);
            anchorPane.getChildren().add(label1);
            anchorPane.getChildren().add(label2);
            anchorPane.getChildren().add(label3);
            anchorPane.getChildren().add(label4);
            anchorPane.getChildren().add(label5);
            hBox.getChildren().add(anchorPane);
            vBox.getChildren().add(hBox);
            hBox0.getChildren().add(label6);
            hBox0.getChildren().add(label7);
            hBox0.getChildren().add(label8);
            vBox.getChildren().add(hBox0);

            this.getChildren().add(label);  //label
            this.getChildren().add(vBox);   //VBox

            //check rank
            if (rankno == 1) {
                RANK1.getChildren().setAll(this);
                parent = RANK1;
            }else if (rankno == 2) {
                RANK2.getChildren().setAll(this);
                parent = RANK2;
            }else if (rankno == 3) {
                RANK3.getChildren().setAll(this);
                parent = RANK3;
            }
        }
        public void setstate(int state){
            if (state == 0){
                //offline
                label5.setStyle("-fx-text-fill: red");
                label5.setText("Offline");
                isOnline = false;
            }else {
                label5.setStyle("-fx-text-fill: limegreen");
                label5.setText("Online");
                isOnline = true;
            }
        }
        public void setprice(double newprice){
            label7.setText(newprice+"");
        }
        public void setasuser(){
            isuser = true;
            this.parent.setStyle("-fx-border-color: #2178bf; -fx-border-width: 3; -fx-background-color: #EEEEEE");
        }
    }
    public  class NotificationMessage extends VBox {

        protected final Label message;
        protected final FontAwesomeIconView fontAwesomeIconView;
        public final JFXButton seen;
        protected final FontAwesomeIconView fontAwesomeIconView0;
        protected final Separator separator;

        public NotificationMessage(String msg) {

            message = new Label();
            fontAwesomeIconView = new FontAwesomeIconView();
            seen = new JFXButton();
            fontAwesomeIconView0 = new FontAwesomeIconView();  //check on seen
            separator = new Separator();

            setMaxHeight(108.0);
            setPrefHeight(95.0);
            setPrefWidth(285.0);

            VBox.setVgrow(message, javafx.scene.layout.Priority.ALWAYS);
            message.setAlignment(javafx.geometry.Pos.TOP_LEFT);
            message.setPrefWidth(272.0);
           // message.setText("Bid1 is Starting Now , You Can Go Now and Submit New Price in Order To Win !");
            message.setText(msg);
            message.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            message.setWrapText(true);

            fontAwesomeIconView.setTranslateX(2.0);
            fontAwesomeIconView.setIcon(FontAwesomeIcon.WARNING);
            fontAwesomeIconView.setSize("25");
            fontAwesomeIconView.setFill(Paint.valueOf("#ebd547"));
            message.setGraphic(fontAwesomeIconView);
            message.setFont(new Font("Franklin Gothic Medium", 13.0));

            seen.setStyle("-fx-background-color: rgba(65, 184, 230, 1);");
            seen.setTranslateX(190.0);
            seen.setText("Seen");
            seen.setTextFill(Paint.valueOf("#FFF"));
            seen.setPrefSize(74,25);

            seen.setGraphic(fontAwesomeIconView0);
            fontAwesomeIconView0.setIcon(FontAwesomeIcon.CHECK);
            fontAwesomeIconView0.setSize("12.0");
            fontAwesomeIconView0.setFill(Paint.valueOf("#FFF"));
            VBox.setMargin(seen, new Insets(10.0, 0.0, 0.0, 0.0));

            separator.setPrefWidth(200.0);
            VBox.setMargin(separator, new Insets(5.0, 0.0, 0.0, 0.0));

            getChildren().add(message);
            getChildren().add(seen);
            getChildren().add(separator);

        }
    }
    public  class recievedmsgpane extends HBox {
        protected final ImageView img;
        protected final FontAwesomeIconView fontAwesomeIconView;
        protected final Label msg;
        public int senderID;
        public recievedmsgpane(int senderid ,String senderimg , String sendermsg) {
            senderID = senderid;
            img = new ImageView();
            fontAwesomeIconView = new FontAwesomeIconView();
            msg = new Label();

            setPrefHeight(60.0);
            setPrefWidth(253.0);
            setSpacing(8.0);

            img.setFitHeight(59.0);
            img.setFitWidth(40.0);
            img.setPickOnBounds(true);
            img.setPreserveRatio(true);
            img.setImage(new Image(getClass().getResource(senderimg).toExternalForm()));

            fontAwesomeIconView.setTranslateX(5.0);
            fontAwesomeIconView.setTranslateY(12.0);
            fontAwesomeIconView.setIcon(FontAwesomeIcon.CARET_LEFT);
            fontAwesomeIconView.setSize("20.0");
            fontAwesomeIconView.setFill(Paint.valueOf("#b5e7c0"));

            msg.setAlignment(javafx.geometry.Pos.TOP_LEFT);
            msg.setContentDisplay(javafx.scene.control.ContentDisplay.TEXT_ONLY);
            msg.setEllipsisString("");
            msg.setGraphicTextGap(5.0);
            msg.setLineSpacing(2.0);
            msg.setPrefHeight(50.0);
            msg.setPrefWidth(193.0);
            msg.setStyle("-fx-background-color: #b5e7c0;");
            msg.setText(sendermsg);
            msg.setTextAlignment(javafx.scene.text.TextAlignment.JUSTIFY);
            msg.setTextFill(javafx.scene.paint.Color.valueOf("#121713"));
            msg.setTranslateX(-4.0);
            msg.setWrapText(true);
            HBox.setMargin(msg, new Insets(10.0, 2.0, 0.0, 0.0));
            msg.setPadding(new Insets(2.0, 2.0, 2.0, 4.0));

            getChildren().add(img);
            getChildren().add(fontAwesomeIconView);
            getChildren().add(msg);

        }
    }
    public  class Sendmsgpane extends HBox {

        protected final Label msg;

        public Sendmsgpane(String sendmsg) {

            msg = new Label();

            setPrefHeight(60.0);
            setPrefWidth(253.0);
            setSpacing(8.0);

            msg.setAlignment(javafx.geometry.Pos.TOP_LEFT);
            msg.setContentDisplay(javafx.scene.control.ContentDisplay.TEXT_ONLY);
            msg.setEllipsisString("");
            msg.setGraphicTextGap(5.0);
            msg.setLineSpacing(2.0);
            msg.setPrefHeight(50.0);
            msg.setPrefWidth(193.0);
            msg.setStyle("-fx-background-color: #6aa5e2;");
            msg.setText(sendmsg);
            msg.setTextAlignment(javafx.scene.text.TextAlignment.JUSTIFY);
            msg.setTextFill(javafx.scene.paint.Color.WHITE);
            msg.setTranslateX(-4.0);
            msg.setWrapText(true);
            HBox.setMargin(msg, new Insets(10.0, 2.0, 0.0, 5.0));
            msg.setPadding(new Insets(2.0, 2.0, 2.0, 4.0));
            msg.setFont(new Font("Arial", 12.0));

            getChildren().add(msg);

        }
    }
    //</editor-fold>
}
