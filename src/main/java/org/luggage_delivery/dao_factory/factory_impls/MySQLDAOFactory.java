package org.luggage_delivery.dao_factory.factory_impls;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 17:54
*/

import org.luggage_delivery.dao_factory.DAOFactory;
import org.luggage_delivery.dao.*;
import org.luggage_delivery.dao.mysql_implementations.*;

public class MySQLDAOFactory implements DAOFactory {
    @Override
    public DeliveryDAO getDeliveryDAO() {
        return new DeliveryDAOImpl();
    }

    @Override
    public DeliveryStatusDAO getDeliveryStatusDAO() {
        return new DeliveryStatusDAOImpl();
    }

    @Override
    public RoleDAO getRoleDAO() {
        return new RoleDAOImpl();
    }

    @Override
    public RouteDAO getRouteDAO() {
        return new RouteDAOImpl();
    }

    @Override
    public TariffDAO getTariffDAO() {
        return new TariffDAOImpl();
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }
}
