/*
 * Слой доступа к данным. Клас предоставляет интерфейс между приложением и БД
 * реализован по шаблону Singlton
 */
package Model;

import Control.Exceptions.UserAutentificationException;
import Control.Exceptions.UserInputException;
import Control.WebEngine;
import Model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import org.apache.tomcat.dbcp.dbcp.DriverManagerConnectionFactory;

/**
 *
 * @author admin4eg
 */
public class DAO {
    
    public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/TaskManager";
    
    public static final String USER_NAME = "root";
    
    public static final String PASSWORD = "";
    
    public static final String DATA_BASE_DRIVER = "com.mysql.jdbc.Driver";
    
            
    Connection connection;
    
    private static volatile DAO instance;
    
    private DAO(){
        
    }
    
    /**
     * Обьектт, предоставляющий доступ к базе данных. Существует в единственном 
     * экземпляре во время выполнения приложения
     * @return 
     */
    public static DAO getInstance(){
        synchronized(DAO.class){
            if(instance == null){
                instance = new DAO();
            }
         return instance;
        }
    }
    
    
    //
    public List<String> SqlExecute(String sqlStatement) throws ClassNotFoundException{
        Statement statement =null;
        LinkedList result = null;
        try {
            Class.forName(DATA_BASE_DRIVER);
            //создать соединение с базой
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            //создать выражение
            statement = connection.createStatement();
            //выполнить выражение
            statement.executeQuery(sqlStatement);
            //результирующий список откопировать
            //result = statement.getResultSet();
            
                        
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                //закрыть выражение
                statement.close();
                //resultSet.close();
                //закрыть результируюший набор
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    /**
     * получение обьекта пользователь
     * @param login
     * @param password
     * @return 
     */
    public User getUser(String login, String password) throws UserAutentificationException, Exception{
        ResultSet rs;
        Statement statement =null;
        ResultSet resultSet = null;
        List<User> resultList = new LinkedList<User>();
        User user = null;
        String sqlStatement = "SELECT * FROM users WHERE loginname=\"" + login + "\"";
        
        try {
            Class.forName(DATA_BASE_DRIVER);
            //создать соединение с базой
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            //создать выражение
            statement = connection.createStatement();
            //выполнить выражение
            statement.executeQuery(sqlStatement);
            //результирующий список откопировать
            rs = statement.getResultSet();
            String currentLogin = null;
            
            while (rs.next()) {
                if (rs.getString("loginname").equalsIgnoreCase(login)){
                    if (rs.getString("password").equalsIgnoreCase(WebEngine.getEncryptString(password))){
                        user = new User(rs.getInt("id"), rs.getString("name"), 
                                rs.getString("phone"), rs.getString("location"),
                                rs.getInt("group"), rs.getString("loginname"), rs.getInt("rights"));
                        resultList.add(user);
                    }
                }
            }
            
                        
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("база данных не рабтает. Свяжитесь с системным администратором");
        }finally{
            try {
                //закрыть выражение
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                //закрыть результируюший набор
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new Exception("база данных не рабтает. Свяжитесь с системным администратором");
            }
        }
        
        
          //return new User(177, "Ugolnikov Roman","3456", "somewhere", getGroup(1), "u-roma", 1);
        if (resultList.size() > 0){
            return resultList.get(0);
        }else{
            return null;
        }
    }
    
     /**
     * получение обьекта пользователь из ба по его идентификатору
     * @param ID
     * @return 
     */
    public User getUser(int id){
        //TODO надо получить пользователя из бд 
        ResultSet rs;
        Statement statement =null;
        ResultSet resultSet = null;
        List<User> resultList = new LinkedList<User>();
        User user = null;
        String sqlStatement = "SELECT * FROM users WHERE id=\"" + id + "\"";
        
        try {
            Class.forName(DATA_BASE_DRIVER);
            //создать соединение с базой
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            //создать выражение
            statement = connection.createStatement();
            //выполнить выражение
            statement.executeQuery(sqlStatement);
            //результирующий список откопировать
            rs = statement.getResultSet();
            String currentLogin = null;
            
            while (rs.next()) {
                if (rs.getInt("id") == id){
                    user = new User(rs.getInt("id"), rs.getString("name"), 
                            rs.getString("phone"), rs.getString("location"),
                            rs.getInt("group"), rs.getString("loginname"), rs.getInt("rights"));
                    resultList.add(user);
                }
            }
            
                        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                //закрыть выражение
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                int g = 0;
                //закрыть результируюший набор
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
          //return new User(177, "Ugolnikov Roman","3456", "somewhere", getGroup(1), "u-roma", 1);
        if (resultList.size() > 0){
            return resultList.get(0);
        }else{
            return null;
        }
    }
    
    /**
     * удаление обьекта пользователь из БД
     * @param ID
     * @return 
     */
    public  boolean deleteUser(int ID){
        //TODO
        return true;
    }
    
    
    /**
     * удаление обьекта пользователь из БД
     * @param user
     * @return 
     */
    public boolean deleteUser(User user){
        //TODO
        return true;
    }

    /**
     * возворащяет список заданий, отностящихся к пользователю, переданному в араметрах
     * (это либо для него заявка, либо для его группы)
     * @param user пользователь для которого нужно выбрать заявки
     * @return  список заявок
     */
    List<Task> getTasks(User user, boolean showeDone) throws ClassNotFoundException {
        Task task;
        List<Task> resultList = new ArrayList();
        Statement statement =null;
        ResultSet rs = null;
        int reciverId = user.getUserID();
        int groupId = user.getGroupId();
//                
//     SELECT demends.id, demends.reciver, demends.customer,
//                demends.createdate, demends.priority, demends.reciver, demends.grouptype,
//                demends.comments, demends.title, demends.text, demends.localization, demends.closedate
//                FROM (groups INNER JOIN users ON groups.id = users.group) INNER JOIN demends ON users.id = demends.reciver
//                WHERE (((groups.id)=XX) AND ((demends.grouptype > 0))) OR (demends.reciver = XX)
        
        
        String sqlStatement =  "SELECT demends.id, demends.reciver, demends.customer,\n"
    + "demends.createdate, demends.priority, demends.reciver, demends.grouptype,\n"
    + "demends.comments, demends.title, demends.text, demends.localization, demends.closedate\n"
    + "FROM (groups INNER JOIN users ON groups.id = users.group) INNER JOIN demends ON users.id = demends.reciver\n"
    + "WHERE (((groups.id="+groupId+") AND ((demends.grouptype > 0))) OR (demends.reciver = " + reciverId + "))";
        
        if(!showeDone){//показываем только те у которых нет времени закрытия, т. е. активные
            sqlStatement += "AND demends.closedate = null";
        }
        
        
        try {
            Class.forName(DATA_BASE_DRIVER);
            //создать соединение с базой
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            //создать выражение
            statement = connection.createStatement();
            //выполнить выражение
            statement.executeQuery(sqlStatement);
            //результирующий список откопировать
            rs = statement.getResultSet();
            String currentLogin = null;
            
            while (rs.next()) {
                task = new Task(rs.getInt("id"), rs.getInt("customer"), rs.getDate("createdate"),
                        rs.getInt("priority"), rs.getInt("reciver"), rs.getBoolean("grouptype"), 
                        rs.getString("title"), rs.getString("text"), rs.getString("comments"), 
                        rs.getString("localization"), rs.getDate("closedate"));
                resultList.add(task);
                
            }
            
                        
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                //закрыть выражение
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                //закрыть результируюший набор
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        return resultList;
       
//        
//        result.add(new Task(44, user, new java.util.Date(), 2, user, true,
//                "заголовок заявки", "текст заголовка, который должен быть отображен в ------------"
//                + "----------------------------------------------------------"
//                + "-----------------------------------------------------------"
//                + "-----------------------------------------------------------",
//                " дата - коментарий"
//                + "дата - коментарий", "локализация заявки", new java.util.Date()));
//        result.add(new Task(45, user, new java.util.Date(), 1, user, true,
//                "заголовок заявки", "текст заголовка, который должен быть отображен в ------------"
//                + "----------------------------------------------------------"
//                + "-----------------------------------------------------------"
//                + "-----------------------------------------------------------",
//                " дата - коментарий"
//                + "дата - коментарий", "локализация заявки", null));
//                
        
    }
    
    public Task getTask(int id) throws ClassNotFoundException{
        Task task;
        List<Task> resultList = new ArrayList();
        Statement statement =null;
        ResultSet rs = null;
       
        String sqlStatement = "SELECT * FROM demends WHERE id=" + id+ " ORDER BY createdate DESC";
        
        try {
            Class.forName(DATA_BASE_DRIVER);
            //создать соединение с базой
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            //создать выражение
            statement = connection.createStatement();
            //выполнить выражение
            statement.executeQuery(sqlStatement);
            //результирующий список откопировать
            rs = statement.getResultSet();
            String currentLogin = null;
            
            while (rs.next()) {
                boolean isGroupTask;
                String s23=  rs.getString("createdate");
                Date crdate = WebEngine.parseDate(rs.getString("createdate"));
                Date cldate = WebEngine.parseDate(rs.getString("closedate"));
                if (rs.getInt("grouptype")>0){
                    isGroupTask = true;
                }else{
                    isGroupTask = false;
                }
                
                task = new Task(rs.getInt("id"), rs.getInt("customer"), crdate,
                        rs.getInt("priority"), rs.getInt("reciver"), isGroupTask, 
                        rs.getString("title"), rs.getString("text"), rs.getString("comments"), 
                        rs.getString("localization"), cldate);
                
                resultList.add(task);
                
            }
            
                        
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                //закрыть выражение
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                //закрыть результируюший набор
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
          //return new User(177, "Ugolnikov Roman","3456", "somewhere", getGroup(1), "u-roma", 1);
        if (resultList.size() > 0){
            return resultList.get(0);
        }else{
            return null;
        }
    }
    
    /**
     * возвращает список пользователей подходяших заданному выражению (поиск на вхождение)
     * @param searchStatement - строка для поиска. Если null значит выражение не заданно, возвращаем всех
     * @return 
     */
    public List<User> getUsers(String searchStatement) throws ClassNotFoundException{
        
        ResultSet rs;
        Statement statement =null;
        ResultSet resultSet = null;
        List<User> resultList = new LinkedList<User>();
        User user = null;
        if (searchStatement == null) searchStatement = "";
        String sqlStatement = "SELECT * FROM users WHERE name Like\"%" + searchStatement + "%\" ORDER BY name";
        
        try {
            Class.forName(DATA_BASE_DRIVER);
            //создать соединение с базой
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            //создать выражение
            statement = connection.createStatement();
            //выполнить выражение
            statement.executeQuery(sqlStatement);
            //результирующий список откопировать
            rs = statement.getResultSet();
            String currentLogin = null;
            
            while (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("name"), 
                        rs.getString("phone"), rs.getString("location"),
                        rs.getInt("group"), rs.getString("loginname"), rs.getInt("rights"));
                resultList.add(user);
            }
            
                        
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                //закрыть выражение
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                //закрыть результируюший набор
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
//        
//          //return new User(177, "Ugolnikov Roman","3456", "somewhere", getGroup(1), "u-roma", 1);
//        if (resultList.size() > 0){
//            return resultList.get(0);
//        }else{
//            return null;
//        }
//        
//        resultList.add(new User(1, "Ugolnikov Roman","3456", "somewhere", 1, "u-roma", 1));
//        resultList.add(new User(1, "Ugolnikov Roman","3456", "somewhere", 1, "u-roma", 1));
//        resultList.add(new User(1, "Ugolnikov Roman","3456", "somewhere", 1, "u-roma", 1));
//        resultList.add(new User(1, "Ugolnikov Roman","3456", "somewhere", 1, "u-roma", 1));
//        resultList.add(new User(1, "Ugolnikov Roman","3456", "somewhere", 1, "u-roma", 1));
//        resultList.add(new User(1, "Ugolnikov Roman","3456", "somewhere", 1, "u-roma", 1));
//        resultList.add(new User(1, "Ugolnikov Roman","3456", "somewhere", 1, "u-roma", 1));
//        resultList.add(new User(1, "Ugolnikov Roman","3456", "somewhere", 1, "u-roma", 1));
//        resultList.add(new User(1, "Ugolnikov Roman","3456", "somewhere", 1, "u-roma", 1));
//        resultList.add(new User(1, "Ugolnikov Roman","3456", "somewhere", 1, "u-roma", 1));
//        resultList.add(new User(1, "Ugolnikov Roman","3456", "somewhere", 1, "u-roma", 1));
//        
        return resultList;
    }
    
    public Group getGroup(int id) throws ClassNotFoundException{
        Group group;
        List<Group> resultList = new ArrayList();
        Statement statement =null;
        ResultSet rs = null;
                
        User manager = getUser(new Group(), id);
        String sqlStatement = "SELECT * FROM groups WHERE id=\"" + id+ "\"";
        
        try {
            Class.forName(DATA_BASE_DRIVER);
            //создать соединение с базой
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            //создать выражение
            statement = connection.createStatement();
            //выполнить выражение
            statement.executeQuery(sqlStatement);
            //результирующий список откопировать
            rs = statement.getResultSet();
            String currentLogin = null;
            
            while (rs.next()) {
                group = new Group(rs.getInt("id"), rs.getString("name"), rs.getString("fullname"), manager.getUserID());
                resultList.add(group);
                
            }
            
                        
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                //закрыть выражение
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                //закрыть результируюший набор
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
          //return new User(177, "Ugolnikov Roman","3456", "somewhere", getGroup(1), "u-roma", 1);
        if (resultList.size() > 0){
            return resultList.get(0);
        }else{
            return null;
        }
    }

    /**
     * получить ИД группи по ее имени
     * @param name имя групы
     * @return
     * @throws ClassNotFoundException 
     */
    public int getGroupID(String name) throws ClassNotFoundException{

        int result = 0;
        Statement statement =null;
        ResultSet rs = null;
                
        
        String sqlStatement = "SELECT * FROM groups WHERE `name`='" + name+ "'";
        
        try {
            Class.forName(DATA_BASE_DRIVER);
            //создать соединение с базой
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            //создать выражение
            statement = connection.createStatement();
            //выполнить выражение
            statement.executeQuery(sqlStatement);
            //результирующий список откопировать
            rs = statement.getResultSet();
            String currentLogin = null;
            
            while (rs.next()) {
                result = Integer.valueOf(rs.getInt("id"));
                return result;
                //блок finally выполнится все равно.
            }
            
                        
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                //закрыть выражение
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                //закрыть результируюший набор
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        return result;
    }
    
    //
    public List<Group> getGroups() throws ClassNotFoundException {
       
        List<Group> resultList = new LinkedList<Group>();
//        groups.add(new Group(1, "ГРиТПИТ", "Группа развития и технической потдержки", 1));
//        groups.add(new Group(1, "ГРиТПИТ", "Группа развития и технической потдержки", 1));
//        groups.add(new Group(1, "ГРиТПИТ", "Группа развития и технической потдержки", 1));
//        //groups.add(new Group(11, "ГРиТПИТ", "Группа развития и технической потдержки", u1));
//        groups.add(new Group(1, "ГРиТПИТ", "Группа развития и технической потдержки", 1));
        
        ResultSet rs;
        Statement statement =null;
        ResultSet resultSet = null;
        Group group = null;
        
        String sqlStatement = "SELECT * FROM groups";
        
        try {
            Class.forName(DATA_BASE_DRIVER);
            //создать соединение с базой
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            //создать выражение
            statement = connection.createStatement();
            //выполнить выражение
            statement.executeQuery(sqlStatement);
            //результирующий список откопировать
            rs = statement.getResultSet();
            String currentLogin = null;
            
            while (rs.next()) {
                group = new Group(rs.getInt("id"), rs.getString("name"), rs.getString("fullname"), 1); //XXX
               
                resultList.add(group);
            }
            
                        
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                //закрыть выражение
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                //закрыть результируюший набор
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultList;
    }
    
        
    public User getUser(Group group, int groupId){
        //TODO надо получить пользователя из бд 
        ResultSet rs;
        Statement statement =null;
        ResultSet resultSet = null;
        List<User> resultList = new LinkedList<User>();
        User user = null;
        // SELECT * FROM users WHERE users.group="X" AND users.rights="XXX"
        String sqlStatement = " SELECT * FROM users WHERE users.group=\""+groupId+"\" AND users.rights=\"1\"";
        
        try {
            Class.forName(DATA_BASE_DRIVER);
            //создать соединение с базой
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            //создать выражение
            statement = connection.createStatement();
            //выполнить выражение
            statement.executeQuery(sqlStatement);
            //результирующий список откопировать
            rs = statement.getResultSet();
            String currentLogin = null;
            
            while (rs.next()) {
                    user = new User(rs.getInt("id"), rs.getString("name"), 
                            rs.getString("phone"), rs.getString("location"),
                            rs.getInt("group"), rs.getString("loginname"), rs.getInt("rights"));
                    resultList.add(user);
            }
            
                        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                //закрыть выражение
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                int g = 0;
                //закрыть результируюший набор
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
          //return new User(177, "Ugolnikov Roman","3456", "somewhere", getGroup(1), "u-roma", 1);
        if (resultList.size() > 0){
            return resultList.get(0);
        }else{
            return null;
        }
    }
    
    
    public void addGroup(String groupname,String title,int manager) throws UserInputException {

        Statement statement =null;
        String sqlStatement;

        //INSERT INTO `taskmanager`.`groups` (`id`, `name`, `fullname`)
        //VALUES (NULL, 'Вагонники', 'Отдел экспуатации вагонных составов');
        
        sqlStatement = "INSERT INTO `taskmanager`.`groups` (`name`, `fullname`)";
        sqlStatement += "VALUES ('"+groupname+"', '"+title+"')";
        
        
        try {
            Class.forName(DATA_BASE_DRIVER);
            //создать соединение с базой
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            //создать выражение
            statement = connection.createStatement();
            //выполнить выражение
            //statement.executeQuery(sqlStatement);
            statement.executeUpdate(sqlStatement);
            
            
                        
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                //закрыть выражение
                if (statement != null) statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }
    
    
    public void addUser(User user, String login, String password) throws UserInputException {

        Statement statement = null;
        String sqlStatement;

        //INSERT INTO `taskmanager`.`groups` (`id`, `name`, `fullname`)
        //VALUES (NULL, 'Вагонники', 'Отдел экспуатации вагонных составов');
        
        sqlStatement = "INSERT INTO `taskmanager`.`users` (`name`, `group`, `phone`, `location`, `rights`, `loginname`, `password`)";
        sqlStatement += " VALUES ('"+user.getName()+"', '"+user.getGroupId();
        sqlStatement +="', '"+user.getPhone();
        sqlStatement +="', '"+user.getLocation();
        sqlStatement +="', '"+user.getRights();
        sqlStatement +="', '"+login;
        sqlStatement +="', '"+WebEngine.getEncryptString(password);
        sqlStatement +="')";
        
        
        try {
            Class.forName(DATA_BASE_DRIVER);
            //создать соединение с базой
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            //создать выражение
            statement = connection.createStatement();
            //выполнить выражение
            //statement.executeQuery(sqlStatement);
            statement.executeUpdate(sqlStatement);
            
            
                        
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                //закрыть выражение
                if (statement != null) statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }
    
    /**
     * сохранение существующего пользователя в бд, после изменнения его свойств
     */
    public void saveRealUser(User user){
        
        Statement statement =null;
        String sqlStatement;

//        UPDATE  `taskmanager`.`users` SET  `name` =  'Герасимов Вениамин1',
//        `loginname` =  'venya1',
//        `group` =  '2',
//        `rights` =  '1',
//        `location` =  'у коридорi jjjj1',
//        `phone` =  '1011' WHERE  `users`.`id` =5;
        
        sqlStatement = "UPDATE  `taskmanager`.`users` SET"; 
        sqlStatement += "`name` =  '"+user.getName()+"'";
        sqlStatement += "`group` =  '"+user.getGroupId()+"'";
        sqlStatement += "`rights` =  '"+user.getRights()+"'";
        sqlStatement += "`location` =  '"+user.getLocation()+"'";
        sqlStatement += "`phone` =  '"+user.getPhone()+"'";
        sqlStatement += "WHERE  `users`.`id` = "+user.getUserID();
        
        
        try {
            Class.forName(DATA_BASE_DRIVER);
            //создать соединение с базой
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            //создать выражение
            statement = connection.createStatement();
            //выполнить выражение
            //statement.executeQuery(sqlStatement);
            statement.executeUpdate(sqlStatement);
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                //закрыть выражение
                if (statement != null) statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    
    
    /**
     * обнавление заявки
     */
    public void updateTask(Task task){
        
        Statement statement = null;
        String sqlStatement;

//        UPDATE  `taskmanager`.`users` SET  `name` =  'Герасимов Вениамин1',
//        `loginname` =  'venya1',
//        `group` =  '2',
//        `rights` =  '1',
//        `location` =  'у коридорi jjjj1',
//        `phone` =  '1011' WHERE  `users`.`id` =5;
        
        sqlStatement = "UPDATE  `taskmanager`.`demends` SET "; 
        sqlStatement += "`reciver` =  '"+task.getReciver()+"', ";
        sqlStatement += "`comments` =  '"+task.getComment()+"' ,";
        sqlStatement += "`closedate` =  '"+task.getCloseDateString()+"' ";
        sqlStatement += " WHERE  `demends`.`id` = "+task.getId();
        
        
        try {
            Class.forName(DATA_BASE_DRIVER);
            //создать соединение с базой
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            //создать выражение
            statement = connection.createStatement();
            //выполнить выражение
            //statement.executeQuery(sqlStatement);
            statement.executeUpdate(sqlStatement);
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                //закрыть выражение
                if (statement != null) statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    
}


