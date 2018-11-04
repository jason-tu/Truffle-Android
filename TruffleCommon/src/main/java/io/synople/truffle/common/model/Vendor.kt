package io.synople.truffle.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vendor(
    var id: String = "",
    var name: String = "",
    var transactions: MutableList<Item> = mutableListOf()
) : Parcelable
