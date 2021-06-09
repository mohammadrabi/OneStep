package com.itg.onestep.summary

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import com.itg.onestep.databinding.SummaryCardBinding
import com.itg.onestep.databinding.MetadataLayoutBinding
import com.itg.onestep.modules.MetadataObject
import com.itg.onestep.modules.CardObject

class SummaryAdapter(
        private var context: Context,
        private var items: Array<Any>,
        private val listener: SummaryCardButtonsClickListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        holder = when (viewType) {
            SUMMARY_CARD -> SummaryViewHolder(SummaryCardBinding.inflate(inflater, parent, false))
            METADATA -> SummaryMetaDataViewHolder(MetadataLayoutBinding.inflate(inflater, parent, false))
            else -> {
                throw IllegalArgumentException()
            }
        }
        return holder
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is MetadataObject -> METADATA
            is CardObject -> SUMMARY_CARD
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (items[position]) {
            is CardObject -> {
                (holder as SummaryViewHolder).bind(items[position] as CardObject)
            }
            is MetadataObject -> {
                (holder as SummaryMetaDataViewHolder).bind(items[position] as MetadataObject)
            }
            else -> {
                (holder as SummaryViewHolder).bind(items[position] as CardObject)
            }
        }
    }

    inner class SummaryViewHolder(private val binding: SummaryCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CardObject) {
            val eventHandler = SummaryCardViewEventHandler(context, item, binding, listener)
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
                val pos = (it.percent * (max - min) / 100) + min
                var posVal = pos.toFloat() - (card.bubbleContainer.width / 2)
                if (it.percent <= 0) {
                    posVal += 20
                }
                if (it.percent >= 100) {
                    posVal -= 20
                }
                card.bubbleContainer.x = posVal
            }
        }
    }

    inner class SummaryMetaDataViewHolder(private val binding: MetadataLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MetadataObject) {
            val eventHandler = SummaryMetaDataEventHandler(context,item)
            binding.eventHandler = eventHandler
            binding.executePendingBindings()
        }
    }

    companion object {
        const val METADATA = 1
        const val SUMMARY_CARD = 2
    }
}
