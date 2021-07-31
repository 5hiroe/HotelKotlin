package fr.shiroe.hotel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.shiroe.hotel.R
import fr.shiroe.hotel.model.SpinnerModel

class SpinnerAdapter(
    val spinnerList : ArrayList<SpinnerModel>
) : RecyclerView.Adapter<SpinnerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val titleItem = view.findViewById<TextView>(R.id.title_spinner_item)
        val inSpinnerItem = view.findViewById<Spinner>(R.id.spinner_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_horizontal_spinner, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = 2

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSpinner = spinnerList[position]

        holder.titleItem?.text = currentSpinner.title

    }

}