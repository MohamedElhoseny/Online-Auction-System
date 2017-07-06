package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elhosany on 5/12/2017.
 */
public abstract class UserType {

    //interface class only for pointing at available roles
    //public abstract void Who();
    public abstract void submitItem(int itemID, String name, int categoryID, String Pic, String details, double price, Sessions session);

    public abstract void participate(int bidderID , int itemID);

    public abstract void SubmitBid(int bidder_id, int session_id, int item_id, int bid_id);

    public abstract List<Item> searchOnProduct(int CategoryID);

    public abstract ArrayList<Item> viewProducts();

    public abstract ArrayList<Item> viewSellerItems(int sellerID);

    public abstract void submitItem(int sellerID, String name, String Pic, String details,
                                    double price, int sessionID, int categoryID, int adminID, int Accepted,int Served);

    public abstract Object[] getSessionItemBid(int hour);
    public abstract ArrayList<Item> SearchonProduct(int CategoryID);
    public abstract ArrayList<Item>  searchOnSpecificProduct (String CategoryName , String itemName);

    public abstract void SubmitBid(int bidder_id, int session_id, int item_id, double bid);

    public abstract ArrayList<Integer> getParticipatedSessions(int userID);

    public abstract ArrayList<Object[]> getParticipatedBidder(int itemID);

    public abstract boolean isParticipated(int itemID);
    public abstract void init_participateditems(int id);

}