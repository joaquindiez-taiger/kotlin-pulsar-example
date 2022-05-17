package events

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Refund

@Serializable
class RefundRequest (

    val refund : Refund

){
    fun toByteArray() = Json.encodeToString(this).toByteArray()

    companion object{
        fun fromByteArray(bytes: ByteArray) = Json.decodeFromString(serializer(), String(bytes))
    }
}