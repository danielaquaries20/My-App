package com.daniel.myapp.middle.mvvm_counter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crocodic.core.base.viewmodel.CoreViewModel
import com.daniel.myapp.middle.aa_shared_pref.MiddleSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class MvvmCounterViewModel @Inject constructor(private val middleSession: MiddleSession) : CoreViewModel() {

    private val _count = MutableStateFlow(middleSession.getCount())
    val count: StateFlow<Int> = _count

    fun increment() {
        _count.value += 1
        middleSession.saveCount(_count.value)
    }

    fun decrement() {
        _count.value -= 1
        middleSession.saveCount(_count.value)
    }

    override fun apiLogout() {}

    override fun apiRenewToken() {}
}

/*
class Factory(private val middleSession: MiddleSession): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MvvmCounterViewModel(middleSession) as T
    }
}*/
