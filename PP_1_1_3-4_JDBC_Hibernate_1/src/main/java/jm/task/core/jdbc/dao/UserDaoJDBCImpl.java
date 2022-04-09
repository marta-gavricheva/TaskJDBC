package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        String create = "CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastname VARCHAR(255), age INT);";


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(create);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }

    }


    public void dropUsersTable() {
        String drop = "DROP TABLE user";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(drop);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (name, lastname, age) VALUES (?,?,?);");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }


    }

    public void removeUserById(long id) throws SQLException {
        String delete = "DELETE FROM user WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String select = "SELECT * FROM user";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                userList.add(user);
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        String trunc = "TRUNCATE TABLE user";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(trunc);
            preparedStatement.executeUpdate(trunc);
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }
}

