package com.factory.persistence.repository;

import com.factory.persistence.entity.MeanFlowRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface MeanFlowRateRepository extends CrudRepository<MeanFlowRate, UUID> {
}
