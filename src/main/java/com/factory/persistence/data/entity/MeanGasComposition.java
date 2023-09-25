package com.factory.persistence.data.entity;

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
@Table(name = "mean_gas_composition", schema = "factory_data",
        uniqueConstraints = @UniqueConstraint(columnNames = {"label", "event_key"}))
@Data
public class MeanGasComposition {

    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Embedded
    private AuditData auditData;

    @Column(name = "o2")
    private Double o2;

    @Column(name = "h2")
    private Double h2;

    @Column(name = "nh3")
    private Double nh3;

    @Column(name = "co2")
    private Double co2;

    @Column(name = "n2")
    private Double n2;
}
