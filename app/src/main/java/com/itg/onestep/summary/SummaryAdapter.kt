package com.itg.onestep.summary

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import com.itg.onestep.databinding.SummaryCardBinding
import com.itg.onestep.databinding.MetadataLayoutBinding
import com.itg.onestep.handlers.SummaryCardViewEventHandler
import com.itg.onestep.listener.SummaryCardButtonsClickListener
import com.itg.onestep.modules.MetadataObject
import com.itg.onestep.modules.cards

class SummaryAdapter(
        private var context: Context,
        private var items: Array<Any>,
        private val listener: SummaryCardButtonsClickListener,
        private val seconds: Int?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        holder = when (viewType) {
            CALIBRATION_CARD -> CalibrationSummaryViewHolder(SummaryCardBinding.inflate(inflater, parent, false))
            METADATA_ITEM -> SummaryMetaDataViewHolder(MetadataLayoutBinding.inflate(inflater, parent, false))
            else -> {
                //CelloLog.i(SummaryActivity.TAG, "Summary Adapter no holder for viewType: $viewType")
                throw IllegalArgumentException()
            }
        }
        return holder
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is cards -> CALIBRATION_CARD
            is MetadataObject -> METADATA_ITEM
            else -> {
//                CelloLog.i(SummaryActivity.TAG, "unsupported card type")
                throw IllegalArgumentException()
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (items[position]) {
            is cards -> {
                return (holder as CalibrationSummaryViewHolder).bind(items[position] as cards)
            }

            is MetadataObject -> {
                return (holder as SummaryMetaDataViewHolder).bind(items[position] as MetadataObject)
            }

            else -> {
                return (holder as CalibrationSummaryViewHolder).bind(items[position] as cards)
            }
        }
    }

    inner class CalibrationSummaryViewHolder(private val binding: SummaryCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: cards) {
            val eventHandler = SummaryCardViewEventHandler(context, item, binding, listener, seconds ?: 0)
            binding.eventHandler = eventHandler
            setCardBubblePos(binding)

            binding.root.viewTreeObserver.addOnGlobalLayoutListener(
                object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        binding.root.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        setCardBubblePos(binding)
                    }
                }
            )
            binding.executePendingBindings()
        }

        fun setCardBubblePos(card: SummaryCardBinding) {
            val min = card.rangeContainer.x
            val max = card.rangeContainer.x + card.rangeContainer.width
            card.eventHandler?.let {
                // range bar x  - range bar x + width is max
                // percentage of that - minus bubble is location
                // value = (percentage * (max - min) / 100) + min
                val pos = (it.percent * (max - min) / 100) + min
                // restrict bubble from going past edges of rainbow
                var posVal = pos.toFloat() - (card.bubbleContainer.width / 2)
                if (it.percent <= GAP_OFFSET) {
                    posVal += GAP_OFFSET
                }
                if (it.percent >= 95) {
                    posVal -= GAP_OFFSET
                }
                card.bubbleContainer.x = posVal
            }
        }
    }

    inner class SummaryMetaDataViewHolder(private val binding: MetadataLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MetadataObject) {
            val eventHandler = SummaryMetaDataEventHandler(item)
            binding.eventHandler = eventHandler
            binding.executePendingBindings()
        }
    }

    companion object {
        const val CALIBRATION_CARD = 1
        const val INSIGHT_CARD = 2
        const val MAP_CARD = 3
        const val METADATA_ITEM = 4
        const val GAP_OFFSET = 5
    }
}
