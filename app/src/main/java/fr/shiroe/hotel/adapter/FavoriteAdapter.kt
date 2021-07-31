package fr.shiroe.hotel.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.number.NumberFormatter.with
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import fr.shiroe.hotel.GlideApp
import fr.shiroe.hotel.MainActivity
import fr.shiroe.hotel.R
import fr.shiroe.hotel.model.FavModel
import fr.shiroe.hotel.model.RoomModel

class FavoriteAdapter(
    val context: MainActivity,
    var favList: ArrayList<FavModel>,
    private val layoutId: Int
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    //Boite pour ranger les composants à contrôler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val roomImage = view.findViewById<ImageView>(R.id.image_item_favorite)
        val idImage = view.findViewById<TextView>(R.id.image_item_text_favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = favList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentInfo = favList[position]
        val currentImage = currentInfo.image
        Picasso.get().load(currentImage).into(holder.roomImage)
        holder.idImage?.text = currentInfo.id
    }


}