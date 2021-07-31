package fr.shiroe.hotel.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import fr.shiroe.hotel.MainActivity
import fr.shiroe.hotel.R

class RegisterResponseFragment(
    private val context: MainActivity
) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_registerresponse, container, false)

        val buttonHome = view.findViewById<Button>(R.id.backtomenu_button)
        buttonHome.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            context.finish()
            context.startActivity(intent)
        }


        return view
    }
}