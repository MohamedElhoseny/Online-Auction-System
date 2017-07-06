package Controllers;

import Animation.Transition.*;
import Models.Admin;
import Models.Member;
import Models.SchedulerNotifier;
import Models.SystemUser;
import com.jfoenix.controls.*;
import de.jensd.fx.fontawesome.Icon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.IllegalFormatException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@FXMLController(value = "../Views/StartupView.fxml")
public class StartupController implements Initializable
{
    //Flow
    @FXMLViewFlowContext
    ViewFlowContext context;
    private Flow flow;

    //Default components
    @FXML
    private StackPane root;
    @FXML
    private AnchorPane parent;
    @FXML
    private FlowPane titlePane;
    @FXML
    private ImageView logo;
    @FXML
    private Label minititle;
    @FXML
    private Label sys;
    @FXML
    private AnchorPane registrationPane;
    @FXML
    private Pane registeration_form;
    @FXML
    private FontAwesomeIconView close;

    //Registeration Components
    @FXML
    private JFXButton signin;
    @FXML
    private JFXTextField Fname;
    @FXML
    private JFXTextField Lname;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField pass1;
    @FXML
    private JFXPasswordField pass2;
    @FXML
    private ComboBox<String> dd;
    @FXML
    private ComboBox<String> mm;
    @FXML
    private ComboBox<String> yy;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private JFXButton signup;
    @FXML
    private JFXComboBox<String> role;
    @FXML
    private FontAwesomeIconView emailmsg,passmsg,namemsg,phonemsg;
    @FXML
    private Pane reg_content;
    @FXML
    private JFXTextField phone;
    @FXML
    private FontAwesomeIconView roleicon , bdicon;



    //profile picture
    @FXML
    private ImageView profile_picture;
    @FXML
    private Label chosepic;
    @FXML
    private ListView<ImageView> pic_pane;

    //Log in Components
    @FXML private Pane SigninForm;   //navigating with reg_content by signin button
    @FXML private Button login;
    @FXML private TextField login_email;
    @FXML private PasswordField login_pass;
    @FXML private FontAwesomeIconView logEmailsign;
    @FXML private FontAwesomeIconView logPasssign;
    @FXML private Label linktoreg;


    //OUT-OF-GUI Components
    private ToggleGroup gender;
    private GUI_BAG gui_bag;
    private String selected_pic;
    private String defaultpic = "..\\Resources\\Images\\defaultFace.png";
    private boolean isaccept = false;
    private boolean _PassFlag = false;
    private boolean _EmailFlag = false;
    //Login Controls
    private boolean _isAdmin  = false;
    private boolean _isSeller = false;
    private boolean _isBidder = false;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gui_bag = GUI_BAG.get_Bag();
        _animateStartUp();

        //init dd , mm , yy [changed in phase 3]
        String[] days = new String[31];
        String[] months = new String[12];
        String[] years = new String[22];
        for (int i=0; i<31; i++)
            days[i] = String.valueOf(i+1);
        for (int i=0; i<12; i++)
            months[i] = String.valueOf(i+1);
        int y = 1995;
        for (int i=0; i<22; i++)
            years[i] = String.valueOf(y++);

        dd.getItems().setAll(days);
        mm.getItems().setAll(months);
        yy.getItems().setAll(years);
        //init gender
        gender = new ToggleGroup();
        male.setToggleGroup(gender);
        male.setUserData("male");
        female.setToggleGroup(gender);
        female.setUserData("female");
        //init role
        role.getItems().add("I am Seller");
        role.getItems().add("I am Bidder");

        //init profile pic
        chosepic.setOnMouseClicked(event -> {
            pic_pane.setVisible(true);
        });

