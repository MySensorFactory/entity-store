CREATE TABLE factory_data.report
(
    id          UUID NOT NULL,
    label       TEXT UNIQUE,
    name        TEXT,
    description TEXT,
    from_time   TIMESTAMP,
    to_time     TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE factory_data.report_sensor_label
(
    id          UUID NOT NULL,
    report_id   UUID NOT NULL,
    sensor_type TEXT,
    label       TEXT,
    PRIMARY KEY (id),
    CONSTRAINT fk_report_sensor_label_report FOREIGN KEY (report_id)
        REFERENCES factory_data.report (id) ON DELETE CASCADE,
    CONSTRAINT uk_report_sensor_label_report_sensor_type UNIQUE (report_id, sensor_type)
);