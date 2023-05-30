package cr.ac.una.firebase.entity

data class Persona( var id: String , var nombre : String) {
    constructor() : this("", "") {
        // Constructor sin parámetros
    }

}