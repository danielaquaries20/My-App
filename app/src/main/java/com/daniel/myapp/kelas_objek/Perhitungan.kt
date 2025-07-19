package com.daniel.myapp.kelas_objek

class Perhitungan(
    private val angka1: Float,
    private val angka2: Float
) {
    fun penambahan(): Float {
        return angka1 + angka2
    }

    fun pengurangan(): Float {
        return angka1 - angka2
    }

    fun perkalian(): Float {
        return angka1 * angka2
    }

    fun pembagian(): Float {
        return angka1 / angka2
    }
}