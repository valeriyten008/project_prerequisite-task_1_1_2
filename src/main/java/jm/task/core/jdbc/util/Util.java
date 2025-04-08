package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/example_schema";
    private static final String USER = "root";
    private static final String PASSWORD = "5854732sirenaA!";
    private static final Logger logger = Logger.getLogger(Util.class.getName());

    private static SessionFactory sessionFactory;

    private Util() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Программная конфигурация Hibernate
                Properties settings = new Properties();
                settings.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                settings.setProperty("hibernate.connection.url", URL);
                settings.setProperty("hibernate.connection.username", USER);
                settings.setProperty("hibernate.connection.password", PASSWORD);
                settings.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
                settings.setProperty("hibernate.show_sql", "true");
                settings.setProperty("hibernate.hbm2ddl.auto", "update");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());

                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Ошибка при создании SessionFactory", e);
                throw new RuntimeException("Hibernate SessionFactory creation failed", e);
            }
        }
        return sessionFactory;
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при подключении к базе данных", e);
            throw new RuntimeException("Ошибка при подключении к базе данных", e);
        }
    }
}


