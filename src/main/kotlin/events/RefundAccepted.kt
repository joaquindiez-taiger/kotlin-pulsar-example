package events

import kotlinx.serialization.SerialName
import models.Refund

@kotlinx.serialization.Serializable @SerialName("RefundAccepted")
data class RefundAccepted (
    override val refund: Refund
): RefundEvent()