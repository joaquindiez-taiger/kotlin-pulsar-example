import models.Refund

class RefundChecker {

    fun check(refund: Refund): RefundStatus =  when(refund.amount > 200){
        true -> {
            //RefundStatus.Declined ( "Refund is too Large")
            throw java.lang.Exception()
        }
        false -> RefundStatus.Approved
    }

}

sealed class RefundStatus{
    object Approved : RefundStatus()
    class Declined ( reason: String): RefundStatus()
}