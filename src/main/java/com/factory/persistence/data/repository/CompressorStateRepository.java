package com.factory.persistence.data.repository;

import com.factory.persistence.data.entity.CompressorState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Repository
public interface CompressorStateRepository extends CrudRepository<CompressorState, UUID> {

    @Query("SELECT cs FROM CompressorState cs WHERE cs.auditData.timestamp > :from " +
            "AND cs.auditData.timestamp < :to " +
            "AND cs.auditData.label = :label ")
    List<CompressorState> findByTimeWindowAndLabel(String label, ZonedDateTime from, ZonedDateTime to);

}
