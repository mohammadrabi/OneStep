package com.itg.onestep.summary

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import com.itg.onestep.modules.SummaryObject
import com.itg.onestep.databinding.ActivitySummaryBinding

class SummaryViewModel(
    summary: SummaryObject,
    activity: Activity,
    activityBinding: ActivitySummaryBinding?,
    listener: SummaryCardButtonsClickListener,
) {
    var adapter: SummaryAdapter

    init {
        val items = arrayListOf<Any>()
        summary.metadata?.let {
            items.add(it)
        }
        summary.cards?.let {
            items.addAll(it.toCollection(ArrayList()))
        }

        adapter = SummaryAdapter(activity, items.toTypedArray(), listener)
        val linearLayoutManager = LinearLayoutManager(activity)
        activityBinding?.recyclerView?.itemAnimator = null
        activityBinding?.recyclerView?.layoutManager = linearLayoutManager
        activityBinding?.recyclerView?.adapter = adapter
    }
}
