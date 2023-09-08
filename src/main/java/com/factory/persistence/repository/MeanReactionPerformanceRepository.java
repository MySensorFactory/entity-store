package com.factory.persistence.repository;

import com.factory.persistence.entity.MeanReactionPerformance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface MeanReactionPerformanceRepository extends CrudRepository<MeanReactionPerformance, UUID> {
}
