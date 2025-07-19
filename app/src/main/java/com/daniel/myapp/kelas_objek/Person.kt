package com.daniel.myapp.kelas_objek

data class Person(
    private val name: String,
    private val gender: String,
    private val hometown: String,
    private val school: String,
    private val hobby: String,
    private val facebook: String,
    private val instagram: String,
) {
    fun describe() {
        println("Nama: $name")
        println("Gender: $gender")
        println("Kota: $hometown")
        println("Sekolah: $school")
        println("Hobi: $hobby")
        println("FB: $facebook")
        println("IG: $instagram")
    }
}
