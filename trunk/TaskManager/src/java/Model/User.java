/*
 *ПользоBатeль а cистеме
 */
package Model;

import Control.Exceptions.UserAutentificationException;
import Control.WebEngine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.persistence.sessions.Session;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;


/**
 *
 * @author admin4eg
 */
public class User {

    public static final  int WORKER_RIGHTTS = 1;
    
    public static final  int MANGER_RIGHTTS = 2;
    
    public static final  int ADMIN_RIGHTTS = 3;
    
    private int userID;
    
    private String name;
    
    private String phone;
    
    private String location;
    
    //private Group group;
    private int groupId;
    
    private String loginname;
    
    /** 1 пользователь, 2 координатор, 3 администратор*/
    private volatile int rights;
    
    private List<Task> tasksList;
    
      
    public User() {
    }
    
    
    public User(int userID, String name, String phone, String location, int group, String loginname, int rights) {
        this.userID = userID;
        setName(name);
        setLocation(location);
        setPhone(phone);
        this.location = location;
        this.groupId = group;
        this.loginname = loginname;
        this.rights = rights;
    }
    
        
    /**
     * пользователь получает все "свои" заявки - то что относится к нему или к группе.
     */
    public void makeTasksQueue(boolean showDone) throws ClassNotFoundException {
        //tasksList.clear();
        //tasksList = DAO.getInstance().getTasks(this);
        tasksList = new ArrayList<Task>(DAO.getInstance().getTasks(this, showDone));
    }
    
    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }else{
            this.name = "";
        }
    }

    public String getPhone() {
        return phone;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setPhone(String phone) {
        if (phone != null) {
            this.phone = phone;
        }else{
            this.phone = "";
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location != null) {
            this.location = location;
        }else{
            this.location = "";
        }      
    }

    public int getRights() {
        return rights;
    }

    public void setRights(int rights) {
        this.rights = rights;
    }
    
    public static User getUser(int userID){
        return DAO.getInstance().getUser(userID);
    }

//    public Group getGroup() {
//        return group;
//    }
//
//    public void setGroup(Group group) {
//        this.group = group;
//    }
    
    
    
    /**
     * создать пользователя по логину и паролю
     * @param login
     * @param password
     */
    public static User getUser(String login,String password) throws UserAutentificationException, Exception{
         return DAO.getInstance().getUser(login, password);
    }

    @Override
    public String toString() {
        Group group = null;
        try {
            group = DAO.getInstance().getGroup(getGroupId());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name + "("+loginname +")\n" + phone + "\n телефон: " + location + "\n отдел: " + group.getName();
    }
    
    
    public List<Task> getTasksQueue(){
        return new ArrayList<Task>(tasksList);
    }
    
    
    public void SaveEdit(){
        //сохраняем экземпляр в бд
        DAO.getInstance().saveRealUser(this);
    }
}
