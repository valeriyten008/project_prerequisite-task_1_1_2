package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoJDBCImpl userDaoJDBSImpl = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDaoJDBSImpl.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoJDBSImpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBSImpl.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoJDBSImpl.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoJDBSImpl.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoJDBSImpl.cleanUsersTable();
    }
}
