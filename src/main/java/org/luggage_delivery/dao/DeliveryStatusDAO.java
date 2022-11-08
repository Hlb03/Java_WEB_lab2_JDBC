package org.luggage_delivery.dao;

import org.luggage_delivery.entity.DeliveryStatus;

import java.util.List;

public interface DeliveryStatusDAO {
    void addDeliveryStatus(DeliveryStatus status);
    DeliveryStatus getById(int id);
    DeliveryStatus getByStatusName(String statusName);
    List<DeliveryStatus> getAllStatuses();
    void updateDeliveryStatus(DeliveryStatus status);
    void deleteDeliveryStatus(DeliveryStatus status);
}
