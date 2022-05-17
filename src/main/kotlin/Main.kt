import events.RefundRequest
import kotlinx.coroutines.runBlocking
import models.Refund
import org.slf4j.LoggerFactory

fun main () = runBlocking {
    val logger = LoggerFactory.getLogger("EventsProducer")

    logger.info("Running Kotlin Pulsar Sample")

     Pulsar.client().use {pulsarClient ->
        val producer = Pulsar.refundEventsProducer(pulsarClient)

        repeat(10) {
            val event = RefundRequest(Refund.random())

            logger.info("Sending ${event.refund.id}")
            producer.sendEvent(event)
        }
    }
}