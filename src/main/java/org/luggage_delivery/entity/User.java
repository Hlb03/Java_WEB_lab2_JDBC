package org.luggage_delivery.entity;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 5:26
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private BigDecimal balance;

    private Role role;
}
