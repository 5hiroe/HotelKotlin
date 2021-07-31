package fr.shiroe.hotel.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.shiroe.hotel.*
import fr.shiroe.hotel.adapter.CalendarAdapter
import fr.shiroe.hotel.adapter.CheckinAdapter
import fr.shiroe.hotel.model.CalendarTitleModel
import fr.shiroe.hotel.model.CheckinModel


class HomeFragment(
    private val context: MainActivity
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater?.inflate(R.layout.fragment_home, container, false)

        val calendarList = arrayListOf<CalendarTitleModel>()

        calendarList.add(
            CalendarTitleModel(
                "Arrivée",
                "Glissez vers la droite ᐅ"
            )
        )
        calendarList.add(
            CalendarTitleModel(
                "Départ",
                "ᐊ Glissez vers la gauche"
            )
        )

        val checkInList = arrayListOf<CheckinModel>()

        checkInList.add(
            CheckinModel(
                "Réserver"
            )
        )

        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_calendars_home)
        horizontalRecyclerView.adapter = CalendarAdapter(calendarList)

        val horizontalbutonRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_checkin_home)
        horizontalbutonRecyclerView.adapter = CheckinAdapter(checkInList)


        return view
    }
}