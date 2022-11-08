package org.luggage_delivery.dao_factory;

import org.luggage_delivery.dao.*;

public interface DAOFactory {
    DeliveryDAO getDeliveryDAO();
    DeliveryStatusDAO getDeliveryStatusDAO();
    RoleDAO getRoleDAO();
    RouteDAO getRouteDAO();
    TariffDAO getTariffDAO();
    UserDAO getUserDAO();
}
