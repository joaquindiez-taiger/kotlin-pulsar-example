import events.RefundRequest
import org.apache.pulsar.client.api.Message

fun main() {
    println(" Start RefundChecker v1.0")

    val refundChecker = RefundChecker()

    Pulsar.client().use { pulsarClient ->

        val consumer = Pulsar.consumer(pulsarClient, "refund-checker-subscription")

        println(" Consumer Obtained")

        while (true) {
            val message: Message<ByteArray> = consumer.receive()
             val refundRequestEvent = RefundRequest.fromByteArray(message.data)

            when ( refundChecker.check(refundRequestEvent.refund)){

                is RefundStatus.Declined -> {
                    println ( "Refund ${refundRequestEvent.refund.id} declined")
                }

                is RefundStatus.Approved -> {
                    println ( "Refund ${refundRequestEvent.refund.id} approved")
                }

            }

            consumer.acknowledge(message)
        }

    }

}