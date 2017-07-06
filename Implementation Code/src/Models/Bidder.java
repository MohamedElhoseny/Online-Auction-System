package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elhosany on 5/12/2017.
 */
public class Bidder extends UserType {
    //by elhosany
    private ArrayList<Integer> participatedItems;


    public Bidder(int bidderid){
        System.out.println("setting Bidder Role to Login User[id = "+bidderid+"] ..");
        init_participateditems(bidderid);
    }

    @Override
    public void submitItem(int itemID , String name , int categoryID , String Pic , String details , double price , Sessions session) {}

    @Override
    public ArrayList<Object[]> getParticipatedBidder(int itemID) {
        return null;
    }

    @Override
    public void SubmitBid(int bidder_id, int session_id, int item_id, int bid_id) {

    }

    @Override
    public List<Item> searchOnProduct(int CategoryID) {
        return null;
    }
    //Methods

    @Override
    public ArrayList<Item> viewSellerItems(int sellerID) {
        return null;
    }


    @Override
    public void submitItem(int sellerID, String name, String Pic, String details, double price, int sessionID, int categoryID, int adminID, int Accepted, int Served) {

    }






    //Get all Products to show to user
    public ArrayList<Item> viewProducts() {
        //  ArrayList<Item> AllRetrunedItems = new ArrayList<>();
        //  Item neededItem = new Item();
        //   AllRetrunedItems = neededItem.getItemList();
        ArrayList<Item> AcceptedItems = new ArrayList<>();
        Item item = new Item();
        item.initializeItems();
        ArrayList<Item> AllRetrunedItems = item.getItemList();
        for (Item it: AllRetrunedItems){
            if (it.getAccepted() == 1 && it.getServed() == 0){   //accept and not served then can be in product menu
                System.out.println(it.getServed());
                AcceptedItems.add(it);
            }
        }

        return AcceptedItems;
    }
    @Override
    public Object[] getSessionItemBid(int hour) {
        Object[] INFO=null;
        DBInterface DB = DBInterface.getInstance();
 /* this query to get All info from database ... if you want something else tell me or addit to the query*/
        ArrayList<Object[]> AllInfo = DB.select(
                "item,Sessions,Set_Bids,Category",
                "item.ID,item.Item_name,item.Details,Category.Cat_Name,item.picture,item.price,Sessions.Start_time,Sessions.End_time,Max(Set_Bids.Bids)",
                "Category.ID =item.Cat_ID and Sessions.ID = Set_Bids.session_ID and item.ID = Set_Bids.Item_ID and Sessions.Start_time="+hour
        );
        for (Object[] obj : AllInfo) {
            /*
            System.out.println((obj[0].toString())); // item.ID
            System.out.println((obj[1].toString())); // item.Item_name
            System.out.println((obj[2].toString())); // item.Details
            System.out.println((obj[3].toString())); // Category.Cat_Name
            System.out.println((obj[4].toString())); // item.picture
            System.out.println((obj[5].toString())); // item.price
            System.out.println((obj[6].toString())); // Sessions.Start_time
            System.out.println((obj[7].toString())); // Sessions.End_time
            System.out.println((obj[8].toString())); // Max(Set_Bids.Bids)
            */

            INFO = obj; // to return object of all info
        }
        return INFO;
    }

    @Override
    public ArrayList<Integer> getParticipatedSessions(int userID){
        ArrayList<Integer> info = new ArrayList<>();
        DBInterface DB = DBInterface.getInstance();
        ArrayList<Object[]> records = DB.select(
          "sessions,session_participants",
                "Start_time",
                     "sessions.ID = session_participants.session_ID and session_participants.Bidder_ID ="+userID
        );
        for (Object[] e: records) {
            info.add((Integer)e[0]);
        }
        return info;
    }
    @Override
    public ArrayList<Item> SearchonProduct(int CategoryID) {
        Item item = new Item();
        item.initializeItems();
        Category category = new Category();
        category.initializeCategories();

        ArrayList<Item> AllItems , CategoryItems = new ArrayList<>();
        AllItems = item.getItemList();

        for (Item obj : AllItems) {
            if (obj.getCat_ID() == CategoryID){
                CategoryItems.add(obj);
            }
        }
        System.out.println(CategoryItems.size());
        return CategoryItems;
    }
    @Override
    public ArrayList<Item>  searchOnSpecificProduct (String CategoryName , String itemName){
        System.out.println(CategoryName);
        System.out.println(itemName);
        int categoryID = 0;
        Item item = new Item();
        item.initializeItems();
        Category category = new Category();
        category.initializeCategories();
        ArrayList<Item> AllItems;
        ArrayList<Item> WantedItems= new ArrayList<>();
        ArrayList<Category> AllCtegories = category.getCategoryList();
        AllItems = item.getItemList();
        for (Category c : AllCtegories) {
            if (CategoryName.equals(c.getCat_Name()))
            {
                System.out.println("found Category");
                categoryID = c.getId();
                for (Item obj : AllItems) {
                    if (obj.getItem_name().equals(itemName)){
                        System.out.println("found item &added");
                        WantedItems.add(obj);
                    }
                }
            } else {
                System.out.println("Didn't find");
            }
        }
        return WantedItems;
    }

    @Override
    public void SubmitBid(int bidder_id, int session_id, int item_id, double bid) {
        // Set_Bids
        DBInterface DB = DBInterface.getInstance();
        DB.insert("Set_Bids","Bidder_ID,session_ID,Item_ID,Bids",""+bidder_id+","+session_id+","+item_id+","+bid);
        System.out.println("Bid submitted successfully successfully");
    }

    @Override
    public void init_participateditems(int id) {
        participatedItems = new ArrayList<>();
        DBInterface db = DBInterface.getInstance();
        ArrayList<Object[]> result = db.select("session_participants","item_ID","bidder_ID = "+id);
        for (Object[] record: result) {
            participatedItems.add((Integer) record[0]);
        }
    }
    @Override
    public boolean isParticipated(int itemID){
        return participatedItems.contains(itemID);
    }

    @Override
    public void participate(int bidderID , int itemID) {
        int sessionID = 0;
        ArrayList<Item> AllItems;
        Item item = new Item();
        item.initializeItems();
        AllItems = item.getItemList();
        for (Item it : AllItems) {
            if (it.getId() == itemID) {
                System.out.println(it.getSession_ID());
                sessionID = it.getSession_ID();
            }
        }
        DBInterface DB = DBInterface.getInstance();
        DB.insert("session_Participants", "session_ID,bidder_ID,item_ID", "" + sessionID + "," + bidderID+","+ itemID);
        System.out.println("participate successfully");
        init_participateditems(bidderID);
        System.out.println("reinitializing Participated items List ..");
    }

}
