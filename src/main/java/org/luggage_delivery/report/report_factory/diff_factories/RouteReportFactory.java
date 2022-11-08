package org.luggage_delivery.report.report_factory.diff_factories;
/*
  User: admin
  Cur_date: 25.10.2022
  Cur_time: 5:25
*/

import org.luggage_delivery.dao.DeliveryDAO;
import org.luggage_delivery.report.DeliveryReport;
import org.luggage_delivery.report.report_factory.DeliveryReportFactory;
import org.luggage_delivery.report.report_impls.RouteReport;

public class RouteReportFactory implements DeliveryReportFactory {

    private final DeliveryDAO deliveryDAO;

    public RouteReportFactory(DeliveryDAO deliveryDAO) {
        this.deliveryDAO = deliveryDAO;
    }

    @Override
    public DeliveryReport createDeliveryReport() {
        return new RouteReport(deliveryDAO);
    }
}
