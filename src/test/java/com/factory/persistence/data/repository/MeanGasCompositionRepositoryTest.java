package com.factory.persistence.data.repository;

import com.factory.common.JpaIntegrationTest;
import com.factory.persistence.data.entity.AuditData;
import com.factory.persistence.data.entity.MeanGasComposition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@JpaIntegrationTest
class MeanGasCompositionRepositoryTest {
    @Autowired
    private MeanGasCompositionRepository meanGasCompositionRepository;

    @Test
    void testFindByTimeWindowAndLabel() {
        MeanGasComposition meanGasComposition = createMeanGasComposition();
        meanGasCompositionRepository.save(meanGasComposition);

        String label = meanGasComposition.getAuditData().getLabel();
        ZonedDateTime from = meanGasComposition.getAuditData().getTimestamp().minusHours(1);
        ZonedDateTime to = meanGasComposition.getAuditData().getTimestamp().plusHours(1);

        List<MeanGasComposition> result = meanGasCompositionRepository.findByTimeWindowAndLabel(label, from, to);

        assertEquals(1, result.size());

        MeanGasComposition foundMeanGasComposition = result.get(0);
        assertEquals(meanGasComposition.getO2(), foundMeanGasComposition.getO2());
        assertEquals(meanGasComposition.getH2(), foundMeanGasComposition.getH2());
        assertEquals(meanGasComposition.getNh3(), foundMeanGasComposition.getNh3());
        assertEquals(meanGasComposition.getCo2(), foundMeanGasComposition.getCo2());
        assertEquals(meanGasComposition.getN2(), foundMeanGasComposition.getN2());
        assertEquals(meanGasComposition.getAuditData().getLabel(), foundMeanGasComposition.getAuditData().getLabel());
        assertEquals(meanGasComposition.getAuditData().getTimestamp(), foundMeanGasComposition.getAuditData().getTimestamp());
    }

    private MeanGasComposition createMeanGasComposition() {
        MeanGasComposition meanGasComposition = new MeanGasComposition();
        meanGasComposition.setO2(20.0);
        meanGasComposition.setH2(10.0);
        meanGasComposition.setNh3(5.0);
        meanGasComposition.setCo2(8.0);
        meanGasComposition.setN2(57.0);

        AuditData auditData = new AuditData();
        auditData.setEventKey(UUID.randomUUID().toString());
        auditData.setLabel("TestLabel");
        auditData.setTimestamp(ZonedDateTime.now());

        meanGasComposition.setAuditData(auditData);
        return meanGasComposition;
    }
}