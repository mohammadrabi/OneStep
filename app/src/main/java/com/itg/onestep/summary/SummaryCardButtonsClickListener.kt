package com.itg.onestep.summary

interface SummaryCardButtonsClickListener {
    fun onMoreInfoClickedClicked(title: String?, description: String?, videoId: String?) {}
    fun onViewDetailsClicked(title: String?, gaitParameter: String?, uuid: String?, seconds: Int) {}
}
