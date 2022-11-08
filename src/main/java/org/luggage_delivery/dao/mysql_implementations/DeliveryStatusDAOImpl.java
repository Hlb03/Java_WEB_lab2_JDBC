package org.luggage_delivery.dao.mysql_implementations;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 12:07
*/

import org.luggage_delivery.dao.DeliveryStatusDAO;
import org.luggage_delivery.data_source.HikariCPDataSource;
import org.luggage_delivery.entity.DeliveryStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.luggage_delivery.util.DAOUtil.closeResources;

public class DeliveryStatusDAOImpl implements DeliveryStatusDAO {

    @Override
    public void addDeliveryStatus(DeliveryStatus status) {
        final String ADD_DELIVERY_STATUS = "INSERT INTO `delivery_status` (`status_name`) VALUE (?)";
        PreparedStatement preparedStatement = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = con.prepareStatement(ADD_DELIVERY_STATUS);
            preparedStatement.setString(1, status.getStatusName());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, null);
        }
    }

    @Override
    public DeliveryStatus getById(int id) {
        final String GET_DELIVERY_STATUS_BY_ID = "SELECT * FROM `delivery_status` WHERE `id` = ?";
        DeliveryStatus status = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {
            preparedStatement = con.prepareStatement(GET_DELIVERY_STATUS_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            while (rs.next())
                status = setDeliveryStatusParams(rs);
        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return status;
    }

    @Override
    public DeliveryStatus getByStatusName(String statusName) {
        final String GET_DELIVERY_STATUS_BY_NAME = "SELECT * FROM `delivery_status` WHERE `status_name` = ?";
        DeliveryStatus status = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
        ) {
            preparedStatement = con.prepareStatement(GET_DELIVERY_STATUS_BY_NAME);
            preparedStatement.setString(1, statusName);
            rs = preparedStatement.executeQuery();

            while (rs.next())
                status = setDeliveryStatusParams(rs);
        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return status;
    }

    @Override
    public List<DeliveryStatus> getAllStatuses() {
        final String GET_ALL_DELIVERY_STATUSES = "SELECT * FROM `delivery_status` ORDER BY 1";
        List<DeliveryStatus> statuses = new LinkedList<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {
            preparedStatement = con.prepareStatement(GET_ALL_DELIVERY_STATUSES);
            rs = preparedStatement.executeQuery();

            while (rs.next())
                statuses.add(setDeliveryStatusParams(rs));
        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return statuses;
    }

    @Override
    public void updateDeliveryStatus(DeliveryStatus status) {
        final String UPDATE_DELIVERY_STATUS = "UPDATE `delivery_status` SET `status_name` = ? WHERE `id` = ?";
        PreparedStatement preparedStatement = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {
            preparedStatement = con.prepareStatement(UPDATE_DELIVERY_STATUS);
            preparedStatement.setString(1, status.getStatusName());
            preparedStatement.setInt(2, status.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, null);
        }

    }

    @Override
    public void deleteDeliveryStatus(DeliveryStatus status) {
        final String DELETE_DELIVERY_STATUS = "DELETE FROM `delivery_status` WHERE `id` = ?";
        PreparedStatement preparedStatement = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {
            preparedStatement = con.prepareStatement(DELETE_DELIVERY_STATUS);
            preparedStatement.setInt(1, status.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, null);
        }

    }

    private DeliveryStatus setDeliveryStatusParams(ResultSet rs) throws SQLException {
        DeliveryStatus status = new DeliveryStatus();
        status.setId(rs.getInt(1));
        status.setStatusName(rs.getString(2));

        return status;
    }
}
