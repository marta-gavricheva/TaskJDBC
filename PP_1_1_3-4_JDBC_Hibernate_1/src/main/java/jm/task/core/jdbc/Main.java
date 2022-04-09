package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Util.getConnection();

        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Ivan", "Petrow", (byte) 17);
        userDao.saveUser("Pavel", "Pavlov", (byte) 17);
        userDao.saveUser("Andrey", "Andreev", (byte) 17);
        userDao.saveUser("Vasiliy", "Vasiliev", (byte) 17);

        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }
}
