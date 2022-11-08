package org.luggage_delivery.entity;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 5:37
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    private int id;
    private double luggageSize;
    private BigDecimal totalPrice;
    private String luggageType;
    private double weight;
    private Date startDate;
    private Date deliveryDate;
    private String deliveryAddress;

    private DeliveryStatus status;
    private Route route;
    private User user;
}
