package org.luggage_delivery.dao.mysql_implementations;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 8:02
*/

import org.luggage_delivery.dao.TariffDAO;
import org.luggage_delivery.data_source.HikariCPDataSource;
import org.luggage_delivery.entity.Tariff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.luggage_delivery.util.DAOUtil.closeResources;

public class TariffDAOImpl implements TariffDAO {

    @Override
    public void addTariff(Tariff tariff) {
        final String ADD_TARIFF = "INSERT INTO `tariff` (`type`, `price`) VALUE (?, ?)";
        PreparedStatement preparedStatement = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = con.prepareStatement(ADD_TARIFF);
            preparedStatement.setString(1, tariff.getType());
            preparedStatement.setBigDecimal(2, tariff.getPrice());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) { ex.printStackTrace();
        } closeResources(preparedStatement, null);
    }

    @Override
    public Tariff getById(int id) {
        final String GET_TARIFF_BY_ID = "SELECT * FROM `tariff` WHERE `id` = ?";
        Tariff tariff = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = con.prepareStatement(GET_TARIFF_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next())
                tariff = setTariffParams(rs);

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return tariff;
    }

    @Override
    public Tariff getByType(String type) {
        final String GET_TARIFF_BY_TYPE = "SELECT * FROM `tariff` WHERE `type` = ?";
        Tariff tariff = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
        ) {

            preparedStatement = con.prepareStatement(GET_TARIFF_BY_TYPE);
            preparedStatement.setString(1, type);
            rs = preparedStatement.executeQuery();
            while (rs.next())
                tariff = setTariffParams(rs);

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return tariff;
    }

    @Override
    public List<Tariff> getAllTariffs() {
        final String GET_ALL_TARIFFS = "SELECT * FROM `tariff` ORDER BY `id`";
        List<Tariff> tariffs = new LinkedList<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {
            preparedStatement = con.prepareStatement(GET_ALL_TARIFFS);
            rs = preparedStatement.executeQuery();
            while (rs.next())
                tariffs.add(setTariffParams(rs));

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, rs);
        }

        return tariffs;
    }

    @Override
    public void updateTariff(Tariff tariff) {
        final String UPDATE_TARIFF = "UPDATE `tariff` SET `type` = ?, `price` = ? WHERE `id` = ?";
        PreparedStatement preparedStatement = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = con.prepareStatement(UPDATE_TARIFF);
            preparedStatement.setString(1, tariff.getType());
            preparedStatement.setBigDecimal(2, tariff.getPrice());
            preparedStatement.setInt(3, tariff.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, null);
        }
    }

    @Override
    public void deleteTariff(Tariff tariff) {
        final String DELETE_TARIFF = "DELETE FROM `tariff` WHERE `id` = ?";
        PreparedStatement preparedStatement = null;

        try (
                Connection con = HikariCPDataSource.getConnection()
            ) {

            preparedStatement = con.prepareStatement(DELETE_TARIFF);
            preparedStatement.setInt(1, tariff.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) { ex.printStackTrace();
        } finally {
            closeResources(preparedStatement, null);
        }

    }

    private Tariff setTariffParams(ResultSet rs) throws SQLException {
        Tariff tariff = new Tariff();
        tariff.setId(rs.getInt(1));
        tariff.setType(rs.getString(2));
        tariff.setPrice(rs.getBigDecimal(3));

        return tariff;
    }
}
