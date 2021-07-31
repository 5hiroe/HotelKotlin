package fr.shiroe.hotel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.shiroe.hotel.*
import fr.shiroe.hotel.model.ClientInfoModel

class InputAdapter(
    val context: MainActivity,
    private val layoutId: Int,
    private var clientList: ArrayList<ClientInfoModel>
) : RecyclerView.Adapter<InputAdapter.ViewHolder>() {

    //Boite pour ranger tous les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Spinner
        var clientValueId = view.findViewById<TextView>(R.id.cl_info_value_id)
        var clientValueName = view.findViewById<TextView>(R.id.cl_info_value_name)
        var clientValueEmail = view.findViewById<TextView>(R.id.cl_info_value_email)
        var clientValuePassword = view.findViewById<TextView>(R.id.cl_info_value_password)
        var clientValueAge = view.findViewById<TextView>(R.id.cl_info_value_age)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = clientList.size


    //Mise à jour de chaque modèle avec l'objet en question
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Récupérer les informations de la chambre
        var currentInfo = clientList[position]

        holder.clientValueId?.text = currentInfo.id
        holder.clientValueName?.text = currentInfo.name
        holder.clientValueEmail?.text = currentInfo.email
        holder.clientValuePassword?.text = currentInfo.password
        holder.clientValueAge?.text = currentInfo.pays_id
    }
}