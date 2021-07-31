package fr.shiroe.hotel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.shiroe.hotel.model.CalendarTitleModel
import fr.shiroe.hotel.R

class CalendarAdapter(
    val calendarList : ArrayList<CalendarTitleModel>
) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val calendarItem = view.findViewById<DatePicker>(R.id.calendar_item)
        val titleItem = view.findViewById<TextView>(R.id.item_calendar_title)
        val slideItem = view.findViewById<TextView>(R.id.slide_direction_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_horizontal_calendar, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = 2

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCalendar = calendarList[position]

        holder.titleItem?.text = currentCalendar.title
        holder.slideItem?.text = currentCalendar.slide
    }
}