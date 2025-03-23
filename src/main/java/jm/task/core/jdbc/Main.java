package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
        public static void main(String[] args) {
            UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();

            userDao.createUsersTable();

            userDao.saveUser("Valeriy", "Ten", (byte) 25);
            userDao.saveUser("Steve", "Pops", (byte) 30);
            userDao.saveUser("Alex", "Heg", (byte) 28);
            userDao.saveUser("Lena", "Ten", (byte) 22);

            List<User> users = userDao.getAllUsers();
            for (User user : users) {
                System.out.println(user.toString());
            }

            userDao.cleanUsersTable();

            userDao.dropUsersTable();

        }
}
