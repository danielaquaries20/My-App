package com.daniel.myapp.middle.counter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daniel.myapp.middle.aa_shared_pref.MiddleSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CounterViewModel(private val session: MiddleSession) : ViewModel() {

    private val _count = MutableStateFlow(session.getCount())
    val count: StateFlow<Int> = _count

    fun increment() {
        val newValue = _count.value + 1
        _count.value = newValue
        // Setelah nilai berubah, ViewModel meminta repository untuk menyimpannya.
        session.saveCount(newValue)
    }

    fun decrement() {
        val newValue = _count.value - 1
        _count.value = newValue
        session.saveCount(newValue)
    }
}

class CounterViewModelFactory(private val session: MiddleSession) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CounterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CounterViewModel(session) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}