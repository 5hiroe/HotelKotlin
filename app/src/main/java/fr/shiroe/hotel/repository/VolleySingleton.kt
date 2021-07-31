package fr.shiroe.hotel.repository

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import fr.shiroe.hotel.MainActivity

//Chargement de la requête en Singleton afin de palier à l'asynchronicité

class VolleySingleton constructor(context: Context) {
    companion object{
        @Volatile
        private var INSTANCE: VolleySingleton? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: VolleySingleton(context).also{
                    INSTANCE = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }
    fun <T> addToRequestQueue(req: Request<T>){
        requestQueue.add(req)
    }
}