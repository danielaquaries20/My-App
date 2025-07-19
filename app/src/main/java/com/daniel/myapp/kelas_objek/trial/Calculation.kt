package com.daniel.myapp.kelas_objek.trial

class Calculation(private val number1: Float, private val number2: Float) {
    fun add(): Float {
        return number1 + number2
    }

    fun min(reverse: Boolean = false): Float {
        return if (reverse) {
            number2 - number1
        } else {
            number1 - number2
        }
    }

    fun multiplication(): Float {
        return number1 * number2
    }

    fun division(reverse: Boolean = false): Float {
        return if (reverse) {
            number2 / number1
        } else {
            number1 / number2
        }
    }
}