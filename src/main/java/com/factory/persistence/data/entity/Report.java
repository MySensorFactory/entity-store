package com.factory.persistence.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
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
    @Pattern(regexp = "\\S+")
    private String label;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "from_time")
    private ZonedDateTime from;

    @Column(name = "to_time")
    private ZonedDateTime to;

    @OneToMany(mappedBy = "report",
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    private Set<ReportSensorLabel> reportSensorLabels = new HashSet<>();

    public void update(final Report other, final Runnable flushOperation){
        this.setLabel(other.getLabel());
        this.setName(other.getName());
        this.setDescription(other.getDescription());
        this.setFrom(other.getFrom());
        this.setTo(other.getTo());

        this.getReportSensorLabels().clear();

        if(Objects.nonNull(flushOperation)){
            flushOperation.run();
        }

        this.getReportSensorLabels().addAll(other.getReportSensorLabels());
        this.getReportSensorLabels().forEach(sl -> sl.setReport(this));
    }
}
