package org.luggage_delivery;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 5:24
*/

import org.luggage_delivery.dao.*;
import org.luggage_delivery.dao_factory.DAOFactory;
import org.luggage_delivery.dao_factory.factory_impls.MySQLDAOFactory;
import org.luggage_delivery.entity.*;
import org.luggage_delivery.report.DeliveryReport;
import org.luggage_delivery.report.report_impls.DayReport;
import org.luggage_delivery.report.report_impls.RouteReport;

import java.math.BigDecimal;
import java.sql.Date;


public class Starter {
    public static void main(String[] args) {
        DAOFactory daoFactory = new MySQLDAOFactory();

        DeliveryStatusDAO deliveryStatusDAO = daoFactory.getDeliveryStatusDAO();
        RouteDAO routeDAO = daoFactory.getRouteDAO();
        UserDAO userDAO = daoFactory.getUserDAO();
        RoleDAO roleDAO = daoFactory.getRoleDAO();
        DeliveryDAO deliveryDAO = daoFactory.getDeliveryDAO();
        TariffDAO tariffDAO = daoFactory.getTariffDAO();

        Tariff tariff = new Tariff(5, "Some tariff", new BigDecimal("0.3"));
        Tariff tariff1 = new Tariff(100, "One more tariff", new BigDecimal("0.19"));

        tariffDAO.addTariff(tariff);
        tariffDAO.addTariff(tariff1);

        System.out.println(tariffDAO.getAllTariffs());

        Tariff updateTariff = tariffDAO.getByType("Some tariff");
        updateTariff.setType("HELLO WORLD!");

        tariffDAO.updateTariff(updateTariff);

        System.out.println(tariffDAO.getAllTariffs());

//        Route route1 = routeDAO.getById(3);
        Route route2 = routeDAO.getById(4);
//        DeliveryStatus status1 = deliveryStatusDAO.getById(2);
//        DeliveryStatus status2 = deliveryStatusDAO.getById(3);
//
//        User user1 = userDAO.getById(2);
//        User user2 = userDAO.getById(3);
//
        DeliveryReport dayReport = new DayReport(deliveryDAO);
        DeliveryReport routeReport = new RouteReport(deliveryDAO);
        dayReport.createReport(Date.valueOf("2022-10-25"));
        routeReport.createReport(route2);

//        deliveryDAO.addDelivery(new Delivery(1, 15.3, new BigDecimal("180.53"), "beauty", 0.11, Date.valueOf("2022-10-22"),
//                Date.valueOf("2022-10-26"), "str. Wolf 2/16", status1, route1, user1));
//        deliveryDAO.addDelivery(new Delivery(22, 190.32, new BigDecimal("240.95"), "building", 9.2, Date.valueOf("2022-10-22"),
//                Date.valueOf("2022-10-26"), "str. Green 15", status2, route2, user2));

//        System.out.println(deliveryDAO.getAllDeliveries());
    }
}