package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Util.getSessionFactory();

        UserDaoHibernateImpl userDaoHibernate= new UserDaoHibernateImpl();

        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("Ivan", "Petrow", (byte) 17);
        userDaoHibernate.saveUser("Pavel", "Pavlov", (byte) 17);
        userDaoHibernate.saveUser("Andrey", "Andreev", (byte) 17);
        userDaoHibernate.saveUser("Vasiliy", "Vasiliev", (byte) 17);

        userDaoHibernate.getAllUsers();
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();

    }
}
