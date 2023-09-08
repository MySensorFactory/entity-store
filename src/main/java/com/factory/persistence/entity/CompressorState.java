package com.factory.persistence.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.UUID;

@Entity
@Table(name = "compressor_state", schema = "factory_data",
        uniqueConstraints = @UniqueConstraint(columnNames = {"label", "event_key"}))
@Data
public class CompressorState {

    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Embedded
    private AuditData auditData;

    @Column(name = "noise_level")
    private Double noiseLevel;

    @Column(name = "vibration_amplitude")
    private Double vibrationAmplitude;

    @Column(name = "vibration_frequency")
    private Double vibrationFrequency;
}

