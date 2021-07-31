package fr.shiroe.hotel.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.shiroe.hotel.MainActivity
import fr.shiroe.hotel.R
import fr.shiroe.hotel.repository.RoomRepository.Singleton.roomList
import fr.shiroe.hotel.adapter.RoomAdapter

class RoomListFragment(
    private val context: MainActivity
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater?.inflate(R.layout.fragment_room_list, container, false)

        //Récupération du recyclerView
        val roomListRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_room_list)
        roomListRecyclerView?.adapter = RoomAdapter(context, roomList, R.layout.item_vertical_room)
        roomListRecyclerView?.layoutManager = LinearLayoutManager(context)

        return view
    }

}