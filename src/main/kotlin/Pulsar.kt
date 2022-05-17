import events.RefundEvent
import org.apache.pulsar.client.api.DeadLetterPolicy
import org.apache.pulsar.client.api.Producer
import org.apache.pulsar.client.api.PulsarClient
import org.apache.pulsar.client.api.SubscriptionInitialPosition
import org.apache.pulsar.client.api.SubscriptionType
import java.util.concurrent.TimeUnit


/**
 * Ver Notas Readme.mds oara configurar Tenant y Namespace
 *
 */
object Pulsar {

    private const val PULSAR_URL = "pulsar://127.0.0.1:6650"
    private const val SAMPLE_TOPIC = "persistent://tenant/ns1/sample-events"

    internal fun client(): PulsarClient =
        PulsarClient.builder()
            .serviceUrl(PULSAR_URL)
            .build()

    internal fun refundEventsProducer(client: PulsarClient) = client
        .newProducer()
        .topic(SAMPLE_TOPIC)
        .create()

    /**
     * Por defecto las Subscripciones son Exclusivas si se quisiera tener varios
     * procesos concurrentes hay que cambiar a .subscriptionType(SubscriptionType.Shared)
     */
    internal fun refundEventsConsumer(client: PulsarClient, name: String) = client
        .newConsumer()
        .subscriptionName(name)
        .subscriptionType(SubscriptionType.Shared)
        .negativeAckRedeliveryDelay(10, TimeUnit.SECONDS)
        .deadLetterPolicy(DeadLetterPolicy.builder().maxRedeliverCount(2).build())
        .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
        .topic(SAMPLE_TOPIC)
        .subscribe()

    internal fun consumerFromEarliest(client: PulsarClient, name: String) = client
        .newConsumer()
        .topic(SAMPLE_TOPIC)
        .subscriptionName(name)
        .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
        .subscribe()
}

@JvmName("sendLoanEventByteArray")
internal fun Producer<ByteArray>.sendEvent(event: RefundEvent) = send(event.toByteArray())