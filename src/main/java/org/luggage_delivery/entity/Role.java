package org.luggage_delivery.entity;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 5:13
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private int id;
    private String roleName;
}