package Models;


import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by G510 on 4/15/2017.
 */
public class MainTest
{
    public static void main(String[] args) throws SQLException
    {
      /*  System.out.println("Testing class [For Queries]");

        DBInterface DBinstance = DBInterface.getInstance();


        String attributes = "item_name, Details, picture, price",
               values = " \"laptop\", \"details\", \"path\\somewhere\\example.jpg\", 15.3";

        //Tracing Insert
        DBinstance.insert("item", attributes, values);


        //Tracing Select
        ArrayList<Object[]> selectedItems =  DBinstance.select("item","ID");
        ArrayList<Item> items = new ArrayList<>();

        for (Object[] selectedItem : selectedItems)          //Object[]  == Means 'Record'
        {
            items.add(new Item( (int)selectedItem[0]) );    //id -> (int)selectedItem[0])
        }

        for (Item x: items) {
           System.out.println(x.getId());
        }


        //Tracing Delete
        DBinstance.delete("item",8);

        //Tracing Update
        DBinstance.update("item","Item_name = 'Smart Tv'",1," price = 15.3");

*/

        SystemUser user = new SystemUser();
        int R = user.Login("hassanhamdyriad@yahoo.com","123456M");

        switch (R){
            case 0:
                System.out.println("Welcome Admin " + user.getFname() + " " + user.getLname() );
                break;
            case 1:
                System.out.println("Seller");
                break;
            case 2:
                System.out.println("Bidder");
                break;
            case -1:
                System.out.println("Email Not Found");
                break;
            case -2:
                System.out.println("Password Is Wrong");
        }

    }

}
