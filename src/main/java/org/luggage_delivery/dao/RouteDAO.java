package org.luggage_delivery.dao;

import org.luggage_delivery.entity.Route;

import java.util.List;

public interface RouteDAO {
    void addRoute(Route route);
    Route getById(int id);
    Route getByRoute(String start, String destination);
    List<Route> getAllRoutes();
    void updateRoute(Route route);
    void deleteRoute(Route route);
}
