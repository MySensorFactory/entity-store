package com.factory.persistence.repository;

import com.factory.persistence.entity.MeanPressure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface MeanPressureRepository extends CrudRepository<MeanPressure, UUID> {

    Optional<MeanPressure> findByAuditData_EventKeyAndAuditData_Label(String eventKey, String label);
}
