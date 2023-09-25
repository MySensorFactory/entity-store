package com.factory.persistence.data.repository;

import com.factory.persistence.data.entity.MeanPressure;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface MeanPressureRepository extends CrudRepository<MeanPressure, UUID> {

    Optional<MeanPressure> findByAuditData_EventKeyAndAuditData_Label(String eventKey, String label);


    @Query("SELECT mp FROM MeanPressure mp WHERE mp.auditData.timestamp > :from " +
            "AND mp.auditData.timestamp < :to " +
            "AND mp.auditData.label = :label ")
    List<MeanPressure> findByTimeWindowAndLabel(String label, ZonedDateTime from, ZonedDateTime to);
}
