package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session=null;
        try{
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            String SQLCreate = "CREATE TABLE IF NOT EXISTS user "+
                    "(id BIGINT(30) AUTO_INCREMENT, " +
                    "name VARCHAR(30),"+
                    "lastName VARCHAR(60),"+
                    "age TINYINT,"+
                    "PRIMARY KEY (id))";
            session.createSQLQuery(SQLCreate).executeUpdate();
            session.getTransaction().commit();
        }
        catch(Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session=null;
        try{
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            String SQLDrop = "DROP TABLE IF EXISTS user";
            session.createSQLQuery(SQLDrop).executeUpdate();
            session.getTransaction().commit();
        }
        catch(Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session=null;
        try{
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            session.getTransaction().commit();
        }
        catch(Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session=null;
        try{
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Session session=null;
        try{
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            //String SQLGet = "select * from user";
            // list = session.createSQLQuery(SQLGet).list();
            list = session.createCriteria(User.class).list();
            //list = session.createQuery("From " + User.class.getSimpleName()).list();
            session.getTransaction().commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        finally {
            session.close();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session=null;
        try{
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();

            session.getTransaction().commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        finally {
            session.close();
        }
    }
}
