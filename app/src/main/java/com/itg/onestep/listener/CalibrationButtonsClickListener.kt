package com.itg.onestep.listener

interface CalibrationButtonsClickListener {
    fun onMoreInfoClickedClicked(title: String?, description: String?, videoId: String?) {}
    fun onViewDetailsClicked(title: String?, gaitParameter: String?, uuid: String?, seconds: Int) {}
}
