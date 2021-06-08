package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class GaitParameters(
    val result: List<GaitParametersObject>?
) : Parcelable
