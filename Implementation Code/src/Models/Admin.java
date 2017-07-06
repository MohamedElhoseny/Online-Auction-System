package Models;

import Models.SystemUser;
import sun.rmi.runtime.Log;

import java.util.ArrayList;

/**
 * Return Values
 *  (-1) --> something Wrong
 *  (1)  --> done successfully
 */
public class Admin  extends SystemUser {
    //Attributes
    private ArrayList<SystemUser> users;
    private ArrayList<Item> items;
    private ArrayList<Category> categories;
    private ArrayList<Sessions> sessions;
    private Item item;
    private Category category;
    private Sessions session;
    private boolean check;

    //Constructor
    public Admin(){

    }
    public Admin(SystemUser LoginClient){
        this.systemUsersList = LoginClient.getSystemUsersList();
        this.setEmail(LoginClient.getEmail());
        this.setFname(LoginClient.getFname());
        this.setLname(LoginClient.getLname());
        this.setAuthorization(LoginClient.getAuthorization());
        this.setPassword(LoginClient.getPassword());
        this.setBirthdate(LoginClient.getBirthdate());
        this.setId(LoginClient.getId());
        this.setProfilepic(LoginClient.getProfilePic());
        this.setGender(LoginClient.getGender());
        this.setPhone(LoginClient.getPhone());
    }


    //Getters and Setters
//    public void setFname(String fname){this.Fname = fname;}
//    public String getFname(){return this.Fname;}
    public void initializeAdmin(){
        users = getSystemUsersList();
        item = new Item();
        item.initializeItems();
        items = item.getItemList();
        category = new Category();
        category.initializeCategories();
        categories = category.getCategoryList();
        session = new Sessions();
        session.initializeSessions();
        sessions = session.getSessionList();
    }
    public ArrayList<Category> getCategories() {

        ArrayList<Category> CategoryName = new ArrayList<Category>();
        for (Category c : categories) {
            for (SystemUser x : users) {
                if (c.getAdmin_ID() == x.getId()) {
                    CategoryName.add(new Category(c.getId(), c.getCat_Name(), x.getFname() + " " + x.getLname()));
                }
            }
        }
        categories = CategoryName;
        return CategoryName;
    }
    public ArrayList<Sessions> getSessions() {
        return sessions;
    }


    //Methods
    public Sessions SpecifyAuctionTime(int starttime,String date){
        session.setStart_time(starttime);
        session.setEnd_time(starttime+1);
        session.setSession_date(date);
        session.add();
        sessions.add(session);

        //return this session to update table
        return session;
    }
    public ArrayList<Item> getRequestedItems(){
        ArrayList<Item> requestedItem = new ArrayList<Item>();
        for (Item I : items) {
            if(I.getAccepted() == 0){
                requestedItem.add(I);
            }
        }
        return requestedItem;
    }
    public void ApproveItem(ArrayList<Item> IDs){
        for (Item item : items) {
            for (Item x : IDs) {
                if( x.getId() == item.getId()){
                    item.setID(x.getId());
                    item.setAccepted(1);
                    item.getsession().setSessionReserved(item.getsession().getId());
                    item.update();
                    break;
                }
            }
        }
    }
    public void RejectItem(ArrayList<Item> IDs){
        for (Item item: items) {
            for (Item x : IDs) {
                if( x.getId() == item.getId()){
                    item.setID(x.getId());
                    item.setAccepted(2);
                    item.update();
                    break;
                }
            }
        }
    }
    public void DeleteCategory(ArrayList<Integer> CatID){
        for (Category category: categories) {
            for (int x:CatID) {
                if(x == category.getId()){
                    category.setID(x);
                    category.delete();
                    break;
                }
            }
        }
    }
    public void DeleteUser(ArrayList<Integer> IDs){
        ArrayList<SystemUser> tempusers = new ArrayList<>();
        int size = IDs.size();
        for ( SystemUser x : this.systemUsersList ) {
            for (int ID : IDs) {
                if (x.getId() == ID) {
                    setId(ID);
                    delete();
                    --size;
                    break;
                }else {
                    tempusers.add(x);
                }
            }
        }
        this.systemUsersList = tempusers;        //users after deleted selected users
    }
    public void AddCategory(ArrayList<Category> cat){
        check = false;
        for (Category CAT : cat) {
            for (Category c : categories) {
                if (CAT.getCat_Name().equals(c.getCat_Name())) {
                    check = true;
                    System.out.println("this category found later");
                    break;
                }
            }
            if (!check) {
                category.setID(CAT.getId());
                category.setName(CAT.getCat_Name());
                category.setAdmin_ID(CAT.getAdmin_ID());
                category.add();
            }
        }
    }
    public long getuserscount(){
        ArrayList<Object[]> r = DBInterface.getInstance().select(
                "systemuser",
                "COUNT(*)",
                "Authorization != 0"
        );
        return (long)r.get(0)[0];
    }

    public long getsalescount(){
        ArrayList<Object[]> r = DBInterface.getInstance().select(
                "item",
                "COUNT(*)",
                "Served = 1"
        );
        return (long)r.get(0)[0];
    }
    public long getrequestscount(){
        ArrayList<Object[]> r = DBInterface.getInstance().select(
                "item",
                "COUNT(*)",
                "Accepted = 0"
        );
        return (long)r.get(0)[0];
    }


}



