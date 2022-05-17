import events.RefundEvent
import kotlinx.coroutines.future.await
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import org.apache.pulsar.client.api.PulsarClientException
import org.slf4j.LoggerFactory

fun main() = runBlocking {
    val logger = LoggerFactory.getLogger("RefundCheckerService")
    logger.info(" Start RefundChecker v1.0")

    val refundChecker = RefundChecker()

    Pulsar.client().use { pulsarClient ->

        val consumer = Pulsar.refundEventsConsumer(pulsarClient, "refund-checker-subscription")

        while (isActive) {

            val message = try {
                consumer.receiveAsync().await()
            } catch (e: PulsarClientException) {
                logger.warn("Failed to receive message: $e")
                continue
            }
            //val message: Message<ByteArray> = consumer.receive()
            try{
                val refundRequestEvent = RefundEvent.fromByteArray(message.data)
                when ( refundChecker.check(refundRequestEvent.refund)){

                    is RefundStatus.Declined -> {
                        logger.info ( "Refund ${refundRequestEvent.refund.id} declined")
                    }
                    is RefundStatus.Approved -> {
                        logger.info ( "Refund ${refundRequestEvent.refund.id} approved")
                    }
                }
                consumer.acknowledge(message)
            }catch(e: java.lang.Exception){
                consumer.negativeAcknowledge(message)
                logger.info ( "Refund ${message.messageId} rejected")
            }
        }
    }
}