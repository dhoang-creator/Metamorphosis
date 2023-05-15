import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.common.serialization.Serializer

/*
  ProducingApp separated into four parts:
  1. Configure the clients,
  2. Produce a batch of records
  3. Produce events as records
  4. Produce a record in a transaction

  We also need to create an instance of KafkaProducer[K, V]
 */


class ProducingApp {

  val keySerializer: Serializer[Key] = ???
  val tvShowSerializer: Serializer[TvShow] = ???
  val ratingSerializer: Serializer[Rating] = ???

  val producer = new KafkaProducer[Key, TvShow](
    config.producerConfig.toMap.asJava, // producer-config bloc from conf file
    keySerializer,
    tvShowSerializer
  )
}
