package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gps(val template: String?, val version: Int?, val latitude: Double, val longitude: Double, val events: Array<Event>) : Parcelable
