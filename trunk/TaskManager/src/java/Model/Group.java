/*
 * объект группа
 */
package Model;

import Control.Exceptions.UserInputException;

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
    
    public static void add(String name, String title, int manager) throws UserInputException, ClassNotFoundException{
        //проверяем, существует ли такой пользователь
        int managerId = manager;
        if ((DAO.getInstance().getUser(manager)== null) & (manager > 0)){
            //данные не верны, пользователь  не существует
            throw new UserInputException("неверные данные, пользователь не существует");
        }
        //добавляем группу
        DAO.getInstance().addGroup(name, title, manager);
        //изменяем у пользователя данные о принадлежности к группе и права
        if (managerId > 0){// отрицательное значение означает что координатора не задавали при создании
            User managerU = User.getUser(managerId);
            managerU.setRights(2);
            managerU.setGroupId(DAO.getInstance().getGroupID(name));
            managerU.SaveEdit();
        }
    }
}
