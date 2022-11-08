package org.luggage_delivery.dao;

import org.luggage_delivery.entity.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
    User getById(int id);
    User getByLogin(String login);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(User user);
}
