package io.synople.truffle.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
    var id: String = "",
    var name: String = "",
    var transactions: MutableList<Ticket> = mutableListOf()
): Parcelable
