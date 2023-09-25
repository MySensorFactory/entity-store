package com.factory.persistence.data.repository;

import com.factory.persistence.data.entity.MeanGasComposition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Repository
public interface MeanGasCompositionRepository extends CrudRepository<MeanGasComposition, UUID> {

    @Query("SELECT gc FROM MeanGasComposition gc WHERE gc.auditData.timestamp > :from " +
            "AND gc.auditData.timestamp < :to " +
            "AND gc.auditData.label = :label ")
    List<MeanGasComposition> findByTimeWindowAndLabel(String label, ZonedDateTime from, ZonedDateTime to);
}
