package com.factory.persistence.repository;

import com.factory.persistence.entity.MeanTemperature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface MeanTemperatureRepository extends CrudRepository<MeanTemperature, UUID> {
}
