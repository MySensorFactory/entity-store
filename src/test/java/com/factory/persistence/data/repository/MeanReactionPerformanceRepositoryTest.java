package com.factory.persistence.data.repository;

import com.factory.common.JpaIntegrationTest;
import com.factory.persistence.data.entity.AuditData;
import com.factory.persistence.data.entity.MeanReactionPerformance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@JpaIntegrationTest
class MeanReactionPerformanceRepositoryTest {

    @Autowired
    private MeanReactionPerformanceRepository meanReactionPerformanceRepository;

    @Test
    void testSaveAndFindById() {
        MeanReactionPerformance meanReactionPerformance = createMeanReactionPerformance("TestLabel", 42.5);
        meanReactionPerformanceRepository.save(meanReactionPerformance);

        Optional<MeanReactionPerformance> foundMeanReactionPerformance = meanReactionPerformanceRepository.findById(meanReactionPerformance.getId());

        assertTrue(foundMeanReactionPerformance.isPresent());
        assertEquals(meanReactionPerformance.getValue(), foundMeanReactionPerformance.get().getValue());
        assertEquals(meanReactionPerformance.getAuditData().getLabel(), foundMeanReactionPerformance.get().getAuditData().getLabel());
    }

    private MeanReactionPerformance createMeanReactionPerformance(final String label, final Double value) {
        MeanReactionPerformance meanReactionPerformance = new MeanReactionPerformance();
        meanReactionPerformance.setValue(value);

        meanReactionPerformance.setAuditData(createAuditData(label));

        return meanReactionPerformance;
    }

    private AuditData createAuditData(final String label) {
        AuditData auditData = new AuditData();
        auditData.setEventKey(UUID.randomUUID().toString());
        auditData.setLabel(label);
        auditData.setTimestamp(ZonedDateTime.now());
        return auditData;
    }
}