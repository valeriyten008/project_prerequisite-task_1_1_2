package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl(); // Изменено имя переменной

        userService.createUsersTable();

        userService.saveUser("Valeriy", "Ten", (byte) 25);
        userService.saveUser("Steve", "Pops", (byte) 30);
        userService.saveUser("Alex", "Heg", (byte) 28);
        userService.saveUser("Lena", "Ten", (byte) 22);

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
