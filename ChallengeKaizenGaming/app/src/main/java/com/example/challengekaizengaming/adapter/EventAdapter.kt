package com.example.challengekaizengaming.adapter

import android.content.Context
import android.content.SharedPreferences
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.challengekaizengaming.R
import com.example.challengekaizengaming.dto.EventDTO

import java.util.Calendar

class EventAdapter(
    private var events: List<EventDTO>,
) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {


    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countDownTimeView : TextView = itemView.findViewById(R.id.countdown_timer)
        val starFavouritesView : ImageView = itemView.findViewById(R.id.star_favourite)
        val eventItemView : LinearLayout = itemView.findViewById(R.id.event_item)
        val competitor1View: TextView = itemView.findViewById(R.id.competitor1)
        val competitor2View: TextView = itemView.findViewById(R.id.competitor2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]

        val eventName = event.d.split("-")
        holder.competitor1View.text = eventName[0].trim()
        holder.competitor2View.text = eventName[1].trim()


        val sharedPreferences = holder.itemView.context.getSharedPreferences("FavouriteEvents", Context.MODE_PRIVATE)
        val favoritesSet = sharedPreferences.getStringSet("sport_${event.si}", null)?.toMutableSet()
            ?: mutableSetOf()

        event.isFavorite = favoritesSet.contains(event.i)

        if(event.isFavorite){
            holder.starFavouritesView.setImageResource(R.drawable.baseline_star_yellow_24)
        }
        else{
            holder.starFavouritesView.setImageResource(R.drawable.baseline_star_outline_24)
        }
        holder.eventItemView.setOnClickListener {
            event.isFavorite = !event.isFavorite

            if(event.isFavorite){
                favoritesSet.add(event.i)
                sharedPreferences.edit().putStringSet("sport_${event.si}", favoritesSet).apply()
            }
            else{
                favoritesSet.remove(event.i)
                sharedPreferences.edit().putStringSet("sport_${event.si}", favoritesSet).apply()
            }
            notifyItemChanged(position)
        }

        startCountdown(event.tt,
            onTick = { formattedTime ->
                holder.countDownTimeView.text = formattedTime
            },
            onFinish = {
                holder.countDownTimeView.text = "00:00:00"
            }
        )


    }

    override fun getItemCount() = events.size

    fun setEvents(newList : List<EventDTO> ){
        events = newList
        notifyDataSetChanged()
    }


    private fun startCountdown(unixTime: Long, onTick: (String) -> Unit, onFinish: () -> Unit) {

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = unixTime * 1000

        val midnight = Calendar.getInstance()
        midnight.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0)
        midnight.set(Calendar.MILLISECOND, 0)

        val currentTimeMillis = System.currentTimeMillis()
        val midnightMillis = midnight.timeInMillis
        val timeRemainingMillis = midnightMillis - currentTimeMillis

        object : CountDownTimer(timeRemainingMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                val days = millisUntilFinished / (24 * 60 * 60 * 1000)
                val hours = (millisUntilFinished % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000)
                val minutes = ((millisUntilFinished / (1000 * 60)) % 60)
                val seconds = ((millisUntilFinished / 1000) % 60)

                val formattedTime : String = if (days > 0) {
                    String.format("%dd %02d:%02d:%02d", days, hours, minutes, seconds)
                } else {
                    String.format("%02d:%02d:%02d", hours, minutes, seconds)
                }
                //val formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds)
                onTick(formattedTime)
            }

            override fun onFinish() {
                onFinish()
            }
        }.start()
    }

}
