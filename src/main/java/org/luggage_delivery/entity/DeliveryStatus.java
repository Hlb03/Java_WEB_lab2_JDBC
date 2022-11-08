package org.luggage_delivery.entity;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 5:36
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryStatus {
    private int id;
    private String statusName;
}
