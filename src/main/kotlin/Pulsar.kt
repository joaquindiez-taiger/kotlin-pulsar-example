import org.apache.pulsar.client.api.PulsarClient
import org.apache.pulsar.client.api.SubscriptionInitialPosition


/**
 * Ver Notas Readme.mds oara configurar Tenant y Namespace
 *
 */
object Pulsar {

    const val pulsarUrl = "pulsar://127.0.0.1:6650"
    const val topic = "persistent://tenant/ns1/sample-events"


    internal fun client(): PulsarClient =
        PulsarClient.builder()
            .serviceUrl(pulsarUrl)
            .build()

    internal fun producer(client: PulsarClient) = client
        .newProducer()
        .topic(topic)
        .create()

    /**
     * Por defecto las Subscripciones son Exclusivas si se quisiera tener varios
     * procesos concurrentes hay que cambiar a .subscriptionType(SubscriptionType.Shared)
     */
    internal fun consumer(client: PulsarClient, name: String) = client
        .newConsumer()
        .topic(topic)
      //  .subscriptionType(SubscriptionType.Shared)
        .subscriptionName(name)
        .subscribe()

    internal fun consumerFromEarliest(client: PulsarClient, name: String) = client
        .newConsumer()
        .topic(topic)
        .subscriptionName(name)
        .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
        .subscribe()
}