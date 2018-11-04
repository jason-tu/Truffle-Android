package io.synople.truffle.common.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList
import java.util.UUID

@Parcelize
data class Ticket(
    var id: String = "",
    var items: MutableList<Item> = mutableListOf(),
    var customerId: String = "",
    var vendorId: String = "",
    var time: String = "",
    var amount: Double = 0.0
) : Parcelable
