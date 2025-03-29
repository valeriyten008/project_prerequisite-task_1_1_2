package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoHibernateImpl implements UserDao {

    Logger logger = Logger.getLogger(UserDaoHibernateImpl.class.getName());

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sqlCommands = "CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "lastName VARCHAR(100), " +
                    "age TINYINT)";
            session.createNativeQuery(sqlCommands).executeUpdate();
            transaction.commit();
            logger.info("Table was created");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in table creating", e);
        }
    }

    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sqlCommands = "DROP TABLE IF EXISTS users";
            session.createNativeQuery(sqlCommands).executeUpdate();
            transaction.commit();
            logger.info("Table was removed");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in table dropping", e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            logger.info("User was added: " + name + " " + lastName);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding user", e);
        }
    }

    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                logger.info("User with ID " + id + " was removed");
            }
            transaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting user", e);
        }
    }

    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            List<User> users = session.createQuery("FROM User", User.class).list();
            logger.info("Returned all users");
            return users;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting all users", e);
            return null;
        }
    }

    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
            logger.info("All users removed");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error cleaning users", e);
        }
    }
}

