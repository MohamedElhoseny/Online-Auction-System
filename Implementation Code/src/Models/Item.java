package Models;
import java.util.ArrayList;


public class Item extends MappedClass
{
    //Attributes
    private int ID;
    private String Item_name;
    private String Details;
    private String picture;
    private double Price;
    private int  Cat_ID;
    private int session_ID;
    private int seller_ID;
    private int Admin_ID;
    private int Accepted;
    private String state;
    public  ArrayList<Item> ItemList;
    private ArrayList<Object[]> list;
    private String UAccepted = "";
    private Sessions session ;
    private Category category;
    private SystemUser seller;
    private String CatName,SellerName;
    private int StartTime;
    private int served;

    //Constructor
    public Item(int id, String name,String details, String pic, double price,int category,
                int session,int seller,int admin,int accept,int served)
    {
        this.ID = id;
        this.Item_name = name;
        this.Details = details;
        this.picture = pic;
        this.Price = price;
        this.Cat_ID=category;
        this.session_ID=session;
        this.seller_ID = seller;
        this.Admin_ID = admin;
        this.Accepted=accept;
        this.served = served;
    }
    public Item(){
    }
    public  void initializeItems(){
        try {
            getData();   //read Database Data
        }catch (Exception e){
            System.out.println("Error in DB");
        }

        Sessions x = new Sessions();
        Category y = new Category();
        SystemUser z = new SystemUser(); //change this class to initialize as session and categories
        x.initializeSessions();
        y.initializeCategories();
        ArrayList<SystemUser> UsersList = z.getSystemUsersList();
        ArrayList<Sessions> sessionList   = x.getSessionList();
        ArrayList<Category> CategoryList = y.getCategoryList();

        for(Item i :ItemList) {
            for (Sessions s : sessionList) {
                if (s.getId() == i.session_ID){
                    i.session = s;
                    i.StartTime = s.getStart_time();
                }
            }
            for(Category c: CategoryList)
            {
                if(c.getId()==i.Cat_ID){
                    i.category=c;
                    i.CatName = c.getCat_Name();
                }
            }
            for (SystemUser u: UsersList){
                if (u.getId() == i.seller_ID){
                    i.seller = u;
                    i.SellerName = u.getFname() + " " + u.getLname();
                }
            }
            initItemState(i);
        }

    }
    private void initItemState(Item i) {
        if (i.Accepted == 0)
            i.state = "Pending";
        else if (i.Accepted == 1)
            i.state = "Accepted";
        else if (i.Accepted == 2)
            i.state = "Rejected";
    }

    //Getters and Setters
    public void setAccepted(int accepted) {
        UAccepted = "Accepted = " + accepted;
        accepted = accepted;
    }
    public String getItem_name() {
        return this.Item_name;
    }
    public void  setItem_name(String n){this.Item_name = n;}
    public String getDetails() {
        return this.Details;
    }
    public String getpicture() {
        return this.picture;
    }
    public double getPrice() {return this.Price;}
    public int getCat_ID(){return this.Cat_ID;}
    public int getSession_ID(){return this.session_ID;}
    public int getSeller_ID(){return this.seller_ID;}
    public int getAdmin_ID(){return this.Admin_ID;}
    public String getState(){ return this.state;}
    public void setState(String s){ this.state = s;}
    public int getAccepted(){return this.Accepted;}
    public Sessions getsession() {return this.session;}
    public Category getcategory(){return this.category;}
    public int getStartTime() {
        return StartTime;
    }
    public void setStartTime(int startTime) {
        StartTime = startTime;
    }
    public String getCatName() {
        return CatName;
    }
    public String getSellerName() {
        return SellerName;
    }
    public void setCatName(String catName) {
        CatName = catName;
    }
    public void setSellerName(String sellerName) {
        this.SellerName = sellerName;
    }
    public SystemUser getSeller(){return this.seller;}
    public void setID(int id) {
        this.ID = id;
    }
    public int getID(){return this.ID;}
    public void setName(String name) {this.Item_name = name;}
    public void setDetails(String details) {
        this.Details = details;
    }
    public void setpicture(String pic) {this.picture = pic;}
    public void setPrice(double price) {
        this.Price = price;
    }
    public void setCat_ID(int category){this.Cat_ID=category;}
    public void setSession_ID(int session){this.session_ID=session;}
    public void setSeller_ID(int seller){this.seller_ID=seller;}
    public void setAdmin_ID(int admin){this.Admin_ID=admin;}
    public void setServed(int i){
        this.served = i;
    }
    public int getServed(){return this.served;}


    //Methods
    @Override
    protected String getAttributes()
    {
        return "ID,Item_name,Details,picture,Price,Cat_ID,session_ID,seller_ID,Admin_ID,Accepted,Served";
    }
    @Override
    protected String getValues()
    {
        return   ID + ",'" + Item_name + "','" + Details + "','"+picture+"',"+Price+","+Cat_ID+","+session_ID+","+seller_ID+","+Admin_ID+","+Accepted +","+served;
    }

    protected String getOptions() {
        return UAccepted;
    }

    @Override
    protected String getWhere() {
        return "";
    }


    @Override
    public int getId() {
        return this.ID;
    }

    @Override
    protected Object get() {
        return null;
    }

    public ArrayList<Item> getItemList() {return this.ItemList;}

    public void getData() throws NullPointerException {
        ItemList = new ArrayList<>();
        list = getAll();
        for(Object[] item : list) {
            ItemList.add(new Item((int) item[0], (String) item[1], (String) item[2], (String) item[3],
                    (double) item[4], (int) item[5],(int) item[6],(int) item[7],(int) item[8],(int) item[9] , (int) item[10] ));
        }
    }

    public void add() {super.add();}

    public void delete() {super.delete();}

    public void update() {super.update();}

    //by Elhosany
    public ArrayList<Object[]> getParticipatedBidders(int sessionID , int itemID){
        DBInterface db = DBInterface.getInstance();
        return db.select(
          "systemuser,session_participants",
                "ID,Fname,Lname,profilePic,phone,price,State",
                    "systemuser.ID = session_participants.bidder_ID and session_ID = "+sessionID+" and item_ID ="+itemID+" ORDER BY price DESC"
        );
    }
    public double getCurrentPrice(int session_ID,int itemID){
        DBInterface dbInterface = DBInterface.getInstance();
        ArrayList<Object[]> x = dbInterface.select(
          "session_participants",
                "max(price)",
                "session_ID = "+session_ID+" and item_ID = "+itemID
        );
        return (double) x.get(0)[0];
    }
    public void submitnewPrice(int session_ID,int item_ID,int bidder_ID,String price){
        DBInterface db = DBInterface.getInstance();
        db.update("session_participants","price",price," session_ID = "+session_ID+" and item_ID = "+
                item_ID +" and bidder_ID = "+bidder_ID);
    }
    public void setitemServed(int itemID){
        DBInterface.getInstance().update(
                "item","Served","1","ID = "+itemID
        );
    }
}