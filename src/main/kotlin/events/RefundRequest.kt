package events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import models.Refund

@Serializable @SerialName("RefundRequest")
class RefundRequest (

    override  val refund : Refund

): RefundEvent()