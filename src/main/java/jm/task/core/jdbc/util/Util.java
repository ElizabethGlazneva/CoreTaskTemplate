package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import jm.task.core.jdbc.model.User;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/sheme1?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTCeSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "Galusha9Glukov9";

    private Connection connection;
    public Util(){
    }

    public Connection getConnection() {
        if(connection==null){
            try {
                connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                        .setProperty("hibernate.connection.url", URL)
                        .setProperty("hibernate.connection.username", USERNAME)
                        .setProperty("hibernate.connection.password", PASSWORD)

                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .setProperty("hibernate.show_sql", "true")
                        .setProperty("hibernate.current_session_context_class", "thread")
                        .addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
