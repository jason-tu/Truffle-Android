package io.synople.truffle.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Item(
    var id: String = "",
    var name: String = "",
    var price: Float = 0f
) : Parcelable
