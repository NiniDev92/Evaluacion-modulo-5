package com.talento.evaluacinmdulo5.model

import java.io.Serializable

data class ShoeItem(
    val id: String,
    val nombre: String,
    val urlImagen: String,
    val precio: Double
) : Serializable
