package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
        public static void main(String[] args) {
            UserService userDao = new UserServiceImpl();

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
