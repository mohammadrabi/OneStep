package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(var t: Float?, var latitude: Double, var longitude: Double) : Parcelable
