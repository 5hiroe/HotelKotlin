package fr.shiroe.hotel.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.shiroe.hotel.*
import fr.shiroe.hotel.model.RoomModel
import fr.shiroe.hotel.repository.FavListRepository.SingletonFav.favList
import fr.shiroe.hotel.repository.FavListRepository.SingletonFav.favListId
import java.util.Arrays
import java.util.stream.IntStream
import java.util.stream.StreamSupport.stream

//import fr.shiroe.hotel.roomPopup

class RoomAdapter(
    val context: MainActivity,
    private val roomList: ArrayList<RoomModel>,
    private val layoutId: Int
) : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {

    //Boite pour ranger tous les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Spinner
        val roomImage = view.findViewById<ImageView>(R.id.image_item_room)
        val roomName = view.findViewById<TextView>(R.id.room_title)
        val roomDesc = view.findViewById<TextView>(R.id.room_desc)
        val roomPrice = view.findViewById<TextView>(R.id.room_price)
        val starImage = view.findViewById<ImageView>(R.id.item_fav_star)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = roomList.size


    //Mise à jour de chaque modèle avec l'objet en question
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            //Récupérer les informations de la chambre
            val currentRoom = roomList[position]

            //Utiliser Glide pour récupérer l'image à partir de son lien (+ajout au composant)
            Glide.with(context).load(Uri.parse(currentRoom.image)).into(holder.roomImage)

            //Mettre à jour le numéro de chambre
            holder.roomName?.text = currentRoom.id

            //Mettre à jour la description de la chambre
            holder.roomDesc?.text = currentRoom.description

            //Mettre à jour le prix de la chambre
            holder.roomPrice?.text = currentRoom.prix

            //Interaction lors du clic sur une chambre
            holder.itemView.setOnClickListener {
                //Afficher la popup
                RoomPopup(this, currentRoom, context).show()
            }
            if (currentRoom.fav == "oui"){
                holder.starImage.setImageResource(R.drawable.ic_star)
            }else{
                holder.starImage.setImageResource(R.drawable.ic_star_empty)
            }


//        for (i in 0 until favList.size){
//            val currentFav = favList[i]
//            val currentIdFav = currentFav.id
//            val currentIdRoom = currentRoom.id
//
//            if (currentIdFav == currentIdRoom){
//                holder.starImage.setImageResource(R.drawable.ic_star)
//            }
//        }
    }
}


