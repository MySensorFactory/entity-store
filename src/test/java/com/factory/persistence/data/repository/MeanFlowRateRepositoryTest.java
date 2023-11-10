package com.factory.persistence.data.repository;

import com.factory.common.JpaIntegrationTest;
import com.factory.persistence.data.entity.AuditData;
import com.factory.persistence.data.entity.MeanFlowRate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JpaIntegrationTest
class MeanFlowRateRepositoryTest {
    @Autowired
    private MeanFlowRateRepository meanFlowRateRepository;

    @Test
    void testFindByTimeWindowAndLabel() {
        String label = "yourLabel";
        ZonedDateTime from = ZonedDateTime.parse("2023-01-01T00:00:00.000Z");
        ZonedDateTime to = ZonedDateTime.parse("2023-02-01T00:00:00.000Z");

        List<MeanFlowRate> result = meanFlowRateRepository.findByTimeWindowAndLabel(label, from, to);

        // Thn
        assertNotNull(result, "Result should not be null");
    }

    @Test
    void testSaveMeanFlowRate() {
        MeanFlowRate meanFlowRate = new MeanFlowRate();
        meanFlowRate.setValue(10.0);

        AuditData auditData = new AuditData();
        auditData.setEventKey("eventKey");
        auditData.setLabel("label");
        auditData.setTimestamp(ZonedDateTime.now());

        meanFlowRate.setAuditData(auditData);

        MeanFlowRate savedMeanFlowRate = meanFlowRateRepository.save(meanFlowRate);

        assertNotNull(savedMeanFlowRate.getId(), "Saved MeanFlowRate should have an ID");
        assertEquals(10.0, savedMeanFlowRate.getValue(), "Saved MeanFlowRate should have the correct value");
    }
}