package Models;

        import Models.DBInterface;
        import Models.MappedClass;

        import java.util.ArrayList;
        import java.util.Date;

/**
 *  return in Login function
 *  (-2)  --> pass is wrong
 *  (-1)  --> Email is wrong
 *  (0)   --> this is admin
 *  (1)   --> this is seller
 *  (2)   --> this is bidder
 *
 *  Gender
 *  (1)  --> MALE
 *  (2)  --> FEMALE
 *
 *  Login
 *  (1)  --> LOGIN
 *  (2)  --> LOGOUT
 *
 */
public class SystemUser extends MappedClass {
    //Attributes

    protected int id,authorization,Gender,State;
    protected String Uauthorization = "",UGender = "",UFname = "",ULname = "",Uemail = "",Upassword = "",Uprofilepic = "",Uphone = "",UBirthdate = "";
    protected String Fname,Lname,email,pass,profilepic,phone,IP;
    protected String Birthdate;
    public ArrayList<SystemUser> systemUsersList = new ArrayList<SystemUser>();
    private ArrayList<Object[]> list;
    private ArrayList<String> Email;

    //Constructor

    public SystemUser(){
        getData();
    }


    public SystemUser(int id,String Fname,String Lname,String Email,String password,
                      String pic,int gender,String Birthdate,String phone,int authorization,String IP , int State)
    {
        this.id = id;
        this.Fname = Fname;
        this.Lname = Lname;
        this.email = Email;
        this.pass = password;
        this.profilepic = pic;
        this.Gender = gender;
        this.Birthdate = Birthdate;
        this.phone = phone;
        this.authorization = authorization;
        this.IP = IP;
        this.State = State;
    }


    public SystemUser(String Fname,String Lname,String Email,String password,String pic,
                      int gender,String Birthdate,String phone,int authorization)
    {
        this.Fname = Fname;
        this.Lname = Lname;
        this.email = Email;
        this.pass = password;
        this.profilepic = pic;
        this.Gender = gender;
        this.Birthdate = Birthdate;
        this.phone = phone;
        this.authorization = authorization;
    }



    //Methods
    public ArrayList<String> GetEmails(){
        Email = new ArrayList<String>();
        for (SystemUser user : systemUsersList) {
            Email.add(user.email);
        }
        return Email;
    }
    public void getData() throws NullPointerException {
        list = getAll();
        for(Object[] user : list) {
            systemUsersList.add(new SystemUser((int) user[0], (String) user[1], (String) user[2], (String) user[3],
                    (String) user[4], (String) user[5], (int) user[6], (String) user[7],
                       (String) user[8], (int) user[9], (String) user[10], (int)user[11]));
        }
    }
    public int Login(String EMAIL,String Password){
        try {
            for (SystemUser user : systemUsersList) {
                if (user.email.equalsIgnoreCase(EMAIL)) {
                    if (user.pass.equals(Password)) {
                        System.out.println("Email & Password are Correct \nLogin Successfully\n\n\n" +
                                "WELCOME " + user.Fname + " " + user.Lname);
                        id = user.id;
                        setFname(user.Fname);
                        setLname(user.Lname);
                        setEmail(user.email);
                        setPassword(user.pass);
                        setProfilepic(user.profilepic);
                        setGender(user.Gender);
                        setPhone(user.phone);
                        setAuthorization(user.authorization);
                        setBirthdate(user.Birthdate);
                        return user.authorization;
                    } else {
                        System.out.println("Sorry Password you have entered is wrong");
                        return -2;
                    }

                }
            }
            System.out.println("Sorry Email you have entered is wrong");
            return -1;
        }catch (NullPointerException e){
            return -4;
        }

    }
    public void Logout(SystemUser systemUser){
        systemUser.systemUsersList.clear();
        systemUser = null;
    }
    public ArrayList<String> getNotifications(int userID) {
        ArrayList<Object[]> AllUsersNotifications = new ArrayList<>();
        ArrayList<String> UserNotifications = new ArrayList<>();

        // get instance from DBInterface to work on database
        DBInterface DB = DBInterface.getInstance();

        // get all notifications
        AllUsersNotifications = DB.select("Notification" , "message" ,userID);

        for (Object[] obj : AllUsersNotifications) {
            UserNotifications.add(obj[0].toString());
        }

        return UserNotifications;
    }


