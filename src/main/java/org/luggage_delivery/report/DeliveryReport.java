package org.luggage_delivery.report;

public interface DeliveryReport {

    //FACTORY METHOD GoF pattern
    void createReport(Object reportObject);
}
