package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Liza", "Glaz", (byte) 22);
        userService.saveUser("Dasha", "Gal", (byte) 23);
        userService.saveUser("Anna", "Dem", (byte) 19);
        userService.saveUser("Olga", "Yar", (byte) 30);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
