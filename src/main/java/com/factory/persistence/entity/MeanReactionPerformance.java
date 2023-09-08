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
@Table(name = "mean_reaction_performance", schema = "factory_data",
        uniqueConstraints = @UniqueConstraint(columnNames = {"label", "event_key"}))
@Data
public class MeanReactionPerformance {

    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "value")
    private Double value;

    @Embedded
    private AuditData auditData;
}

