package org.luggage_delivery.entity;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 5:35
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    private int id;
    private String startPoint;
    private String destination;
    private double distance;
}
