package com.factory.persistence.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Report report;

    @Column(name = "sensor_type")
    private String sensorType;

    @Column(name = "label")
    @Pattern(regexp = "\\S+")
    private String label;
}
