package com.factory.persistence.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Entity
@Table(name = "report_sensor_label", schema = "factory_data",
        uniqueConstraints = @UniqueConstraint(columnNames = {"report_id", "sensor_type"}))
@Getter
@Setter
public class ReportSensorLabel {

    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @JoinColumn(name = "report_id")
    @ManyToOne(optional = false)
    private Report report;

    @Column(name = "sensor_type", unique = true)
    private String sensorType;

    @Column(name = "label")
    @Pattern(regexp = "\\S+")
    private String label;
}
