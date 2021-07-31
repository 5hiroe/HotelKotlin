package fr.shiroe.hotel

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import fr.shiroe.hotel.adapter.RoomAdapter
import fr.shiroe.hotel.model.RoomModel
import fr.shiroe.hotel.repository.VolleySingleton
import org.json.JSONException
import org.json.JSONObject

class RoomPopup(
    private val adapter: RoomAdapter,
    private val currentRoom: RoomModel,
    private val context: MainActivity
) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_rooms_details)
        setupComponents()
        setupCloseButton()
        setupFavoriteButton()
    }

    private fun setupComponents(){
        //Actualiser l'image de la chambre
        val roomImage = findViewById<ImageView>(R.id.popup_image_room)
        Glide.with(adapter.context).load(Uri.parse(currentRoom.image)).into(roomImage)

        //Actualiser le nom de la chambre
        findViewById<TextView>(R.id.popup_title_room).text = currentRoom.id

        //Actualiser la description
        findViewById<TextView>(R.id.popup_one_desc_room).text = currentRoom.description

        //Actualiser le prix
        findViewById<TextView>(R.id.popup_two_desc_room).text = currentRoom.prix

        //Actualiser la capacité
        findViewById<TextView>(R.id.popup_ter_desc_room).text = currentRoom.capacite
    }

    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener {
            //Fermer la fenêtre
            dismiss()
        }
    }

    private fun setupFavoriteButton(){
        findViewById<ImageView>(R.id.fav_button).setOnClickListener{
            updateData()
        }
    }

    private fun updateData(){
        //chargement du cache
        val sharedPreferences = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val connected:String? = sharedPreferences.getString("STRING_KEY", null)

        //Création du JSONObject contenant l'id de la personne connecté (si elle est connectée)
        val params = HashMap<String, String>()
        params["id"] = connected.toString()
        params["chid"] = currentRoom.id
        val jsonObject = JSONObject(params as Map<String, String>)

        var url = "https://www.dorian-roulet.com/testStage-master/mobileApp/addtofav.php"

        //Initialisation de la requête
        var stringRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            Response.Listener { response ->
                try {
                    println("ok $response")
                    val intent = Intent(context, MainActivity::class.java)
                    context.finish()
                    context.startActivity(intent)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },  Response.ErrorListener{error->
                println("Erreur wlh")
                error.printStackTrace()
            }
        )
        //Envoi de la requête
        var req = Volley.newRequestQueue(context)
        req.add(stringRequest)

    }
}