package com.daniel.myapp.middle.counter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crocodic.core.base.viewmodel.CoreViewModel
import com.daniel.myapp.middle.aa_shared_pref.MiddleSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(private val session: MiddleSession) : CoreViewModel() {

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

    override fun apiLogout() {}

    override fun apiRenewToken() {}
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