package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Insight(
    val version: Int? = null,
    val template: String? = null,
    val uuid: String? = null,
    val title: String? = null,
    val body: String? = null,
    val external_url: String? = null,
    val image_url: String? = null,
    val feedback: Int? = null,
    val feedback_visible: Boolean? = null,
    val image_key: String? = null
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Insight
        if (version != other.version) return false
        if (template != other.template) return false
        if (uuid != other.uuid) return false
        if (title != other.title) return false
        if (body != other.body) return false
        if (external_url != other.external_url) return false
        if (image_url != other.image_url) return false
        if (feedback != other.feedback) return false
        if (feedback_visible != other.feedback_visible) return false
        if (image_key != other.image_key) return false

        return true
    }

    override fun hashCode(): Int {
        var result = version ?: 0
        result = 31 * result + (template?.hashCode() ?: 0)
        result = 31 * result + (uuid?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (body?.hashCode() ?: 0)
        result = 31 * result + (external_url?.hashCode() ?: 0)
        result = 31 * result + (image_key?.hashCode() ?: 0)
        result = 31 * result + (feedback?.hashCode() ?: 0)
        result = 31 * result + (feedback_visible?.hashCode() ?: 0)
        result = 31 * result + (image_url?.hashCode() ?: 0)
        return result
    }

    companion object {
        const val BULB = "insightBulb"
        const val HAT = "insightHat"
        const val LIKE = "insightLike"
        const val MESSAGE = "insightMessage"
        const val SPEAKER = "insightSpeaker"
        const val BOOK = "insightBook"
        const val LIKE_VALUE = 1
        const val DISLIKE_VALUE = 2
    }
}
