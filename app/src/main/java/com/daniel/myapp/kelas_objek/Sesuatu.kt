package com.daniel.myapp.kelas_objek

open class Sesuatu(
    private var apa: String = ""
) {
    open fun describe() {
        println("Ini adalah $apa")
    }

    fun getApa() : String {
        return apa
    }

    fun setApa(value: String) {
        apa = value
    }
}