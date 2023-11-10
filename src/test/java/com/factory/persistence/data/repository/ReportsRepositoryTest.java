package com.factory.persistence.data.repository;

import com.factory.common.JpaIntegrationTest;
import com.factory.persistence.data.entity.Report;
import com.factory.persistence.data.entity.ReportSensorLabel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JpaIntegrationTest
class ReportsRepositoryTest {
    @Autowired
    private ReportsRepository reportsRepository;

    @Test
    void testFindAllByTimeWindow() {
        final String testReport = "TestReport";
        Report report = createReport(testReport, ZonedDateTime.now().minusDays(1), ZonedDateTime.now().plusDays(1));
        reportsRepository.save(report);

        ZonedDateTime from = ZonedDateTime.now().minusDays(2);
        ZonedDateTime to = ZonedDateTime.now().plusDays(2);
        List<Report> reports = reportsRepository.findAllByTimeWindow(from, to);

        assertEquals(1, reports.size());
        Report foundReport = reports.get(0);
        assertEquals(report.getLabel(), foundReport.getLabel());
        assertEquals(report.getFrom(), foundReport.getFrom());
        assertEquals(report.getTo(), foundReport.getTo());
    }

    private Report createReport(final String label, final ZonedDateTime from, final ZonedDateTime to) {
        final String sensorType1 = "SensorType1";
        final String label1 = "Label1";
        final String sensorType2 = "SensorType2";
        final String label2 = "Label2";

        Report report = new Report();
        report.setLabel(label);
        report.setFrom(from);
        report.setTo(to);

        Set<ReportSensorLabel> reportSensorLabels = new HashSet<>();
        reportSensorLabels.add(createReportSensorLabel(sensorType1, label1, report));
        reportSensorLabels.add(createReportSensorLabel(sensorType2, label2, report));
        report.setReportSensorLabels(reportSensorLabels);

        return report;
    }

    private ReportSensorLabel createReportSensorLabel(final String sensorType, final String label, final Report report) {
        ReportSensorLabel reportSensorLabel = new ReportSensorLabel();
        reportSensorLabel.setSensorType(sensorType);
        reportSensorLabel.setLabel(label);
        reportSensorLabel.setReport(report);
        return reportSensorLabel;
    }
}