        role.setOnAction(event -> SetAcceptMsg(roleicon));
        pic_pane.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<ImageView>() {
            @Override
            public void onChanged(Change<? extends ImageView> c) {
                profile_picture.setImage(pic_pane.getSelectionModel().getSelectedItem().getImage()); //get selected image
                selected_pic = pic_pane.getSelectionModel().getSelectedItem().getId();
                System.out.println(selected_pic);
                pic_pane.setVisible(false);
            }
        });

        yy.setOnAction(event -> {
            if (dd.getSelectionModel().isEmpty() && dd.getSelectionModel().isEmpty())
                SetErrorMsg(bdicon);
            else
                SetAcceptMsg(bdicon);
        });

        //handle birthday event

        //handling required Inputs

        //text change property
        email.textProperty().addListener(ActionListener->{
            if (!email.getText().isEmpty())
               validate_Email();
        });
        pass2.textProperty().addListener(ActionListener->{
            if (!pass1.getText().isEmpty())
                validate_pass();
        });
        pass1.textProperty().addListener(ActionListener->{
            if (!pass2.getText().isEmpty())
                validate_pass();
        });


        Fname.textProperty().addListener(ActionListener->{
            if (!Fname.getText().isEmpty() && !Lname.getText().isEmpty()) {
                 SetAcceptMsg(namemsg);
                 isaccept = true;
            }else {
                SetErrorMsg(namemsg);
                 isaccept = false;
            }
        });
        Lname.textProperty().addListener(ActionListener->{
            if (!Lname.getText().isEmpty() && !Fname.getText().isEmpty()) {
                SetAcceptMsg(namemsg);
                isaccept = true;
            }else {
                SetErrorMsg(namemsg);
                isaccept = false;
            }
        });
        phone.textProperty().addListener(ActionListener->{
            if (!phone.getText().isEmpty()){
                try {
                    Integer trace = Integer.parseInt(phone.getText());
                    SetAcceptMsg(phonemsg);
                    isaccept = true;
                }catch (NumberFormatException ex) {
                    SetErrorMsg(phonemsg);
                    isaccept = false;
                }
            }
        });

        //focus property
        email.focusedProperty().addListener((o,oldval,newval)->{
            if (!newval)
                email.validate();
        });
        pass1.focusedProperty().addListener((o,oldval,newval)->{
            if (!newval)
                pass1.validate();
        });
        pass2.focusedProperty().addListener((o,oldval,newval)->{
            if (!newval)
                pass2.validate();
        });
        Fname.focusedProperty().addListener((o,oldval,newval)->{
            if (!newval)
                Fname.validate();
        });
        Lname.focusedProperty().addListener((o,oldval,newval)->{
            if (!newval)
                Lname.validate();
        });
        phone.focusedProperty().addListener((o,oldval,newval)->{
            if (!newval)
                phone.validate();
        });

        chosepic.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
            selected_pic = file.getName();
            System.out.println("Selected image = "+selected_pic);
            //file.getName()  => get only the name of item
            //file.getAbsolutePath()    => get path of item on PC
            try
            {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                profile_picture.setImage(image);
            } catch (Exception ex) {
                System.out.println("error while reading product image");
            }
        });




        //this is button in registration form that navigate to login form
        signin.setOnAction(event -> {
            animatesignIN();
        });
        linktoreg.setOnMouseClicked(event -> {
            animatessignUP();
        });
    }


    //Behavior Work
    @FXML
    private void Login(ActionEvent e)
    {
        SystemUser user = new SystemUser();   //l2ne lesa m3rfsh hwa 2eh
        int authorization = -1;

        String E_email = login_email.getText();
        String E_password = login_pass.getText();
        authorization = user.Login(E_email,E_password);

            switch (authorization) {
                case -1:
                    SetErrorMsg(logEmailsign);
                    break;
                case -2:
                    SetErrorMsg(logPasssign);
                    SetAcceptMsg(logEmailsign);
                    break;
                case 0:
                    _isAdmin = true;
                    clearPane(SigninForm);
                    RefreshView(new Admin(user));
                    break;
                case 1:
                    _isSeller = true;
                    clearPane(SigninForm);
                    RefreshView(new Member(user,authorization));
                    break;
                case 2:
                    _isBidder = true;
                    clearPane(SigninForm);
                    RefreshView(new Member(user,authorization));
                    break;
                default:
                    System.out.println("Error 101 : Error while Log in Processing !");
                    break;
            }

    }

    @FXML
    private void Register(ActionEvent e)
    {
        Member user = new Member(); //l2no hna l2ma seller aw bidder
        String Role = null;           int auth = -1;

        try {
            Role = role.getSelectionModel().getSelectedItem();
            System.out.println(Role);
            if (Role.equals("I am Seller")) auth=1; else  auth=2;
        }catch (Exception ex){
            SetErrorMsg(roleicon);
            isaccept = false;
        }

        String day =   dd.getSelectionModel().getSelectedItem();
        String month = mm.getSelectionModel().getSelectedItem();
        String year =  yy.getSelectionModel().getSelectedItem();
        String bd = "'"+day+"/"+month+"/"+year+"'";
        System.out.println(day);
        System.out.println(bd);
        String Gender = gender.getSelectedToggle().getUserData().toString();
        int g;    if (Gender.equals("male")) g = 0;  else  g = 1;


        //Check Profile Picture
        if (selected_pic == null)
            selected_pic = defaultpic;

        if (Role != null) {
            SetAcceptMsg(roleicon);
            isaccept = true;
        }
        if (day == null|| month== null || year== null) {
            SetErrorMsg(bdicon);
            isaccept = false;
        }else
            SetAcceptMsg(bdicon);

        //with validation
        if (phone.getText().isEmpty())
            SetErrorMsg(phonemsg);



        if (Fname.getText().isEmpty() || Lname.getText().isEmpty())
            SetErrorMsg(namemsg);
        if (email.getText().isEmpty())
            SetErrorMsg(emailmsg);
        if (pass1.getText().isEmpty() || pass2.getText().isEmpty())
            SetErrorMsg(passmsg);


        if (email.getText().isEmpty())
            SetErrorMsg(emailmsg);
        else{
            if (isaccept && user.canRegister(email.getText())) {   //if all data are write and this email can used
                try {
                    user.Register(Fname.getText(), Lname.getText(), email.getText(), pass1.getText(),
                            phone.getText(), bd, g, "../Resources/Images/ProfilePics/"+selected_pic, auth);
                    clearPane(reg_content);
                    animatesignIN();
                } catch (Exception ex) {
                    System.out.println("There is an Error in Calling DB at Register Function");
                }
            }else if (isaccept)
                SetErrorMsg(emailmsg);
        }

    }

    private void clearPane(Pane pane) {
        if (pane == reg_content) {
            role.getSelectionModel().clearSelection();
            profile_picture.setImage(new Image(getClass().getResource(defaultpic).toExternalForm()));
            Fname.clear();
            Lname.clear();
            email.clear();
            pass1.clear();
            pass2.clear();
            phone.clear();
            dd.getSelectionModel().clearSelection();
            mm.getSelectionModel().clearSelection();
            yy.getSelectionModel().clearSelection();
            bdicon.setVisible(false);
            namemsg.setVisible(false);
            emailmsg.setVisible(false);
            phonemsg.setVisible(false);
            passmsg.setVisible(false);
            roleicon.setVisible(false);
        }else if (pane == SigninForm){
            login_email.clear();
            login_pass.clear();
        }

    }


    //GUI WORK
    private void RefreshView(SystemUser user) {
        if (gui_bag.Contains("user"))
            gui_bag.ModifyRegistered_Component("user",user);
        else
            gui_bag.Register_Component("user",user);

        if (_isAdmin)
            flow = new Flow(AdminController.class);
        else if (_isSeller)
            flow = new Flow(SellerController.class);
        else if (_isBidder)
            flow = new Flow(BidderController.class);


        //Must be one of SystemUser or Fire Exception
        try {
            StackPane nextview = flow.createHandler().start();
            Stage window = (Stage) gui_bag.get_Registered_Component("primaryStage");
            Scene scene = new Scene(nextview);
            window.setX(45);  window.setY(15);
            scene.getStylesheets().add(getClass().getResource("../Resources/CSS/style_Component.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("../Resources/CSS/style.css").toExternalForm());
            window.setScene(scene);
            //SET IP OF LOGIN USER
            user.setIP(user.getId() , InetAddress.getLocalHost().getHostAddress());
            user.setState(user.getId(),1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void _animateStartUp() {

        //animate logo
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(2000), e -> {
            new FlipOutXTransition(titlePane).play();
        }));
        tl.play();

        // after logo animate registration Pane Must Visible
        tl = new Timeline(new KeyFrame(Duration.millis(2800), e -> {
            titlePane.setVisible(false);
            root.setEffect(new InnerShadow());
            new FlipInXTransition(registeration_form).play();
        }));
        tl.play();

        tl = new Timeline(new KeyFrame(Duration.millis(3000),event -> {
            registrationPane.setVisible(true);
            registeration_form.setVisible(true);
        }));

        tl.play();

        close.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }
    private void animatessignUP() {
        new FadeOutRightBigTransition(SigninForm).play();
        new FadeInRightBigTransition(reg_content).play();
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(500) , event -> {
            reg_content.setVisible(true);
            SigninForm.setVisible(false);
        }));
        t1.play();
    }
    private void animatesignIN() {
        new FadeOutRightBigTransition(reg_content).play();
        new FadeInRightBigTransition(SigninForm).play();
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(500) , event -> {
            reg_content.setVisible(false);
            SigninForm.setVisible(true);
        }));
        t1.play();
    }
    private void validate_pass() {
        if (pass1.getText().equals(pass2.getText())){
             SetAcceptMsg(passmsg);
            _PassFlag = true;
        }else {
             SetErrorMsg(passmsg);
            _PassFlag = false;
        }
    }
    private void validate_Email() {
        // JAVA EveryWhere
        Pattern reg_email;
        Matcher matcher;

        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        reg_email = Pattern.compile(EMAIL_PATTERN);
        matcher = reg_email.matcher(email.getText());

        if (matcher.matches()) {
            SetAcceptMsg(emailmsg);
            _EmailFlag = true;
        }else {
            SetErrorMsg(emailmsg);
            _EmailFlag = false;
        }
    }
    private void SetAcceptMsg(FontAwesomeIconView e) {
        e.setVisible(true);
        e.setStyle("-fx-fill: #44B449; -fx-font-size: 1.5em;");
        e.setGlyphName("CHECK_CIRCLE");  //DEH FUNCTION BE CHANGE EL ICON BE EL NAME
    }
    private void SetErrorMsg(FontAwesomeIconView e){
        e.setVisible(true);
        e.setStyle("-fx-fill: red; -fx-font-size: 1.5em;");
        e.setGlyphName("TIMES_CIRCLE");
    }
}
