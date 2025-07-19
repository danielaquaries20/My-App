package com.daniel.myapp.kelas_objek

import com.daniel.myapp.kelas_objek.trial.Calculation
import com.daniel.myapp.kelas_objek.trial.Human
import com.daniel.myapp.kelas_objek.trial.Thing

/*Materi
1. Contoh class pakai Calculate
2. Contoh Class lagi contoh lain
3. Buat Getter dan Setter untuk class tersebut
4. Buat Inheritance
5. Buat Data class Person
*/

fun main() {
    val manusia = Sesuatu("Manusia")
    manusia.describe()
    manusia.setApa("Introvert")
    println("Ini adalah manusia ${manusia.getApa()}")

    val budi = Manusia("Budi", 17, 168.9f, 77.7f)
    budi.describe()

    println("========================================")
    val saya = Person(
        "Daniel",
        "Laki-Laki",
        "Semarang",
        "SMKN 11 Semarang",
        "Bermain Game",
        "-",
        "@daniel"
    )
    saya.describe()
    println("========================================")

    val dia = saya.copy(name = "Vita", gender = "Perempuan", instagram = "@vita")
    dia.describe()

//    CRUD
    //CREATE
    //READ
    //UPDATE
    //DELETE

    //

}