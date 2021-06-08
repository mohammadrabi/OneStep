package com.itg.onestep.listener

interface SummaryCardButtonsClickListener {
    fun onMoreInfoClickedClicked(title: String?, description: String?, videoId: String?) {}
    fun onViewDetailsClicked(title: String?, gaitParameter: String?, uuid: String?, seconds: Int) {}
}
