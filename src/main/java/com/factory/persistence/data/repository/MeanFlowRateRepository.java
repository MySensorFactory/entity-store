package com.factory.persistence.data.repository;

import com.factory.persistence.data.entity.MeanFlowRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Repository
public interface MeanFlowRateRepository extends CrudRepository<MeanFlowRate, UUID> {

    @Query("SELECT fr FROM MeanFlowRate fr WHERE fr.auditData.timestamp > :from " +
            "AND fr.auditData.timestamp < :to " +
            "AND fr.auditData.label = :label ")
    List<MeanFlowRate> findByTimeWindowAndLabel(String label, ZonedDateTime from, ZonedDateTime to);
}
