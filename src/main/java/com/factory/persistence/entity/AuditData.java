package com.factory.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.ZonedDateTime;

@Embeddable
@Getter
@Setter
public class AuditData {
    @Column(name = "event_key")
    private String eventKey;

    @Column(name = "label")
    private String label;

    @Column(name = "timestamp")
    private ZonedDateTime timestamp;
}
