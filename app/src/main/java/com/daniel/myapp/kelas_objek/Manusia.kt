package com.daniel.myapp.kelas_objek

class Manusia(
    private var nama: String,
    private var usia: Int,
    private var tinggi: Float,
    private var berat: Float
) : Sesuatu("Manusia") {

    override fun describe() {
        println("Saya Manusia berusia $usia, nama saya $nama, dengan tinggi badan $tinggi Cm dan berat badan $berat Kg")
    }

    fun getNama(): String {
        return nama
    }

    fun setNama(value: String) {
        nama = value
    }

    fun getUsia(): Int {
        return usia
    }

    fun setUsia(value: Int) {
        usia = value
    }

    fun getTinggi(): Float {
        return tinggi
    }

    fun setTinggi(value: Float) {
        tinggi = value
    }

    fun getBerat(): Float {
        return berat
    }

    fun setBerat(value: Float) {
        berat = value
    }

}