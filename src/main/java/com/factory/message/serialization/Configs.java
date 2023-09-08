package com.factory.message.serialization;

import io.confluent.kafka.serializers.subject.RecordNameStrategy;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig.AUTO_REGISTER_SCHEMAS;
import static io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;
import static io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig.VALUE_SUBJECT_NAME_STRATEGY;

@UtilityClass
public class Configs {
    @NotNull
    public static Map<String, Object> getAvroSerdeConfig(final Map<String, ?> configs) {
        Map<String, Object> props = new HashMap<>();
        props.put(SCHEMA_REGISTRY_URL_CONFIG, configs.get(SCHEMA_REGISTRY_URL_CONFIG));
        props.put(AUTO_REGISTER_SCHEMAS,
                Objects.requireNonNullElse(configs.get(AUTO_REGISTER_SCHEMAS), false));
        props.put(VALUE_SUBJECT_NAME_STRATEGY,
                Objects.requireNonNullElse(configs.get(VALUE_SUBJECT_NAME_STRATEGY), RecordNameStrategy.class));
        return props;
    }
}
