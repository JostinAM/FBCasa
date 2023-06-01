package cr.ac.una.firebase.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import cr.ac.una.firebase.entity.Persona

class PersonaFirebase {

    private val database = Firebase.database
    private val personasRef = database.getReference("persona")


    fun getPersonas(liveData: MutableLiveData<List<Persona>>) {
        personasRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
//                liveData.clear()
                val personasList = mutableListOf<Persona>()

                for (dataSnapshot in snapshot.children) {
                    val persona = dataSnapshot.getValue(Persona::class.java)
                    persona?.let {
                        personasList.add(it)

                        System.out.println(it.nombre)
                        System.out.println(it.id)

                    }
                }
                // Actualizar la interfaz de usuario con la lista de personas
                // Por ejemplo, puedes usar un RecyclerView para mostrar los datos
                liveData.value = personasList
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error
            }
        })
    }

    fun crearPersona(persona: Persona){
        // Agregar una persona a la base de datos
        //        val persona = Persona("Allam Chaves")
        val personaId = personasRef.push().key
        persona.id = personaId.toString()
        personasRef.child(personaId!!).setValue(persona)
    }

    fun modificarPersona(personaId: String, persona: Persona) {
//        val persona = Persona(nombre)
        personasRef.child(personaId).setValue(persona)
    }

    fun deletePersona(personaId: String) {
        val persona = personasRef.child(personaId)
        persona.removeValue()
    }

}