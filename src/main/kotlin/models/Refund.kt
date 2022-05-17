package models
import kotlinx.serialization.Serializable
import org.jetbrains.annotations.TestOnly
import java.util.*


@JvmInline
@Serializable
value class RefundId (val id: String = UUID.randomUUID().toString())
@JvmInline
@Serializable
value class UserId (val id: String = UUID.randomUUID().toString())
@JvmInline
@Serializable
value class MerchantId (val id: String = UUID.randomUUID().toString())
@JvmInline
@Serializable
value class OrderNumberId (val id: String = UUID.randomUUID().toString())

@Serializable
data class Refund (

    val id: RefundId,
    val userId : UserId,
    val merchantId: MerchantId,
    val orderNumberId: OrderNumberId,
    val amount: Int
){
    companion object{
        @TestOnly
        fun random() = Refund( RefundId(), UserId(), MerchantId(), OrderNumberId(), kotlin.random.Random.nextInt())
    }
}