package fr.shiroe.hotel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.shiroe.hotel.R
import fr.shiroe.hotel.model.CheckinModel

class CheckinAdapter (
    val checkinList: ArrayList<CheckinModel>
): RecyclerView.Adapter<CheckinAdapter.ViewHolder>() {

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val checkInValue = view.findViewById<TextView>(R.id.check_in_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_horizontal_checkin, parent, false)

        return CheckinAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCheckin = checkinList[position]

        holder.checkInValue?.text = currentCheckin.value
    }
}