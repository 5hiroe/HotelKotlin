package fr.shiroe.hotel.repository

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import fr.shiroe.hotel.MainActivity
import fr.shiroe.hotel.model.FavModel
import fr.shiroe.hotel.repository.FavListRepository.SingletonFav.favList
import fr.shiroe.hotel.repository.FavListRepository.SingletonFav.favListId
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap

class FavListRepository (private val context: MainActivity) {

    object SingletonFav{
        val favList =  arrayListOf<FavModel>()
        var favListId = arrayOf("str1")
    }

    fun updateData(){

        val sharedPreferences = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val connected:String? = sharedPreferences.getString("STRING_KEY", null)

        if (connected.toString() != "0"){

            val params = HashMap<String, String>()
            params["id"] = connected.toString()
            val jsonArray = JSONArray(listOf(params))

            val url = "https://www.dorian-roulet.com/testStage-master/mobileApp/favlistidbyid.php"
            val request = JsonArrayRequest(Request.Method.POST, url, jsonArray,
            Response.Listener {
                response ->
                try {
                    perseData(response)
                } catch (e: JSONException){
                    e.printStackTrace()
                }
            }, Response.ErrorListener {
                    println("Erreur dude")
                }
            )

            VolleySingleton.getInstance(context).addToRequestQueue(request)
        } else if (connected.toString() == "0"){
            favList.clear()
        }
    }

    private fun perseData(response: JSONArray){

        favList.clear()

        for (i in 0 until response.length()){

            val responseJSON = response.getJSONObject(i)
            val responseStringImage = responseJSON.getString("image")
            val responseStringId = responseJSON.getString("id")
            val responseReplaced = responseStringImage.replace("\\/", "/", ignoreCase = false)
            val fav = FavModel(
                responseStringId,
                responseReplaced
            )
            favList.add(fav)
            favListId = append(favListId, responseStringId)
        }

    }

    fun append(arr: Array<String>, element: String): Array<String> {
        val list: MutableList<String> = arr.toMutableList()
        list.add(element)
        return list.toTypedArray()
    }

}