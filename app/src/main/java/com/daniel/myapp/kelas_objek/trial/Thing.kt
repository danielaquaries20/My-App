package com.daniel.myapp.kelas_objek.trial

open class Thing(
    private var what: String
) {
    open fun describe() {
        println(what)
    }

    fun setWhat(value: String) {
        what = value
    }

    fun getWhat(): String = what
}