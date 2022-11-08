package org.luggage_delivery.dao.mysql_implementations;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 9:04
*/

import org.luggage_delivery.dao.UserDAO;
import org.luggage_delivery.data_source.HikariCPDataSource;
import org.luggage_delivery.entity.Role;
import org.luggage_delivery.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.luggage_delivery.util.DAOUtil.closeResources;

public class UserDAOImpl implements UserDAO {
    @Override
    public void addUser(User user) {
        final String ADD_USER = "INSERT INTO `user` (`first_name`, `last_name`, `login`, `password`, `balance`, `role_id`)" +
                " VALUE (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = setParamsForPreparedStatement(ADD_USER, con, user);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, null);
        }
    }

    @Override
    public User getById(int id) {
        final String GET_USER_BY_ID = "SELECT * FROM `user` WHERE `id` = ?";
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = con.prepareStatement(GET_USER_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            while (rs.next())
                user = setUserParams(rs);

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return user;
    }

    @Override
    public User getByLogin(String login) {
        final String GET_USER_BY_LOGIN = "SELECT * FROM `user` WHERE `login` = ?";
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = con.prepareStatement(GET_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            rs = preparedStatement.executeQuery();
            while (rs.next())
                user = setUserParams(rs);

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        final String GET_ALL_USERS = "SELECT * FROM `user` ORDER BY `id`";
        List<User> users = new LinkedList<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {
            preparedStatement = con.prepareStatement(GET_ALL_USERS);
            rs = preparedStatement.executeQuery();
            while (rs.next())
                users.add(setUserParams(rs));

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return users;
    }

    @Override
    public void updateUser(User user) {
        final String UPDATE_USER = "UPDATE `user` SET `first_name` = ?,`last_name` = ?, " +
                "`login` = ?, `password` = ?, `balance` = ?, `role_id` = ? WHERE `id` = ?";
        PreparedStatement preparedStatement = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {
            preparedStatement = setParamsForPreparedStatement(UPDATE_USER, con, user);
            preparedStatement.setInt(7, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, null);
        }
    }

    @Override
    public void deleteUser(User user) {
        final String DELETE_USER = "DELETE FROM `user` WHERE `id` = ?";
        PreparedStatement preparedStatement = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {
            preparedStatement = con.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, null);
        }
    }

    private User setUserParams(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(1));
        user.setFirstName(rs.getString(2));
        user.setLastName(rs.getString(3));
        user.setLogin(rs.getString(4));
        user.setPassword(rs.getString(5));
        user.setBalance(rs.getBigDecimal(6));

        Role role = new Role();
        role.setId(rs.getInt(7));
        user.setRole(role);

        return user;
    }

    private PreparedStatement setParamsForPreparedStatement(String query, Connection con, User user)
                                                                    throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getLogin());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setBigDecimal(5, user.getBalance());
        preparedStatement.setInt(6, user.getRole().getId());

        return preparedStatement;
    }
}
