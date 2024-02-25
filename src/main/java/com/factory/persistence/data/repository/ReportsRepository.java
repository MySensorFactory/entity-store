package com.factory.persistence.data.repository;

import com.factory.persistence.data.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Repository
public interface ReportsRepository extends JpaRepository<Report, UUID> {

    @Query("SELECT r FROM Report r WHERE :from < r.from AND :to > r.to")
    List<Report> findAllByTimeWindow(ZonedDateTime from, ZonedDateTime to);
}
