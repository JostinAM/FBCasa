package cr.ac.una.firebase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import cr.ac.una.firebase.entity.Persona

class MainActivity : AppCompatActivity() {

    private lateinit var personasRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar Firebase
        FirebaseApp.initializeApp(this)

        // Obtener referencia a la base de datos "personas"
        val database = FirebaseDatabase.getInstance()
        // Obtener referencia a la tabla "persona"
        personasRef = database.getReference("persona")

        // Agregar una persona a la base de datos

        val personaId = personasRef.push().key
        val persona = Persona(personaId.toString(),"Jostin ")
        personasRef.child(personaId!!).setValue(persona).addOnCompleteListener{
            Toast.makeText(this, "Inserted", Toast.LENGTH_LONG).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }
    }
}