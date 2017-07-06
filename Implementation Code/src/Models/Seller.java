package Models;

/**
 * Created by Elhosany on 5/12/2017.
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 4/15/2017.
 */
public class Seller extends UserType {
    @Override
    public void participate(int bidderID, int itemID) {

    }

    @Override
    public void SubmitBid(int bidder_id, int session_id, int item_id, double bid) {

    }

    //Methods
    @Override
// this params are required as it needed to be added in the DB .. admin will be static
    public void submitItem(int sellerID , String name , String Pic
            , String details , double price , int sessionID ,  int categoryID ,int adminID ,int Accepted,int reserved ) {
        Item SubmittedItem = new Item();
        SubmittedItem.setAdmin_ID(adminID);
        SubmittedItem.setSession_ID(sessionID);
        SubmittedItem.setCat_ID(categoryID);
        SubmittedItem.setPrice(price);
        SubmittedItem.setpicture(Pic);
        SubmittedItem.setDetails(details);
        SubmittedItem.setName(name);
        SubmittedItem.setAccepted(Accepted);
        SubmittedItem.setSeller_ID(sellerID);
        SubmittedItem.setServed(reserved);
        SubmittedItem.add();
        System.out.println("Item has been added successfully");
    }

    @Override
    public Object[] getSessionItemBid(int hour) {
        return new Object[0];
    }

    @Override
    public void submitItem(int itemID, String name, int categoryID, String Pic, String details, double price, Sessions session) {

    }

    @Override
    public ArrayList<Item> SearchonProduct(int CategoryID) {
        return null;
    }

    @Override
    public ArrayList<Item> searchOnSpecificProduct(String CategoryName, String itemName) {
        return null;
    }

    @Override
    public void SubmitBid(int bidder_id, int session_id, int item_id, int bid_id) {}

    @Override
    public List<Item> searchOnProduct(int CategoryID) {return null;}

    @Override
    public ArrayList<Item> viewProducts() {
        return null;
    }

    //Get all item Products of Seller
    @Override
    public ArrayList<Item> viewSellerItems(int sellerID) {
        ArrayList<Item> SellerItems = new ArrayList<>();

        Item item = new Item();
        item.initializeItems();
        ArrayList<Item> AllRetrunedItems = item.getItemList();

        for (Item x: AllRetrunedItems) {
            if (sellerID == x.getSeller_ID()){
                SellerItems.add(x);
            }
        }

        return SellerItems;
    }

    @Override
    public ArrayList<Integer> getParticipatedSessions(int userID){
        ArrayList<Integer> info = new ArrayList<>();
        DBInterface DB = DBInterface.getInstance();
        ArrayList<Object[]> records = DB.select(
                "sessions,item",
                "Start_time",
                "sessions.ID = item.session_ID and  item.seller_ID ="+userID     //return all his session time
        );
        for (Object[] e: records) {
            info.add((Integer)e[0]);
        }
        return info;
    }

    @Override
    public void init_participateditems(int id) {

    }

    @Override
    public boolean isParticipated(int itemID) {
        return false;
    }

    @Override
    public ArrayList<Object[]> getParticipatedBidder(int itemID)
    {
        ArrayList<Object[]> bidders = new ArrayList<>();
        DBInterface db = DBInterface.getInstance();

        bidders = db.select("session_participants,systemuser" ,
                "systemuser.Fname , systemuser.Lname",
                "systemuser.ID = session_participants.bidder_ID and session_participants.item_ID = "+itemID
        );
        return  bidders;
    }

}