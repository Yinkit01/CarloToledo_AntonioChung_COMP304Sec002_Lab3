package com.example.carlotoledo_antoniochung_comp304sec002_Lab3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.database.schedule.Schedule
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.databinding.FlightInfoItemBinding


class AirportScheduleListAdapter(private val onItemClicked: (Schedule) -> Unit) :
    ListAdapter<Schedule, AirportScheduleListAdapter.AirportViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Schedule>() {
            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirportViewHolder {
        val viewHolder = AirportViewHolder(
            FlightInfoItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: AirportViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AirportViewHolder(
        private var binding: FlightInfoItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(schedule: Schedule) {
            binding.airlineTextView.text = schedule.airline
            binding.arrivalTimeTextView.text = schedule.arrivalTime
            binding.terminalTextView.text = schedule.terminal
        }
    }
}
