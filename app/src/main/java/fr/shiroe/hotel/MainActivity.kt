package fr.shiroe.hotel

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import fr.shiroe.hotel.fragments.ClientFragment
import fr.shiroe.hotel.fragments.HomeFragment
import fr.shiroe.hotel.fragments.LoginFragment
import fr.shiroe.hotel.fragments.RoomListFragment
import fr.shiroe.hotel.repository.ClientListRepository
import fr.shiroe.hotel.repository.ConnectRepository
import fr.shiroe.hotel.repository.FavListRepository
import fr.shiroe.hotel.repository.RoomRepository


class MainActivity() : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Création des Shared Preferences (données stockées dans le cache). Ici : id si connecté
        val sharedPreferences = this.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val connected:String? = sharedPreferences.getString("STRING_KEY", null)

        //Chargement du fragment "HomeFragment" à l'ouverture de l'activité
        loadFragment(HomeFragment(this), R.string.bottom_home_item)

        //Charger la liste des favoris
        val repoFav = FavListRepository(this)
        //Mise à jour de la liste des favoris
        repoFav.updateData()

        //charger le roomrepository
        val repo = RoomRepository(this)
        //mettre à jour la liste des chambres
        repo.updateData()

        //Charger la liste des Informations Client
        val repoCl = ClientListRepository(this)
        //Mise à jour de la liste des Informations Client
        repoCl.updateData()

        //Chargement de la vue du bouton Login/Logout
        val loginButtonView = findViewById<Button>(R.id.login_button_item_main)
        //Mise à jour du bouton : si on n'est pas connecté, on affiche le bouton Login, sinon, le bouton logout
        if (connected == "0") {
            //On affiche "Login" sur le bouton
            loginButtonView?.text = "Login"
            //Au clic, on charge le LoginFragment
            loginButtonView.setOnClickListener {
                loadFragment(LoginFragment(this), R.string.top_button_login_item)

            }
        }else{
            //On affiche "Logout" sur le bouton
            loginButtonView?.text = "Logout"
            //Au clic, on modifie la valeur de l'id dans le cache et on recharge l'activité (= déconnexion)
            loginButtonView.setOnClickListener {
                val editor = sharedPreferences.edit()
                editor.apply{
                    putString("STRING_KEY", "0")
                }.apply()
                val intent = Intent(this, MainActivity::class.java)
                this.finish()
                this.startActivity(intent)
            }
        }

        //Chargement de la barre de navigation
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home_page -> {
                    loadFragment(HomeFragment(this), R.string.bottom_home_item)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.rooms_page -> {
                    loadFragment(RoomListFragment(this), R.string.bottom_rooms_item)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.clients_page -> {
                    loadFragment(ClientFragment(this), R.string.bottom_client_item)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
    }

    //Fonction pour charger un Fragment
    private fun loadFragment(fragment: Fragment, string: Int){

        //Actualiser le titre de la page
        findViewById<TextView>(R.id.page_title).text = resources.getString(string)

        //Injecter le fragment dans notre boite (fragment_container)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun loadBigFragment(fragment: Fragment, string: Int){
        findViewById<TextView>(R.id.page_title).text = resources.getString(string)

        //Injecter le fragment dans notre boite (fragment_container)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}