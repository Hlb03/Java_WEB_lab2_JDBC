package org.luggage_delivery.dao;

import org.luggage_delivery.entity.Role;

import java.util.List;

public interface RoleDAO {
    void addRole(String roleName);
    Role getById(int id);
    Role getByName(String name);
    List<Role> getAllRoles();
    void updateRole(Role role);
    void deleteRole(Role role);
}
