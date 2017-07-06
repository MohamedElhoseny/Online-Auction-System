package Models;

import Controllers.BidderController;
import Controllers.UsersController;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class SchedulerNotifier implements Runnable
{
    private UsersController usersController;
    private int hour, minute, second, nanosecond,am_pm,year;
    public  Animator animator;
    private String sendmsg = " ";
    private ArrayList<SystemUser> onlineUsers;


    public SchedulerNotifier()
    {
        new Thread(this).start();
    }
    @Override public void run() {
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        nanosecond = calendar.get(Calendar.MILLISECOND);
        year = calendar.get(Calendar.YEAR);
        am_pm = calendar.get(Calendar.AM_PM);
        if (am_pm != 0) //PM
           hour = hour + 12;

        animator = new Animator();
        animator.start();
    }
    public void initUser(UsersController x) {
        /////
        this.usersController = x;
    }
    public String getNotificationContent(int id){
        DBInterface DB = DBInterface.getInstance();
        ArrayList<Object[]> records = DB.select(
                "notification",
                "message",
                "ID="+id);
        return (String) records.get(0)[0];
    }
    public ArrayList<SystemUser> getOnlineUsers(){
        ArrayList<SystemUser> onlineUsers = new ArrayList<>();
        SystemUser user = new SystemUser();
        for (SystemUser u: user.getSystemUsersList()) {
            if (u.getState() != 0){
                onlineUsers.add(u);
            }
        }
        return onlineUsers;
    }



    //<editor-fold desc="Chat Server">


    private class chatserver {
        MulticastSocket socket;
        InetAddress addrs;
        int port = 6001;

        //constructor
        public chatserver() {
            System.out.println("Connection To System Server ...");
            try {
                socket = new MulticastSocket(6001);
                addrs = InetAddress.getByName("224.0.0.1");
            }catch (Exception e) {
                System.out.println("Fatal error : while establishing Chatting Services !");
            }
            System.out.println("Starting Chatting Services ...");
            new receiver();
            new sender();
        }

        //Sender and Receiver
        private class receiver extends Thread
        {
            public  receiver() {
                System.out.println("Start Receiving Service ..");
                this.start();
            }
            public void run()  {
                byte[] rdata = new byte[256];
                try {
                    socket.joinGroup(addrs);  //join Channel Socket
                } catch (IOException e) {
                    e.printStackTrace();
                }

                DatagramPacket packet = new DatagramPacket(rdata,rdata.length);

                /* while (true){
                     try {
                            socket.receive(packet);  //hyfdl wa2f hna l8ayt lma ygelo packet
                            String rmsg = new String(rdata, 0, packet.getLength());
                            System.out.println("recieved : " + rmsg);
                            usersController.receiveChatMsg(32, "../Resources/Images/ProfilePics/s1.jpg", rmsg);
                     } catch (IOException e) {
                           e.printStackTrace();
                     }
                 }
                 */
            }
        }
        private class sender extends Thread
        {
            byte[] sdata;
            DatagramPacket packet;

            public sender() {
                System.out.println("Start Sending Service ..");
                this.start();
            }
            public void run() {
              /*  while (true){
                    //creating packet including message
                    sdata = sendmsg.getBytes();
                    packet = new DatagramPacket(sdata,sdata.length,addrs,port);
                    //send packet to all host connect with this group
                    try {
                        socket.send(packet);
                        System.out.println("send : "+sendmsg);
                        usersController.sendChatMsg(sendmsg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }*/
            }
        }
    }


    /** methods */
    synchronized public void releaselock(String newmsg){
        sendmsg = newmsg;
    }
    public void startChatServices(){
        new chatserver();
    }
    public void closeChatService(){

    }
    //</editor-fold>


    //Dynamic Classes
    private class Animator extends AnimationTimer {
        boolean finalize = false;
        boolean hasresult = false;

        public Animator(){  //init timer to user
            usersController.sethour(hour);
            usersController.setminute(minute);
            usersController.setsecond(second);
            usersController.setnanosecond(nanosecond);
            usersController.setam_pm(am_pm);
            usersController.setYear(year);
        }
        @Override
        public void handle(long now) {
            usersController.setnanosecond(++nanosecond);
            if (nanosecond >= 60) {
                nanosecond = 0;
                usersController.setnanosecond(0);
                usersController.setsecond(++second);
                if (second >= 60) {
                    second = 0;
                    usersController.setsecond(0);
                    usersController.setminute(++minute);
                    if (minute >= 60) {
                        minute = 0;
                        usersController.setminute(0);
                        usersController.sethour(++hour);

                        hasresult = evaluateBids();
                        if (hasresult)
                            finalize = true;

                        if (am_pm == 0){
                            if (hour == 12){
                                am_pm = 1;
                                usersController.setam_pm(1);
                            }
                        }else {
                            if (hour < 12)
                                hour = hour + 12;  //to make system work (+12) while system is (-12) format

                            if (hour == 24){
                                hour = 0;
                                am_pm = 0;
                                usersController.setam_pm(0);
                                usersController.sethour(hour);
                            }
                        }
                    }else if (minute == 1 && finalize){   //to work with updated data
                        evaluateResults();
                        finalize = false;
                    }
                }
            }
        }
        private boolean evaluateBids(){
            String msg;
            int handle;
            handle = usersController.evaluateBids(hour);
            if (handle != 0) {
                msg = getNotificationContent(handle);
                usersController.Notifyuser(msg);
                return true;
            }
            return false;
        }
        private void  evaluateResults(){
            String msg;
            int handle;
            handle = usersController.evaluateResults(hour);
            if (handle != 0) {
                msg = getNotificationContent(handle);
                usersController.Notifyuser(msg);
            }
        }
    }
}
