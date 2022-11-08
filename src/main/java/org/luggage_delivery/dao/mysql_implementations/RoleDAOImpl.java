package org.luggage_delivery.dao.mysql_implementations;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 6:35
*/

import org.luggage_delivery.dao.RoleDAO;
import org.luggage_delivery.data_source.HikariCPDataSource;
import org.luggage_delivery.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.luggage_delivery.util.DAOUtil.closeResources;

public class RoleDAOImpl implements RoleDAO {

    @Override
    public void addRole(String roleName) {
        final String ADD_NEW_ROLE = "INSERT INTO `role` (`role_name`) VALUE ?";

        PreparedStatement preparedStatement = null;
        try (
                Connection con = HikariCPDataSource.getConnection()
        ) {

            preparedStatement = con.prepareStatement(ADD_NEW_ROLE);
            preparedStatement.setString(1, roleName);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, null);
        }
    }

    @Override
    public Role getById(int id) {
        final String GET_ROLE_BY_ID = "SELECT * FROM `role` WHERE `id` = ?";
        Role role = null;

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = con.prepareStatement(GET_ROLE_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            while (rs.next())
                role = setRoleParams(rs);

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return role;
    }

    @Override
    public Role getByName(String name) {
        final String GET_ROLE_BY_NAME = "SELECT * FROM `role` WHERE `role_name` = ?";
        Role role = null;

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = con.prepareStatement(GET_ROLE_BY_NAME);
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();

            while (rs.next())
                role = setRoleParams(rs);

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return role;
    }

    @Override
    public List<Role> getAllRoles() {
        final String GET_ALL_ROLES = "SELECT * FROM `role` ORDER BY `id`";
        List<Role> roles = new LinkedList<>();

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = con.prepareStatement(GET_ALL_ROLES);
            rs = preparedStatement.executeQuery();

            while (rs.next())
                roles.add(setRoleParams(rs));

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return roles;
    }

    @Override
    public void updateRole(Role role) {
        final String UPDATE_ROLE = "UPDATE `role` SET `role_name` = ? WHERE `id` = ?";
        PreparedStatement preparedStatement = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = con.prepareStatement(UPDATE_ROLE);
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setInt(2, role.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, null);
        }
    }

    @Override
    public void deleteRole(Role role) {
        final String DELETE_ROLE = "DELETE FROM `role` WHERE `id` = ?";
        PreparedStatement preparedStatement = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = con.prepareStatement(DELETE_ROLE);
            preparedStatement.setInt(1, role.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, null);
        }
    }

    private Role setRoleParams(ResultSet rs) throws SQLException {
        Role role = new Role();

        role.setId(rs.getInt(1));
        role.setRoleName(rs.getString(2));

        return role;
    }

}
