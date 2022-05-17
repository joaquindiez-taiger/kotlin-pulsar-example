package events

import models.Refund

data class RefundDecline (

    val refund: Refund,
    val reason: String
)