package cr.ac.una.firebase

import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController

import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import cr.ac.una.firebase.databinding.ActivityMainBinding
import cr.ac.una.firebase.entity.Persona
import cr.ac.una.firebase.viewModel.PersonaViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var personasRef: DatabaseReference
    //
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var personaViewModel: PersonaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        //

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            navController.navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        var personaViewModel = ViewModelProvider(this).get(PersonaViewModel::class.java)

        //

//        setContentView(R.layout.activity_main)
        // Inicializar Firebase
//        FirebaseApp.initializeApp(this)
//
//        // Obtener referencia a la base de datos "personas"
//        val database = FirebaseDatabase.getInstance()
//        // Obtener referencia a la tabla "persona"
//        personasRef = database.getReference("persona")
//
//        // Agregar una persona a la base de datos
//
//        val personaId = personasRef.push().key
//        val persona = Persona(personaId.toString(),"Jostin ")
//        personasRef.child(personaId!!).setValue(persona).addOnCompleteListener{
//            Toast.makeText(this, "Inserted", Toast.LENGTH_LONG).show()
//        }.addOnFailureListener{
//            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}