package com.factory.persistence.data.repository;

import com.factory.common.JpaIntegrationTest;
import com.factory.persistence.data.entity.AuditData;
import com.factory.persistence.data.entity.CompressorState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JpaIntegrationTest
class CompressorStateRepositoryTest {
    @Autowired
    private CompressorStateRepository compressorStateRepository;

    @Test
    void testFindByTimeWindowAndLabel() {
        String label = "yourLabel";
        ZonedDateTime from = ZonedDateTime.parse("2023-01-01T00:00:00.000Z");
        ZonedDateTime to = ZonedDateTime.parse("2023-02-01T00:00:00.000Z");

        List<CompressorState> result = compressorStateRepository.findByTimeWindowAndLabel(label, from, to);

        assertNotNull(result, "Result should not be null");
    }

    @Test
    void testSaveCompressorState() {
        CompressorState compressorState = new CompressorState();

        AuditData auditData = new AuditData();
        auditData.setEventKey("eventKey");
        auditData.setLabel("label");
        auditData.setTimestamp(ZonedDateTime.now());

        compressorState.setAuditData(auditData);
        compressorState.setNoiseLevel(70.0);
        compressorState.setVibrationAmplitude(0.5);
        compressorState.setVibrationFrequency(50.0);

        CompressorState savedCompressorState = compressorStateRepository.save(compressorState);

        assertNotNull(savedCompressorState.getId(), "Saved CompressorState should have an ID");
        assertEquals(70.0, savedCompressorState.getNoiseLevel(), "Saved CompressorState should have the correct noise level");
        assertEquals(0.5, savedCompressorState.getVibrationAmplitude(), "Saved CompressorState should have the correct vibration amplitude");
        assertEquals(50.0, savedCompressorState.getVibrationFrequency(), "Saved CompressorState should have the correct vibration frequency");
    }
}