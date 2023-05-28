import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.Serializer
import org.apache.kafka.streams.processor.api.RecordMetadata

import scala.concurrent.Future

/*
  Overarching meta for the ProducingApp is the config of the Kafka engine

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

  // we need to check whether it is advisable to use 'AnyRef'

  // Look to the pattern of the of the mapped configs in relation to the data models
  val baseConfig: Map[String, AnyRef] = config.produceConfig.toMap // doesn't there need to be a config object in order for the mapping to work?
  val config1 = baseConfig ++ Map("client.id" -> "client1", "linger.ms" -> s"60000") // the purpose of the linger is to offset the buffer brokers
  val producer1 = new KafkaProducer[Key, TvShow](config1, keySerializer, tvShowSerializer)

  val config2 = baseConfig ++ Map("client.id" -> "client2", "retries" -> "0")
  val producer2 = new KafkaProducer[Key, Rating](config2, keySerializer, ratingSerializer)

  val config3 = baseConfig ++ Map("client.id" -> "client3", "transactional.id" -> "client3")
  val producer3 = new KafkaProducer[Key, Rating](config3, keySerializer, ratingSerializer)

  val maybeMetadata: Vector[Future[RecordMetadata]] = Dataset.AllTvShows.toVector.map {
    case (showIdKey, showValue) =>
      val record = new ProducerRecord[Key, TvShow]("topic-name", showIdKey, showValue)
      producer1 send record
  }
}
