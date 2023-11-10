package com.factory.persistence.data.repository;

import com.factory.common.JpaIntegrationTest;
import com.factory.persistence.data.entity.AuditData;
import com.factory.persistence.data.entity.MeanTemperature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@JpaIntegrationTest
class MeanTemperatureRepositoryTest {

    public static final String TEST_LABEL = "TestLabel";
    @Autowired
    private MeanTemperatureRepository meanTemperatureRepository;

    @Test
    void testFindByTimeWindowAndLabel() {
        MeanTemperature meanTemperature = createMeanTemperature(TEST_LABEL, ZonedDateTime.now(), 25.0);
        meanTemperatureRepository.save(meanTemperature);

        ZonedDateTime from = ZonedDateTime.now().minusDays(1);
        ZonedDateTime to = ZonedDateTime.now().plusDays(1);
        String label = TEST_LABEL;
        List<MeanTemperature> foundMeanTemperatures = meanTemperatureRepository.findByTimeWindowAndLabel(label, from, to);

        assertEquals(1, foundMeanTemperatures.size());
        MeanTemperature foundMeanTemperature = foundMeanTemperatures.get(0);
        assertEquals(meanTemperature.getValue(), foundMeanTemperature.getValue());
        assertEquals(meanTemperature.getAuditData().getLabel(), foundMeanTemperature.getAuditData().getLabel());
        assertEquals(meanTemperature.getAuditData().getTimestamp(), foundMeanTemperature.getAuditData().getTimestamp());
    }

    private MeanTemperature createMeanTemperature(final String label, final ZonedDateTime timestamp, final Double value) {
        MeanTemperature meanTemperature = new MeanTemperature();
        meanTemperature.setValue(value);

        AuditData auditData = new AuditData();
        auditData.setEventKey(UUID.randomUUID().toString());
        auditData.setLabel(label);
        auditData.setTimestamp(timestamp);
        meanTemperature.setAuditData(auditData);

        return meanTemperature;
    }
}