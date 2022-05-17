import events.RefundRequest
import models.Refund

fun main () {

    println("Running Kotlin Pulsar Sample")

     Pulsar.client().use {pulsarClient ->
        val producer = Pulsar.producer(pulsarClient)

        repeat(10) {

            val event = RefundRequest(Refund.random())
            println(event)
            producer.send(event.toByteArray())
        }

    }


}