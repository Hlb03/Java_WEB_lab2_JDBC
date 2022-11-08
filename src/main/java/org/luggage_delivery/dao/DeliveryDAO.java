package org.luggage_delivery.dao;

import org.luggage_delivery.entity.Delivery;
import org.luggage_delivery.entity.Route;

import java.sql.Date;
import java.util.List;

public interface DeliveryDAO {
    void addDelivery(Delivery delivery);
    Delivery getById(int id);
    Delivery getByStatus(String status);
    List<Delivery> getByDeliveryDate(Date date);
    List<Delivery> getByRoute(Route route);
    List<Delivery> getAllDeliveries();
    void updateDelivery(Delivery delivery);
    void deleteDelivery(Delivery delivery);
}
