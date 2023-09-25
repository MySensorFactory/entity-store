CREATE TABLE IF NOT EXISTS factory_data.compressor_state
(
    id                  uuid NOT NULL,
    label               text,
    "timestamp"         timestamp with time zone,
    event_key           text,
    noise_level         double precision,
    vibration_amplitude double precision,
    vibration_frequency double precision,
    PRIMARY KEY (id),
    UNIQUE (label, event_key)
);
