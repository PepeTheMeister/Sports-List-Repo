package com.example.challengekaizengaming.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.challengekaizengaming.R
import com.example.challengekaizengaming.dto.EventDTO
import com.example.challengekaizengaming.dto.SportDTO

class SportAdapter(
    private var items: List<SportDTO>,
) : RecyclerView.Adapter<SportAdapter.ExpandableViewHolder>() {

    private var filteredEvents : List<EventDTO> = listOf()

    class ExpandableViewHolder(itemView: View) : ViewHolder(itemView) {
        val header: TextView = itemView.findViewById(R.id.textHeader)
        val recyclerExpandable: RecyclerView = itemView.findViewById(R.id.recyclerExpandable)
        val arrowIcon : ImageView = itemView.findViewById(R.id.arrowIcon)
        val sportHeader : RelativeLayout = itemView.findViewById(R.id.sportHeader)
        val switchCompact : SwitchCompat = itemView.findViewById(R.id.switchCompact)
        val emptyText : TextView = itemView.findViewById(R.id.emptyText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpandableViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_sports, parent, false)

        return ExpandableViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpandableViewHolder, position: Int) {
        val sport = items[position]
        holder.header.text = sport.d

        val adapter = EventAdapter(sport.e)

        holder.recyclerExpandable.layoutManager = GridLayoutManager(holder.itemView.context, 3)
        holder.recyclerExpandable.adapter = adapter

        holder.emptyText.visibility = if (sport.e.isEmpty()) View.VISIBLE else View.GONE



        if(sport.isFiltered){
            filteredEvents = getFilteredEvents(sport.e)

            holder.emptyText.visibility = if (filteredEvents.isEmpty()) View.VISIBLE else View.GONE
            adapter.setEvents(filteredEvents)
        }

        if(sport.isExpanded){
            if(sport.isFiltered){
                holder.recyclerExpandable.visibility = if (filteredEvents.isNotEmpty()) View.VISIBLE else View.GONE
            }
            else{
                holder.recyclerExpandable.visibility = if (sport.e.isNotEmpty()) View.VISIBLE else View.GONE
            }

            holder.arrowIcon.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)
        }
        else{
            holder.recyclerExpandable.visibility = View.GONE
            holder.arrowIcon.setImageResource(R.drawable.baseline_keyboard_arrow_down_24)
        }

        holder.sportHeader.setOnClickListener {
            sport.isExpanded = !sport.isExpanded
            notifyItemChanged(position)
        }

        holder.switchCompact.setOnCheckedChangeListener(null)
        holder.switchCompact.isChecked = sport.isFiltered


        holder.switchCompact.setOnCheckedChangeListener{_, isChecked ->
            sport.isFiltered = isChecked
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = items.size

    fun setItems(newList : List<SportDTO> ){
        items = newList
        notifyDataSetChanged()
    }

    fun getFilteredEvents(events: List<EventDTO>): List<EventDTO> {
            return events.filter { it.isFavorite}

    }


}
