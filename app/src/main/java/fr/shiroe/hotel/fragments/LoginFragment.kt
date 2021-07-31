package fr.shiroe.hotel.fragments

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.shiroe.hotel.MainActivity
import fr.shiroe.hotel.R
import fr.shiroe.hotel.repository.ConnectRepository
import fr.shiroe.hotel.repository.VolleySingleton
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class LoginFragment(
    private val context: MainActivity
) : Fragment () {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_login, container, false)


        val connectButton = view.findViewById<Button>(R.id.login_button_item)
        connectButton.setOnClickListener{
            sendHtmlRequest(view)
        }

        val registerButton = view.findViewById<Button>(R.id.register_button_item)
        registerButton.setOnClickListener{
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container, RegisterFragment(context))
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }


        return view
    }

    private fun sendHtmlRequest(view: View){

        val emailreq = view?.findViewById<EditText>(R.id.editText_email)
        val pwdreq = view?.findViewById<EditText>(R.id.editText_password)
        val email = emailreq.text.toString()
        val pwd = pwdreq.text.toString()
        var url = "https://www.dorian-roulet.com/testStage-master/mobileApp/testpostone.php"

        val params = HashMap<String, String>()
        params["mail"] = email
        params["pwd"] = pwd
        val jsonObject = JSONObject(params as Map<String, String>)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            Response.Listener { response ->
                try {
                    val id:String = response.getString("id")
                    val connect = response.getString("connect")
                    view.findViewById<TextView>(R.id.error_login)?.text = ("Ca marche $id $connect $response")
                    saveData(id, context)
                    val intent = Intent(context, MainActivity::class.java)
                    context.finish()
                    context.startActivity(intent)

                } catch (e:Exception){
                    view.findViewById<TextView>(R.id.error_login)?.text = ("Erreur $e $response")
                }

        }, Response.ErrorListener {
            view.findViewById<TextView>(R.id.error_login)?.text = ("Erreur $jsonObject")
            println("Erreur")
        })
        request.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f)

        VolleySingleton.getInstance(context).addToRequestQueue(request)

    }

    private fun saveData(id:String, context: Context){
        val insertText = id.toString()
        //Enregistrer l'id dans le cache si connexion fonctionne
        val sharedPreferences = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("STRING_KEY", insertText)
        }.apply()

        Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show()
    }

}