package org.luggage_delivery.report.report_factory.diff_factories;
/*
  User: admin
  Cur_date: 25.10.2022
  Cur_time: 5:22
*/

import org.luggage_delivery.dao.DeliveryDAO;
import org.luggage_delivery.dao_factory.DAOFactory;
import org.luggage_delivery.report.DeliveryReport;
import org.luggage_delivery.report.report_factory.DeliveryReportFactory;
import org.luggage_delivery.report.report_impls.DayReport;

public class DayReportFactory implements DeliveryReportFactory {

    private final DeliveryDAO deliveryDAO;

    public DayReportFactory(DeliveryDAO deliveryDAO) {
        this.deliveryDAO = deliveryDAO;
    }

    @Override
    public DeliveryReport createDeliveryReport() {
        return new DayReport(deliveryDAO);
    }
}
