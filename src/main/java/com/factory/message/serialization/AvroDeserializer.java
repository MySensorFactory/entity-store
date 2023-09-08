package com.factory.message.serialization;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

import static com.factory.message.serialization.Configs.getAvroSerdeConfig;

@Slf4j
@NoArgsConstructor
public class AvroDeserializer<T extends SpecificRecordBase> implements Deserializer<T> {

    private final KafkaAvroDeserializer kafkaAvroDeserializer = new KafkaAvroDeserializer();

    @Override
    public void configure(Map configs, boolean isKey) {
        Map<String, Object> props = getAvroSerdeConfig(configs);
        this.kafkaAvroDeserializer.configure(props, false);
    }

    @Override
    public T deserialize(String topic, byte[] bytes) {
        T returnObject = null;
        try {
            if (bytes != null) {
                returnObject = (T) kafkaAvroDeserializer.deserialize(topic, null, bytes);
                log.debug("deserialized data='{}'", returnObject.toString());
            }
        } catch (final Exception e) {
            log.error("Unable to Deserialize bytes[] ", e);
        }

        return returnObject;
    }

    @Override
    public void close() {
        // do nothing
    }
}
