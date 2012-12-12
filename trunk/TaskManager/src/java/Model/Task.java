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
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getComment() {
        return comment;
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
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
    
}