    //Getters and Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthorization(int authorization) {
        Uauthorization = "authorization = " + authorization;
        this.authorization = authorization;
    }

    public void setGender(int gender) {
        UGender = "Gender = " + gender;
        Gender = gender;
    }

    public void setFname(String fname) {
        UFname = "Fname = '" + fname + "'";
        Fname = fname;
    }

    public void setLname(String lname) {
        ULname = "Lname = '" + lname + "'";
        Lname = lname;
    }

    public void setEmail(String email) {
        Uemail = "email = '" + email + "'";
        this.email = email;
    }

    public void setPassword(String password) {
        Upassword = "pass = '" + password + "'";
        this.pass = password;
    }

    public void setProfilepic(String profilepic) {
        Uprofilepic = "profilepic = '" + profilepic  + "'";
        this.profilepic = profilepic;
    }

    public void setPhone(String phone) {
        Uphone = "phone = '" + phone + "'";
        this.phone = phone;
    }

    public void setBirthdate(String birthdate) {
        UBirthdate = "Birthdate = " + birthdate;
        Birthdate = birthdate;
    }


    public int getGender() {
//        try {
//            return systemUsersList.get(id-1).Gender;
//        }catch (IndexOutOfBoundsException e){
//            System.out.println("ERROR IN DB");
//            return -1;
//        }
        return Gender;
    }

    public int getAuthorization() {
//        try {
//            return systemUsersList.get(id-1).authorization;
//        }catch (IndexOutOfBoundsException e){
//            System.out.println("ERROR IN DB");
//            return -1;
//        }
        return authorization;
    }

    public String getFname() {
//        try {
//            return systemUsersList.get(id-1).Fname;
//        }catch (IndexOutOfBoundsException e){
//            System.out.println("ERROR IN DB");
//            return "";
//        }
        return Fname;
    }

    public String getLname() {

//        try {
//            return systemUsersList.get(id-1).Lname;
//        }catch (IndexOutOfBoundsException e){
//            System.out.println("ERROR IN DB");
//            return "";
//        }
        return Lname;
    }

    public String getEmail() {

//        try {
//            return systemUsersList.get(id-1).email;
//        }catch (IndexOutOfBoundsException e){
//            System.out.println("ERROR IN DB");
//            return "";
//        }
        return email;
    }

    public String getPassword() {

//        try {
//            return systemUsersList.get(id-1).pass;
//        }catch (IndexOutOfBoundsException e){
//            System.out.println("ERROR IN DB");
//            return "";
//        }
        return pass;
    }

    public String getProfilePic() {

//        try {
//            return systemUsersList.get(id-1).profilepic;
//        }catch (IndexOutOfBoundsException e){
//            System.out.println("ERROR IN DB");
//            return "";
//        }
        return profilepic;
    }

    public String getPhone() {
//        try {
//            return systemUsersList.get(id-1).phone;
//        }catch (IndexOutOfBoundsException e){
//            System.out.println("ERROR IN DB");
//            return "";
//        }
        return phone;
    }

    public String getBirthdate() {
//        try {
//            return systemUsersList.get(id-1).Birthdate;
//        }catch (IndexOutOfBoundsException e){
//            System.out.println("ERROR IN DB");
//            return null;
//        }
        return Birthdate;
    }

    public ArrayList<SystemUser> getSystemUsersList() {
        return systemUsersList;
    }



    @Override
    protected String getAttributes() {
        return "Fname,Lname,email,pass,profilepic,Gender,Birthdate,phone,authorization";
    }

    @Override
    protected String getValues() {
        return "'" + Fname + "','" + Lname + "','" + email + "','" + pass + "','" + profilepic + "'," + Gender + "," + Birthdate + ",'" + phone + "'," + authorization ;
    }

    @Override
    protected String getOptions() {
        return UFname + "," + ULname + "," + Upassword;
    }

    @Override
    protected String getWhere() {
        return "";
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    protected Object get() {
        return null;
    }

    public void add() {

        super.add();
    }

    @Override
    public void delete() {
        super.delete();
    }

    @Override
    public void update() {
        super.update();
    }

    //elhosany
    public SystemUser getUserbyid(int id){
        for (SystemUser s: systemUsersList) {
            if (s.getId() == id){
                return s;
            }
        }
        return null;
    }
    public void setIP(int id , String ip){
        DBInterface db = DBInterface.getInstance();
        db.update("systemuser","IP = '"+ip+"'",id,"");
    }
    public void setState(int id, int state){
        DBInterface db = DBInterface.getInstance();
        db.update("systemuser","State = "+state ,id,"");
    }
    public void setIP(String ip){ this.IP = ip;}
    public String getIP(){return this.IP;}
    public int getState(){return this.State;}
}
