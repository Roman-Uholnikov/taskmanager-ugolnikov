/*
 * объект группа
 */
package Model;

/**
 *
 * @author roman
 */
public class Group {
    int id;
    String name;
    String fullname;
    int managerID;
   // User manager;

    public Group() {
    }

    public Group(int id, String name, String fullname, int managerID) {
        this.id = id;
        this.name = name;
        this.fullname = fullname;
        this.managerID = managerID;
    }
    
//    public Group(int id, String name, String fullname, User manager) {
//        this.id = id;
//        this.name = name;
//        this.fullname = fullname;
//        this.manager = manager;
//    }
//    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getManagerID() {
//        if (manager != null){
//            return manager.getUserID();
//        }else{
            return managerID;
//        }
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }
    
//    public User getManager() {
//        return this.manager;
//    }
}
