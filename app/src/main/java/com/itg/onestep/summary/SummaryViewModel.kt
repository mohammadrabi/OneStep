package com.itg.onestep.summary

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import com.itg.onestep.modules.Summary
import com.itg.onestep.databinding.ActivityWalkSummaryBinding
import com.itg.onestep.listener.SummaryCardButtonsClickListener

class SummaryViewModel(
        private val summary: Summary,
        val activity: Activity,
        var activityBinding: ActivityWalkSummaryBinding?,
        val listener: SummaryCardButtonsClickListener,
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

        adapter = SummaryAdapter(activity, items.toTypedArray(), listener, summary.metadata?.seconds)
        val linearLayoutManager = LinearLayoutManager(activity)
        activityBinding?.recyclerView?.itemAnimator = null
        activityBinding?.recyclerView?.layoutManager = linearLayoutManager
        activityBinding?.recyclerView?.adapter = adapter
    }
}
