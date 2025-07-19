package com.daniel.myapp.kelas_objek.trial

class Human(
    private var humanName: String,
    private var age: Int,
    private var weight: Int,
    private var height: Int,
) : Thing("Manusia") {

    override fun describe() {
        println("Saya adalah Manusia yang berumur $age, nama saya $humanName, tinggi $height Cm dan berat $weight Kg")
    }
}