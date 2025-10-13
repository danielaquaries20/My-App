package com.daniel.myapp.app_friend.base

import com.crocodic.core.base.viewmodel.CoreViewModel

open class BaseViewModel : CoreViewModel() {
    override fun apiLogout() {}

    override fun apiRenewToken() {}
}