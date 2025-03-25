package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {
    Logger logger = Logger.getLogger(UserDaoJDBCImpl.class.getName());

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlCommands = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "gitlastName VARCHAR(100), " +
                "age TINYINT)";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommands);
            logger.info("Table was created");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error in table creating",e);
        }

    }

    public void dropUsersTable() {
        String sqlCommands = "DROP TABLE IF EXISTS";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommands);
            logger.info("Table was removed");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error in dropping users table");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCommands = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement presStatement = connection.prepareStatement(sqlCommands)) {
            presStatement.setString(1, name);
            presStatement.setString(2, lastName);
            presStatement.setByte(3, age);
            presStatement.executeUpdate();
            logger.info("User was added: " +  name + " " + lastName);
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error adding user",e);
        }
    }

        public void removeUserById (long id){
            String sqlCommands = "DELETE FROM users WHERE id = ?";
            try (Connection connection = Util.getConnection();
                 PreparedStatement presStatement = connection.prepareStatement(sqlCommands)) {
                presStatement.setLong(1, id);
                presStatement.executeUpdate();
                logger.info("User was removed");
            } catch (SQLException e) {
                logger.log(Level.SEVERE,"Error deleting user",e);
            }
        }

        public List<User> getAllUsers () {
            List<User> users = new ArrayList<>();
            String sqlCommands = "SELECT * FROM users";
            try (Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommands)){
                while (resultSet.next()){
                    User user =  new User(
                            resultSet.getString("name"),
                            resultSet.getString("lastname"),
                            resultSet.getByte("age"));
                    user.setId(resultSet.getLong("id"));
                    users.add(user);
                }
                logger.info("Returned all users");
            } catch (SQLException e){
                logger.log(Level.SEVERE,"Error returing all users",e);
            }
            return users;
        }

        public void cleanUsersTable () {
            String sqlCommands = "TRUNCATE TABLE users";
            try (Connection connection = Util.getConnection();
                 Statement statement = connection.createStatement()){
                statement.executeUpdate(sqlCommands);
                logger.info("All users removed");
            } catch (SQLException e) {
                logger.log(Level.SEVERE,"Error cleaning users",e);
            }
    }
}
