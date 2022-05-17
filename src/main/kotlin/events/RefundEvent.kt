package events

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Refund

@kotlinx.serialization.Serializable
sealed class RefundEvent {
    abstract val refund: Refund

    open  fun toByteArray() = Json.encodeToString(this).toByteArray()
    companion object{
        fun fromByteArray ( bytes: ByteArray): RefundEvent = Json.decodeFromString(String(bytes))
    }
}


fun main(){

    val event: RefundEvent = RefundRequest(Refund.random())
    val bytes = event.toByteArray()
    println(String(bytes))

    val event2: RefundEvent = RefundEvent.fromByteArray(bytes)

    println(event2::class.simpleName)
    println(event2.refund)

}