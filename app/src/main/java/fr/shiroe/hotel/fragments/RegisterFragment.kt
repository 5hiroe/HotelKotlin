package fr.shiroe.hotel.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import fr.shiroe.hotel.MainActivity
import fr.shiroe.hotel.R
import fr.shiroe.hotel.repository.VolleySingleton
import org.json.JSONObject
import java.lang.Exception

class RegisterFragment(
    private val context: MainActivity
) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_register, container, false)

        val nameEdit = view.findViewById<EditText>(R.id.name_edit_holder)
        val pnameEdit = view.findViewById<EditText>(R.id.pname_edit_holder)
        val mailEdit = view.findViewById<EditText>(R.id.mail_edit_holder)
        val cmailEdit = view.findViewById<EditText>(R.id.cmail_edit_holder)
        val pwdEdit = view.findViewById<EditText>(R.id.pwd_edit_holder)
        val cpwdEdit = view.findViewById<EditText>(R.id.cpwd_edit_holder)
        val adresseEdit = view.findViewById<EditText>(R.id.adresse_edit_holder)
        val cpEdit = view.findViewById<EditText>(R.id.cp_edit_holder)
        val townEdit = view.findViewById<EditText>(R.id.town_edit_holder)
        val paysEdit = view.findViewById<EditText>(R.id.pays_edit_holder)
        val civEdit = view.findViewById<Spinner>(R.id.civ_spinner_holder)
        val responseHolder = view.findViewById<TextView>(R.id.response_holder)

        val registerButton = view.findViewById<Button>(R.id.button_confirm_register)
        registerButton.setOnClickListener {
            val name = nameEdit.text.toString()
            val pname = pnameEdit.text.toString()
            val mail = mailEdit.text.toString()
            val cmail = cmailEdit.text.toString()
            val pwd = pwdEdit.text.toString()
            val cpwd = cpwdEdit.text.toString()
            val adresse = adresseEdit.text.toString()
            val cp = cpEdit.text.toString()
            val town = townEdit.text.toString()
            val pays = paysEdit.text.toString()
            val civ = civEdit.selectedItem.toString()

            val params = HashMap<String, String>()
            params["name"] = name
            params["pname"] = pname
            params["mail"] = mail
            params["cmail"] = cmail
            params["pwd"] = pwd
            params["cpwd"] = cpwd
            params["adresse"] = adresse
            params["cp"] = cp
            params["town"] = town
            params["pays"] = pays
            params["civ"] = civ
            val jsonObject = JSONObject(params as Map<String, String>)
            val url = "https://www.dorian-roulet.com/testStage-master/mobileApp/register.php"

            val request = JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                Response.Listener { response ->
                    try {
                        val reponse = response["response"].toString()

                        if (reponse == "inscrit"){
                            println("Inscription confirmée")
                            val transaction = activity?.supportFragmentManager?.beginTransaction()
                            transaction?.replace(R.id.fragment_container, RegisterResponseFragment(context))
                            transaction?.disallowAddToBackStack()
                            transaction?.commit()
                        } else if (reponse == "taken"){
                            println("Mail déjà utilisé")
                            responseHolder.text = "Ce mail est déjà utilisé"
                        } else if (reponse == "mdp"){
                            println("Mdp ou Cmdp différents")
                            responseHolder.text = "Les mots de passes sont différents"
                        } else if (reponse == "mail"){
                            println("Mail et CMail différents")
                            responseHolder.text = "Les mails sont différents"
                        } else if (reponse == "data vide"){
                            println("data vide")
                            responseHolder.text = "Veuillez remplir tous les champs"
                        }
                    } catch (e: Exception){
                        println("exception lors de l'inscription : $e")
                    }

                }, Response.ErrorListener {
                    println("Erreur")
                })
            request.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f)

            VolleySingleton.getInstance(context).addToRequestQueue(request)


        }

        return view
    }
}