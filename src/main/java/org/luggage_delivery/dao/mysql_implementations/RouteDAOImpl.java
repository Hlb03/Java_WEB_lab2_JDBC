package org.luggage_delivery.dao.mysql_implementations;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 11:36
*/

import org.luggage_delivery.dao.RouteDAO;
import org.luggage_delivery.data_source.HikariCPDataSource;
import org.luggage_delivery.entity.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.luggage_delivery.util.DAOUtil.closeResources;

public class RouteDAOImpl implements RouteDAO {

    @Override
    public void addRoute(Route route) {
        final String ADD_ROUTE = "INSERT INTO `route` (`start_point`, `destination_point`, `distance`) " +
                "VALUE (?, ?, ?)";
        PreparedStatement preparedStatement = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {
            preparedStatement = con.prepareStatement(ADD_ROUTE);
            preparedStatement.setString(1, route.getStartPoint());
            preparedStatement.setString(2, route.getDestination());
            preparedStatement.setDouble(3, route.getDistance());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, null);
        }
    }

    @Override
    public Route getById(int id) {
        final String GET_ROUTE_BY_ID = "SELECT * FROM `route` WHERE `id` = ?";
        Route route = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {
            preparedStatement = con.prepareStatement(GET_ROUTE_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            while (rs.next())
                route = setRouteParams(rs);
        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return route;
    }

    @Override
    public Route getByRoute(String start, String destination) {
        final String GET_ROUTE_BY_ID = "SELECT * FROM `route` WHERE `start_point` = ? AND `destination_point` = ?";
        Route route = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
        ) {
            preparedStatement = con.prepareStatement(GET_ROUTE_BY_ID);
            preparedStatement.setString(1, start);
            preparedStatement.setString(2, destination);
            rs = preparedStatement.executeQuery();

            while (rs.next())
                route = setRouteParams(rs);
        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return route;
    }

    @Override
    public List<Route> getAllRoutes() {
        final String GET_ALL_ROUTES = "SELECT * FROM `route` ORDER BY 1";
        List<Route> routes = new LinkedList<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = con.prepareStatement(GET_ALL_ROUTES);
            rs = preparedStatement.executeQuery();
            while (rs.next())
                routes.add(setRouteParams(rs));

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }
        return routes;
    }

    @Override
    public void updateRoute(Route route) {
        final String UPDATE_ROUTE = "UPDATE `route` SET `start_point` = ?, `destination_point` = ?, " +
                "`distance` = ? WHERE `id` = ?";
        PreparedStatement preparedStatement = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {
            preparedStatement = con.prepareStatement(UPDATE_ROUTE);
            preparedStatement.setString(1, route.getStartPoint());
            preparedStatement.setString(2, route.getDestination());
            preparedStatement.setDouble(3, route.getDistance());
            preparedStatement.setInt(4, route.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, null);
        }
    }

    @Override
    public void deleteRoute(Route route) {
        final String DELETE_ROUTE = "DELETE FROM `route` WHERE `id` = ?";
        PreparedStatement preparedStatement = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {
            preparedStatement = con.prepareStatement(DELETE_ROUTE);
            preparedStatement.setInt(1, route.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, null);
        }
    }

    private Route setRouteParams(ResultSet rs) throws SQLException {
        Route route = new Route();
        route.setId(rs.getInt(1));
        route.setStartPoint(rs.getString(2));
        route.setDestination(rs.getString(3));
        route.setDistance(rs.getDouble(4));

        return route;
    }
}
