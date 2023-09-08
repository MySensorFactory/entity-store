package com.factory.persistence.repository;

import com.factory.persistence.entity.CompressorState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface CompressorStateRepository extends CrudRepository<CompressorState, UUID> {
}
