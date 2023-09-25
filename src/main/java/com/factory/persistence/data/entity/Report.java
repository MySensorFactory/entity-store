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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "report", schema = "factory_data")
@Getter
@Setter
public class Report {

    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "label", unique = true)
    private String label;

    @Column(name = "from_time")
    private ZonedDateTime from;

    @Column(name = "to_time")
    private ZonedDateTime to;

    @OneToMany(mappedBy = "report",
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    private Set<ReportSensorLabel> reportSensorLabels;
}
