package com.factory.message.serialization;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

import static com.factory.message.serialization.Configs.getAvroSerdeConfig;

@Slf4j
public class AvroSerializer<T extends SpecificRecordBase> implements Serializer<T> {

    private final KafkaAvroSerializer kafkaAvroSerializer = new KafkaAvroSerializer();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Map<String, Object> props = getAvroSerdeConfig(configs);
        kafkaAvroSerializer.configure(props, false);
    }

    @Override
    public byte[] serialize(final String topic, final T payload) {
        byte[] bytes = null;
        try {
            bytes = kafkaAvroSerializer.serialize(topic, payload);
            log.debug("Serialized payload: {}", bytes);
        } catch (final Exception e) {
            log.error("Unable to serialize payload ", e);
        }
        return bytes;
    }

    @Override
    public void close() {
        log.info("Shutting down ...");
    }
}
