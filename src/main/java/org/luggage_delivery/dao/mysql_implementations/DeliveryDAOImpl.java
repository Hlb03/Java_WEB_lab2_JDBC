package org.luggage_delivery.dao.mysql_implementations;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 12:37
*/

import org.luggage_delivery.dao.DeliveryDAO;
import org.luggage_delivery.data_source.HikariCPDataSource;
import org.luggage_delivery.entity.Delivery;
import org.luggage_delivery.entity.DeliveryStatus;
import org.luggage_delivery.entity.Route;
import org.luggage_delivery.entity.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static org.luggage_delivery.util.DAOUtil.closeResources;

public class DeliveryDAOImpl implements DeliveryDAO {

    @Override
    public void addDelivery(Delivery delivery) {
        final String ADD_DELIVERY = "INSERT INTO `delivery` (`luggage_size`, `total_price`, `luggage_type`, `weight`, " +
                "`start_date`, `delivery_date`, `delivery_address`, `delivery_status_id`, `routes_id`, `user_id`) " +
                "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = setParamsForPreparedStatement(ADD_DELIVERY, con, delivery);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, null);
        }
    }

    @Override
    public Delivery getById(int id) {
        final String GET_DELIVERY_BY_ID = "SELECT * FROM `delivery` WHERE `id` = ?";
        Delivery delivery = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = con.prepareStatement(GET_DELIVERY_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            while (rs.next())
                delivery = setDeliveryParams(rs, "id");

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return delivery;
    }

    @Override
    public Delivery getByStatus(String status) {
        final String GET_DELIVERY_BY_STATUS_NAME = "SELECT delivery.id, luggage_size, total_price, luggage_type, weight, start_date," +
                "delivery_date, delivery_address, status_name, routes_id, user_id " +
                "FROM `delivery` INNER JOIN `delivery_status` ON " +
                "`delivery`.`delivery_status_id` = `delivery_status`.`id` WHERE `status_name` = ?";
        Delivery delivery = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
        ) {

            preparedStatement = con.prepareStatement(GET_DELIVERY_BY_STATUS_NAME);
            preparedStatement.setString(1, status);
            rs = preparedStatement.executeQuery();

            while (rs.next())
                delivery = setDeliveryParams(rs, "statusName");

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return delivery;
    }

    @Override
    public List<Delivery> getByDeliveryDate(Date date) {
        final String GET_DELIVERIES_BY_DATE = "SELECT * FROM `delivery` WHERE `delivery_date` = ? ORDER BY 1";
        List<Delivery> deliveries = new LinkedList<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = con.prepareStatement(GET_DELIVERIES_BY_DATE);
            preparedStatement.setDate(1, date);
            rs = preparedStatement.executeQuery();

            while (rs.next())
                deliveries.add(setDeliveryParams(rs, "id"));

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return deliveries;
    }

    @Override
    public List<Delivery> getByRoute(Route route) {
        final String GET_DELIVERIES_BY_ROUTE = "SELECT * FROM `delivery` WHERE `routes_id` = ? ORDER BY 1";
        List<Delivery> deliveries = new LinkedList<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
        ) {

            preparedStatement = con.prepareStatement(GET_DELIVERIES_BY_ROUTE);
            preparedStatement.setInt(1, route.getId());
            rs = preparedStatement.executeQuery();

            while (rs.next())
                deliveries.add(setDeliveryParams(rs, "id"));

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return deliveries;
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        final String GET_ALL_DELIVERIES = "SELECT * FROM `delivery` ORDER BY 1";
        List<Delivery> deliveries = new LinkedList<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = con.prepareStatement(GET_ALL_DELIVERIES);
            rs = preparedStatement.executeQuery();

            while (rs.next())
                deliveries.add(setDeliveryParams(rs, "id"));
        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return deliveries;
    }

    @Override
    public void updateDelivery(Delivery delivery) {
        final String UPDATE_DELIVERY = "UPDATE `delivery` SET `luggage_size` = ?, `total_price` = ?, " +
                "`luggage_type` = ?, `weight` = ?, `start_date` = ?, `delivery_date` = ?, `delivery_address` = ?, " +
                "`delivery_status_id` = ?, `routes_id` = ?, `user_id` = ? WHERE `id` = ?";
        PreparedStatement preparedStatement = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {
            preparedStatement = setParamsForPreparedStatement(UPDATE_DELIVERY, con, delivery);
            preparedStatement.setInt(11, delivery.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, null);
        }
    }

    @Override
    public void deleteDelivery(Delivery delivery) {
        final String DELETE_DELIVERY = "DELETE FROM `delivery` WHERE `id` = ?";
        PreparedStatement preparedStatement = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = con.prepareStatement(DELETE_DELIVERY);
            preparedStatement.setInt(1, delivery.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, null);
        }
    }

    private Delivery setDeliveryParams(ResultSet rs, String typeOfDeliveryStatusInit) throws SQLException {
        Delivery delivery = new Delivery();
        delivery.setId(rs.getInt(1));
        delivery.setLuggageSize(rs.getDouble(2));
        delivery.setTotalPrice(rs.getBigDecimal(3));
        delivery.setLuggageType(rs.getString(4));
        delivery.setWeight(rs.getDouble(5));
        delivery.setStartDate(rs.getDate(6));
        delivery.setDeliveryDate(rs.getDate(7));
        delivery.setDeliveryAddress(rs.getString(8));

        DeliveryStatus status;
        if (typeOfDeliveryStatusInit.equals("id"))
            status = setDeliveryStatusById(rs);
        else status = setDeliveryStatusByStatusName(rs);

        Route route = new Route();
        route.setId(rs.getInt(10));
        User user = new User();
        user.setId(rs.getInt(11));

        delivery.setStatus(status);
        delivery.setRoute(route);
        delivery.setUser(user);

        return delivery;
    }

    private PreparedStatement setParamsForPreparedStatement(String query, Connection con, Delivery delivery) throws  SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setDouble(1, delivery.getLuggageSize());
        preparedStatement.setBigDecimal(2, delivery.getTotalPrice());
        preparedStatement.setString(3, delivery.getLuggageType());
        preparedStatement.setDouble(4, delivery.getWeight());
        preparedStatement.setDate(5, delivery.getStartDate());
        preparedStatement.setDate(6, delivery.getDeliveryDate());
        preparedStatement.setString(7, delivery.getDeliveryAddress());
        preparedStatement.setInt(8, delivery.getStatus().getId());
        preparedStatement.setInt(9, delivery.getRoute().getId());
        preparedStatement.setInt(10, delivery.getUser().getId());

        return preparedStatement;
    }

    private DeliveryStatus setDeliveryStatusById(ResultSet rs) throws SQLException {
        DeliveryStatus status = new DeliveryStatus();
        status.setId(rs.getInt(9));

        return status;
    }

    private DeliveryStatus setDeliveryStatusByStatusName(ResultSet rs) throws SQLException {
        DeliveryStatus status = new DeliveryStatus();
        status.setStatusName(rs.getString(9));

        return status;
    }
}
