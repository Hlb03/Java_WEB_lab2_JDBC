package org.luggage_delivery.dao;

import org.luggage_delivery.entity.Tariff;

import java.util.List;

public interface TariffDAO {
    void addTariff(Tariff tariff);
    Tariff getById(int id);
    Tariff getByType(String type);
    List<Tariff> getAllTariffs();
    void updateTariff(Tariff tariff);
    void deleteTariff(Tariff tariff);
}
