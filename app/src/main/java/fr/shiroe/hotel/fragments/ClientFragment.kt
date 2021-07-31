package fr.shiroe.hotel.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.shiroe.hotel.*
import fr.shiroe.hotel.adapter.FavoriteAdapter
import fr.shiroe.hotel.repository.ClientListRepository.Singletonn.clientList
import fr.shiroe.hotel.adapter.InputAdapter
import fr.shiroe.hotel.repository.FavListRepository.SingletonFav.favList
import fr.shiroe.hotel.repository.RoomRepository.Singleton.roomList

class ClientFragment (
    private val context: MainActivity
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater?.inflate(R.layout.fragment_client, container, false)

        //Récupération du recyclerView des informations client
        val clientListRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_cl_info_list)
        clientListRecyclerView?.adapter = InputAdapter(context, R.layout.item_vertical_cl_info, clientList)
        clientListRecyclerView?.layoutManager = LinearLayoutManager(context)

        //Récupération du recyclerView de la liste ds favoris
        val favoriteListRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_cl_fav_list)
        favoriteListRecyclerView?.adapter = FavoriteAdapter(context, favList, R.layout.item_horizontal_favorite)
        favoriteListRecyclerView?.layoutManager = LinearLayoutManager(context)

        return view
    }

}
