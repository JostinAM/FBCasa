package cr.ac.una.firebase.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseApp
import cr.ac.una.firebase.db.PersonaFirebase
import cr.ac.una.firebase.entity.Persona

class PersonaViewModel : ViewModel() {
    private var _personas: MutableLiveData<List<Persona>> = MutableLiveData()
    var personas: LiveData<List<Persona>> = _personas
    private var  apiService = PersonaFirebase()


//    fun initService(){
//        FirebaseApp.initializeApp()
//
//    }

    fun getPersonas(){
        apiService.getPersonas(_personas)
    }

    fun addPersona(nombre: String){

        val persona = Persona("",nombre)

        apiService.crearPersona(persona)
    }

    fun updatePersona(personaId: String, persona: Persona){
        apiService.modificarPersona(personaId, persona)
    }

    fun deletePersona(personaId: String){
        apiService.deletePersona(personaId)
    }
}