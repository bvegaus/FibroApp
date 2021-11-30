package com.androidavanzado.fibroapp.model

import java.time.LocalDate

data class Paciente(var email:String, var nombre: String, var nacimiento: LocalDate, var tto:List<String>, var sexo: String, var telefono:Int)

// Hacer el login, una vez que se tenga el email eso será lo que me servirá para identificar al paciente