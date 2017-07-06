package Models;


import java.util.ArrayList;

/**
 * Created by lenovo on 4/15/2017.
 */
public class Member extends SystemUser {
    //Attributes
    public UserType membertype;

    //Constructor
    public Member(){
    }

    public Member(SystemUser LoginClient , int auth){
        if (auth == 1)
            membertype = new Seller();
        if (auth == 2)
            membertype = new Bidder(LoginClient.id);

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


    //Methods
    public void editInfo (SystemUser user , String EditedFname , String EditedLname , String EditedPassword) {
        user.setFname(EditedFname);
        user.setLname(EditedLname);
        user.setPassword(EditedPassword);
        user.update();
    }
    public boolean canRegister(String email){
        ArrayList<String> checkEmails = this.GetEmails();
        for (String em : checkEmails) {
            if (em.equals(email)) {
                System.out.println("Email is exist");
                return false;
            }
        }
        return true;
    }
    public void Register (String Fname , String Lname ,String Email , String password  , String phone ,
                          String  Birthdata, int gender , String ProfilePic , int auth) {
        this.setFname(Fname);
        this.setLname(Lname);
        this.setEmail(Email);
        this.setBirthdate(Birthdata);
        this.setPhone(phone);
        this.setPassword(password);
        this.setGender(gender);
        this.setProfilepic(ProfilePic);
        this.setAuthorization(auth);
        this.setEmail(Email);
        // insert info. into database
        this.add();
        System.out.println("registered successfully");
    }
    public Item viewDetails(int itemId) {
      //  Item neededItem = new Item() ;
      //  AllRetrunedItems = neededItem.getItemList();
        // AllItems = Item.get();

        Item item = new Item();
        item.initializeItems();  //init
        ArrayList<Item> AllRetrunedItems = item.getItemList();


        Item neededItem = null;
        for (Item x: AllRetrunedItems) {
            if (itemId == x.getId()) {
                neededItem = x;
            }
        }
        return neededItem;
    }

}
