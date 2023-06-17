import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.common.serialization.Serializer


/*
  This class is the gateway for the application to upload the entire dataset into Kafka and is split into the four following stages:
    1. Configure the clients
    2. Produce a batch of records
    3. Produce events as records
    4. Produce a record in a transaction
 */

class ProducingApp {

  /*
    Expected Output: KafkaProducer[K, V]
      - Kafka Client Library already includes classic serializers but the goal here is for you to serialize your own structure
   */

  val keySerializer: Serializer[Key] = ???
  val tvShowSerializer: Serializer[TvShow] = ???
  val ratingSerializer: Serializer[Rating] = ???

  val producer = new KafkaProducer[Key, TvShow](
    config.producerConfig.toMap.asJava, // producer-config bloc from conf file
    keySerializer,
    tvShowSerializer)



}
