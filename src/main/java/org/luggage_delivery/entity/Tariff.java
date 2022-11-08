package org.luggage_delivery.entity;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 5:31
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tariff {
    private int id;
    private String type;
    private BigDecimal price;
}
