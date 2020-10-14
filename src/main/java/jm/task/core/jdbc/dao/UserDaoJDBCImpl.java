package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    Util util = new Util();
    public void createUsersTable() {
        try(Statement statement = util.getConnection().createStatement();){
            util.getConnection().setAutoCommit(false);
            String SQLCreate = "CREATE TABLE IF NOT EXISTS user "+
                    "(id BIGINT(30) AUTO_INCREMENT, " +
                    "name VARCHAR(30),"+
                    "lastName VARCHAR(60),"+
                    "age TINYINT,"+
                    "PRIMARY KEY (id))";
            statement.executeUpdate(SQLCreate);
            util.getConnection().commit();
        }
        catch(Exception ex){
            ex.printStackTrace();
            try {
                util.getConnection().rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try(Statement statement = util.getConnection().createStatement()){
            util.getConnection().setAutoCommit(false);
            String SQLDrop = "DROP TABLE IF EXISTS user";
            statement.executeUpdate(SQLDrop);
            util.getConnection().commit();
        }
        catch(Exception ex){
            ex.printStackTrace();
            try {
                util.getConnection().rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Statement statement = util.getConnection().createStatement();){
            util.getConnection().setAutoCommit(false);
            String SQLSave = "insert into user(name, lastName, age) values ('" + name + "'" +"," + "'" + lastName + "'"+ "," + age + ")";
            statement.executeUpdate(SQLSave);
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            util.getConnection().commit();
        }
        catch(Exception ex){
            ex.printStackTrace();
            try {
                util.getConnection().rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        try(Statement statement = util.getConnection().createStatement();){
            util.getConnection().setAutoCommit(false);
            String SQLRemove = "delete from user where id = id";
            statement.executeUpdate(SQLRemove);
            util.getConnection().commit();
        }
        catch(Exception ex){
            ex.printStackTrace();
            try {
                util.getConnection().rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try(Statement statement = util.getConnection().createStatement();){
            util.getConnection().setAutoCommit(false);
            String SQLGet = "select * from user";
            ResultSet resultSet = statement.executeQuery(SQLGet);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
            util.getConnection().commit();
        }
        catch(Exception ex){
            ex.printStackTrace();
            try {
                util.getConnection().rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        try(Statement statement = util.getConnection().createStatement();){
            util.getConnection().setAutoCommit(false);
            String SQLRemove = "delete from user";
            statement.executeUpdate(SQLRemove);
            util.getConnection().commit();
        }
        catch(Exception ex){
            ex.printStackTrace();
            try {
                util.getConnection().rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}