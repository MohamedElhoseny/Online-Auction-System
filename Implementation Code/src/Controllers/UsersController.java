package Controllers;

import Models.SystemUser;
import javafx.scene.control.Label;

/**
 * Created by omdae on 6/15/2017.
 */
public interface UsersController {
    public void sethour(int h);
    public void setminute(int m);
    public void setsecond(int s);
    public void setnanosecond(int n);
    public void setam_pm(int x);
    public void setYear(int y);
    public void Notifyuser(String msg);
    public int evaluateBids(int currenthour);
    public int evaluateResults(int currenthour);
    public SystemUser getCurrentUser();
    public int getminute();
    public int getsecond();
    public int getnanosecond();
    public void sendChatMsg(String msg);
    public void receiveChatMsg(int id , String profilepic ,String msg);
}
