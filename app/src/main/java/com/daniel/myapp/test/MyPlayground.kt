package com.daniel.myapp.test

fun main() {
//    println(add(5, 7))
//    tampilkanIdentitas("Fulan", "SMKN 11 Semarang")
    /*val rangkingNilai = {nilai : Int ->
        when(nilai){
            in 0..60 -> "Cukup"
            in 61..80 -> "Baik"
            in 81..100 -> "Baik Sekali"
            else -> "Tidak Terdefinisi"
        }
    }
    println(rangkingNilai(110))*/

//    val nilai = 9
//    println(nilai)
//    println(nilai.nambah(5))

    /*println(hitung(4, 5, ::add))
    println(hitung(4, 5, ::min))
    println(hitung(4, 5, ::div))
    println(hitung(4, 5, ::mlpy))*/

//    val operasiTambah = operation("+")
//    println(operasiTambah(6, 2))

//    val isLightBolbOn = false
//    val reaction = if(isLightBolbOn) "It's bright" else "I can't see"
//    println(reaction)

    /*val nilai = intArrayOf(89, 78, 90, 87, 95,100)
    nilai.forEach {
        println(it)
    }*/

    /*for(i in 1..10) {
        println(i)
    }*/

    var angka = 0
    /*while (angka <= 2) {
        println("Sukses")
        angka++
    }*/
    do {
        println("Sukses")
        angka++
    } while (angka <= 2)
}

fun operation(type: String) : (Int, Int) -> Int {
    when (type) {
        "+" -> {
            println("Penambahan")
            return ::add
        }
        "-" -> {
            println("Pengurangan")
            return ::min
        }
        "*" -> {
            println("Perkalian")
            return ::mlpy
        }
        "/" -> {
            println("Pembagian")
            return ::div
        }
        else -> {
            println("Tidak Terdefinisi")
            return ::kosong
        }
    }

}

fun kosong(x: Int, y: Int) :Int {
    return 0
}

fun hitung(x: Int, y: Int, operation : (Int, Int) -> Int): Int {
    return operation(x, y)
}

fun min(x: Int, y: Int) : Int {
    return x - y
}

fun add(x:Int, y:Int) : Int {
    return x + y
}

fun mlpy(x: Int, y: Int) : Int {
    return x * y
}

fun div(x: Int, y: Int) : Int {
    return x / y
}

fun Int.nambah(value : Int) : Int{
    return this + value
}



fun tampilkanIdentitas(nama: String, sekolah: String = "SMKN 1 Kendal") {
    println("$nama Bersekolah di $sekolah")
}