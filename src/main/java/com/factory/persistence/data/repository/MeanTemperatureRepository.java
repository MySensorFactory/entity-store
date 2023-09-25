package com.factory.persistence.data.repository;

import com.factory.persistence.data.entity.MeanTemperature;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Repository
public interface MeanTemperatureRepository extends CrudRepository<MeanTemperature, UUID> {

    @Query("SELECT mt FROM MeanTemperature mt WHERE mt.auditData.timestamp > :from " +
            "AND mt.auditData.timestamp < :to " +
            "AND mt.auditData.label = :label ")
    List<MeanTemperature> findByTimeWindowAndLabel(String label, ZonedDateTime from, ZonedDateTime to);
}
