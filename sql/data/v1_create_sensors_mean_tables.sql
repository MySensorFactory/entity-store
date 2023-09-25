CREATE TABLE IF NOT EXISTS factory_data.mean_temperature
(
    id        uuid NOT NULL,
    value     double precision,
    label     text,
    event_key text,
    timestamp timestamp with time zone,
    PRIMARY KEY (id),
    UNIQUE (label, event_key)
);

CREATE TABLE IF NOT EXISTS factory_data.mean_pressure
(
    id        uuid NOT NULL,
    value     double precision,
    label     text,
    event_key text,
    timestamp timestamp with time zone,
    PRIMARY KEY (id),
    UNIQUE (label, event_key)
);

CREATE TABLE IF NOT EXISTS factory_data.mean_flow_rate
(
    id        uuid NOT NULL,
    value     double precision,
    label     text,
    event_key text,
    timestamp timestamp with time zone,
    PRIMARY KEY (id),
    UNIQUE (label, event_key)
);

CREATE TABLE IF NOT EXISTS factory_data.mean_gas_composition
(
    id        uuid NOT NULL,
    o2        double precision,
    label     text,
    timestamp timestamp with time zone,
    event_key text,
    h2        double precision,
    nh3       double precision,
    co2       double precision,
    n2        double precision,
    PRIMARY KEY (id),
    UNIQUE (label, event_key)
);

CREATE TABLE IF NOT EXISTS factory_data.mean_reaction_performance
(
    id        uuid NOT NULL,
    value     double precision,
    label     text,
    event_key text,
    timestamp timestamp with time zone,
    PRIMARY KEY (id),
    UNIQUE (label, event_key)
);
