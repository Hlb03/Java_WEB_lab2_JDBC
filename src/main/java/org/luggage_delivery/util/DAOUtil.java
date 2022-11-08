package org.luggage_delivery.util;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 8:06
*/

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUtil {

    public static void closeResources(PreparedStatement preparedStatement, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) { ex.printStackTrace();}
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) { ex.printStackTrace();}
        }
    }
}
