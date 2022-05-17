package events

import kotlinx.serialization.SerialName
import models.Refund

@kotlinx.serialization.Serializable @SerialName("RefundDecline")
data class RefundDecline (

    override val refund: Refund,
    val reason: String
): RefundEvent()