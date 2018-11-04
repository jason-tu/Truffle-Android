package io.synople.truffle.common.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList
import java.util.UUID

@Parcelize
data class Ticket(
    var id: String? = null,
    var items: List<Item>? = null,
    var customerId: String? = null,
    var vendorId: String? = null,
    var time: String? = null,
    var amount: Double = 0.toDouble()
) : Parcelable
