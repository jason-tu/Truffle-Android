package io.synople.truffle.vendor

import android.app.Application
import io.synople.truffle.common.model.Vendor

class AppContext : Application() {
    companion object {
        lateinit var vendor: Vendor
    }
}