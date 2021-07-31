package fr.shiroe.hotel.repository

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.shiroe.hotel.MainActivity
import fr.shiroe.hotel.repository.ClientListRepository.Singletonn.clientList
import fr.shiroe.hotel.model.ClientInfoModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ClientListRepository (private val context: MainActivity) {

    object Singletonn{
        //créer une liste des chambres
        val clientList = arrayListOf<ClientInfoModel>()
    }

    fun updateData(){

        val sharedPreferences = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val connected:String? = sharedPreferences.getString("STRING_KEY", null)

        //Absorber les données récupérées dans la db pour les donner à la lste des chambres
        if (connected.toString() != "0") {

            val params = HashMap<String, String>()
            params["id"] = connected.toString()
            val jsonObject = JSONObject(params as Map<String, String>)

            var url = "https://www.dorian-roulet.com/testStage-master/mobileApp/clientInfo.php"
            var stringRequest = JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                Response.Listener { response ->
                    try {
                        parseData(response)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },  Response.ErrorListener{
                    println("Erreur wlh")
                }
            )

            VolleySingleton.getInstance(context).addToRequestQueue(stringRequest)
        }
        else if (connected.toString() == "0"){
            clientList.clear()
        }
    }

    private fun parseData(response: JSONObject){

        clientList.clear()
        for (i in 0 until 1){
            val user = ClientInfoModel(
                response.getString("id"),
                response.getString("nom"),
                response.getString("mail"),
                "**********",
                response.getString("pays_id")
            )
            clientList.add(user)
        }
    }

}