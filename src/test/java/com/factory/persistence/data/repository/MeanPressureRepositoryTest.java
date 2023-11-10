package com.factory.persistence.data.repository;

import com.factory.common.JpaIntegrationTest;
import com.factory.persistence.data.entity.AuditData;
import com.factory.persistence.data.entity.MeanPressure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@JpaIntegrationTest
class MeanPressureRepositoryTest {
    @Autowired
    private MeanPressureRepository meanPressureRepository;

    @Test
    void testFindByTimeWindowAndLabel() {
        MeanPressure meanPressure = createMeanPressure();
        meanPressureRepository.save(meanPressure);

        String label = meanPressure.getAuditData().getLabel();
        ZonedDateTime from = meanPressure.getAuditData().getTimestamp().minusHours(1);
        ZonedDateTime to = meanPressure.getAuditData().getTimestamp().plusHours(1);

        List<MeanPressure> result = meanPressureRepository.findByTimeWindowAndLabel(label, from, to);

        assertEquals(1, result.size());

        MeanPressure foundMeanPressure = result.get(0);
        assertEquals(meanPressure.getValue(), foundMeanPressure.getValue());
        assertEquals(meanPressure.getAuditData().getLabel(), foundMeanPressure.getAuditData().getLabel());
        assertEquals(meanPressure.getAuditData().getTimestamp(), foundMeanPressure.getAuditData().getTimestamp());
    }

    private MeanPressure createMeanPressure() {
        MeanPressure meanPressure = new MeanPressure();
        meanPressure.setValue(25.0);

        AuditData auditData = new AuditData();
        auditData.setEventKey(UUID.randomUUID().toString());
        auditData.setLabel("TestLabel");
        auditData.setTimestamp(ZonedDateTime.now());

        meanPressure.setAuditData(auditData);
        return meanPressure;
    }
}