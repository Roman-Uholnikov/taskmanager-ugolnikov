/*
 * обьект "задание"
 */

package Model;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author admin4eg
 */
public class Task {
    
    int id;   
    int costumer;
    Date createDate;
    int priority;
    int reciver;
    boolean grouptype;
    String title;
    String text;
    String comment;
    String localization;
    Date closeDate;

    
    public Task(){
    }

    public Task(int id, int costumer, Date createDate, int priority, int reciver, boolean grouptype, String title, String text, String comment, String localization, Date closeDate) {
        this.id = id;
        this.costumer = costumer;
        this.createDate = createDate;
        this.priority = priority;
        this.reciver = reciver;
        this.grouptype = grouptype;
        this.title = title;
        this.text = text;
        this.comment = comment;
        this.localization = localization;
        this.closeDate = closeDate;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCostumer() {
        return costumer;
    }

    public void setCostumer(int costumer) {
        this.costumer = costumer;
    }

    public Date getCreateDate() {
        //dateManager.format(task.getCreateDate());
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getReciver() {
        return reciver;
    }

    public void setRecever(int reciver) {
        this.reciver = reciver;
    }

    public boolean isGrouptype() {
        return grouptype;
    }

    public void setGrouptype(boolean grouptype) {
        this.grouptype = grouptype;
    }

    public String getTitle() {
        if (this.title!= null){
            return this.title;
        }else{
            return "";
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        if (this.text!= null){
            return this.text;
        }else{
            return "";
        }
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getComment() {
        if (comment!= null){
            return comment;
        }else{
            return "";
        }
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public Date getCloseDate() {
        String nullTime = closeDate.toGMTString();
        if (nullTime.equalsIgnoreCase("31 Dec 1969 22:00:00 GMT")){
            return null;
        }else{
            return closeDate;
        }
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
    
    /**
     * передача заявки другому исполнителю
     * @param newReciver 
     */
    public void forward( int userId){
        //передача заявки другому получателю
        this.setRecever(userId);
        DAO.getInstance().updateTask(this);
    }
    
    public void addComment(String comment, int userId){
        //добавить коментарий
        String userName = User.getUser(userId).getName();
        this.setComment(this.getComment()+"\\n"+new Date()+" "+userName+" "+comment);
        DAO.getInstance().updateTask(this);
    }
    
    /**
     * закритие заявки (віполнена)
     */
    public void close(){
        this.closeDate = new Date();
        DAO.getInstance().updateTask(this);
        
    }

    public String getCloseDateString() {
        String result = "";
        // формат 2013-01-07 07:26:13
        result += String.valueOf(this.closeDate.getYear()+1900); 
        result += "-" + String.valueOf(this.closeDate.getMonth()+1);
        result += "-" + this.closeDate.getDate();
        result += " " + this.closeDate.getHours();
        result += ":" + this.closeDate.getMinutes();
        result += ":" + this.closeDate.getSeconds();
        return result;
    }
    
}
