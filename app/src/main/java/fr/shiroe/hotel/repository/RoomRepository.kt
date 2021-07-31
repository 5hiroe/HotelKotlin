package fr.shiroe.hotel.repository

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import fr.shiroe.hotel.MainActivity
import fr.shiroe.hotel.repository.RoomRepository.Singleton.roomList
import fr.shiroe.hotel.model.RoomModel
import org.json.JSONArray
import org.json.JSONException

class RoomRepository (private val context: MainActivity) {

    object Singleton{
        //créer une liste des chambres
        val roomList = arrayListOf<RoomModel>()
    }

    fun updateData() {
        //Chargement du cache
        val sharedPreferences = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val connected: String? = sharedPreferences.getString("STRING_KEY", null)

        //Initialisation de la requête
        var req = Volley.newRequestQueue(context)
        //Si l'utilisateur est connecté
        if (connected.toString() != "0") {
            //Récupération des informations des chambres
            var url = "https://www.dorian-roulet.com/testStage-master/mobileApp/requestrooms.php"
            val params = HashMap<String, String>()
            params["id"] = connected.toString()
            val jsonArray = JSONArray(listOf(params))
            var stringRequest = JsonArrayRequest(
                Request.Method.POST, url, jsonArray,
                Response.Listener { response ->
                    try {
                        parseDatas(response)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, null
            )
            //Chargement de la requête
            req.add(stringRequest)
        } //Si l'utilisateur n'est pas connecté
        else {
            //Récupération des informations des chambres
            val params = HashMap<String, String>()
            params["id"] = "0"
            val jsonArray = JSONArray(listOf(params))
            var url = "https://www.dorian-roulet.com/testStage-master/mobileApp/requestrooms.php"
            var stringRequest = JsonArrayRequest(
                Request.Method.POST, url, jsonArray,
                Response.Listener { response ->
                    try {
                        parseDatas(response)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, null
            )
            //Chargement de la requête
            req.add(stringRequest)
        }
    }
    //Fonction pour charger les données récupérées dans la data de la liste des Chambres
    private fun parseDatas(response: JSONArray){
        roomList.clear()
        for (i in 0 until response.length()){
            var currentObject = response.getJSONObject(i)
            val room = RoomModel(
                currentObject.getString("id"),
                currentObject.getString("description"),
                currentObject.getString("image"),
                currentObject.getString("prix"),
                currentObject.getString("capacite") + " personnes",
                currentObject.getString("fav")
            )
            roomList.add(room)
        }
    }


}

