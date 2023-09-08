package com.factory.persistence.repository;

import com.factory.persistence.entity.MeanGasComposition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface MeanGasCompositionRepository extends CrudRepository<MeanGasComposition, UUID> {
}
