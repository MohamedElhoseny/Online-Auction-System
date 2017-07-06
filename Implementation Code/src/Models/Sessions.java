package Models;
import java.time.LocalDate;
import java.util.ArrayList;

public class Sessions extends MappedClass {
    //Attributes
    public int ID;
    private String Session_date;
    private int Start_time;
    private int End_time;
    private SystemUser winner = null;
    private int reserved;
    public ArrayList<Sessions> SessionList = new ArrayList<Sessions>();
    private ArrayList<Object[]> list;

    // Winner_ID default =0;
    public Sessions() {

    }

    public void initializeSessions() {
        try {
            getData();
        } catch (NullPointerException e) {
            System.out.println("Error in DB");
        }
    }

    public Sessions(int id, String date, int start, int end,int reserved) {
        this.ID = id;
        this.Session_date = date;
        this.Start_time = start;
        this.End_time = end;
        this.reserved = reserved;
        // this.invoice=Invoice;
        // this.Winner_ID=winner;
    }

    //setters
    public void setID(int id) {
        this.ID = id;
    }
    public void setStart_time(int time) {
        this.Start_time = time;
    }
    public void setEnd_time(int time) {
        this.End_time = time;
    }
    public void setSession_date(String session_date) {
        this.Session_date = session_date;
    }
    public void setReserved(int i){
        reserved = i;
    }
    //Getters
    public int getID(){ return this.ID;}
    public String getSession_date() {
        return Session_date;
    }
    public int getStart_time() {
        return this.Start_time;
    }
    public int getEnd_time() {
        return this.End_time;
    }
    public int getReserved(){
        return reserved;
    }

    @Override
    protected String getAttributes() {
        return "Session_date,Start_time,End_time,reserved";
    }

    @Override
    protected String getValues() {
        return "'" + Session_date + "'," + Start_time + "," + End_time+","+reserved;
    }

    @Override
    protected String getOptions() {
        return null;
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

    //Methods
    public ArrayList<Sessions> getSessionList() {
        return SessionList;
    }

    public void getData() throws NullPointerException {
        list = getAll();
        for (Object[] session : list) {
            // Integer.parseInt(session[2]);
            SessionList.add(new Sessions((int) session[0], (String) session[1], (int) session[2], (int) session[3] , (int)session[4]));
        }
    }

    public void add() {
        super.add();
    }

    public void delete() {
        super.delete();
    }

    public void update() {
        super.update();
    }

    //Elhosany
    public SystemUser GetWinnerUser(int end_time) {
        DBInterface db = DBInterface.getInstance();
        ArrayList<Object[]> winnerinfo = null;
        initializeSessions();
        for (Sessions s : SessionList) {
            if (s.getEnd_time() == end_time) {
                winnerinfo = db.select(
                        "systemuser,sessions",
                        "systemuser.ID,systemuser.Fname,systemuser.Lname,systemuser.phone,systemuser.profilePic",
                        "sessions.Winner_ID = systemuser.ID and sessions.End_time = " + end_time
                );
                System.out.println("after select winner in db winnerinfo list is "+winnerinfo);
                break;
            }
        }

        if (!winnerinfo.isEmpty()){
            winner = new SystemUser();
            winner.setId((int) winnerinfo.get(0)[0]);
            winner.setFname((String) winnerinfo.get(0)[1]);
            winner.setLname((String) winnerinfo.get(0)[2]);
            winner.setPhone((String) winnerinfo.get(0)[3]);
            winner.setProfilepic((String) winnerinfo.get(0)[4]);
            System.out.println("winter is set 100%");
        }

        return winner;
    }
    public void setWinnerUser(int sessionid , int winnerid){
        DBInterface dbInterface = DBInterface.getInstance();
        dbInterface.update("sessions","Winner_ID",winnerid+"","ID = "+sessionid);
    }
    public ArrayList<Object[]> GetWinnerinfo(int itemID){
        ArrayList<Object[]> record = DBInterface.getInstance().select(
                "sessions , item , systemuser , session_participants",
                "systemuser.Fname , systemuser.Lname , systemuser.phone , systemuser.Email , session_participants.price,systemuser.profilePic",
                "sessions.ID = item.session_ID\n" +
                        "        and sessions.Winner_ID = systemuser.ID\n" +
                        "        and session_participants.bidder_ID = systemuser.ID\n" +
                        "        and session_participants.item_ID = item.ID\n" +
                        "        and item.ID = "+itemID
        );

        return record;
    }
    public void setSessionReserved(int sessionid){
        DBInterface.getInstance().update(
                "sessions",
                "reserved",
                "1",
                "sessions.ID = "+sessionid
        );
    }
}