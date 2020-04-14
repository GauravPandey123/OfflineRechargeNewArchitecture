package com.offline.android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.offline.android.di.auth.state.AuthStateEvent
import com.offline.android.di.auth.state.AuthStateEvent.*
import com.offline.android.di.auth.state.AuthViewState
import com.offline.android.di.auth.state.LoginFields
import com.offline.android.di.auth.state.RegistrationFields
import com.offline.android.di.auth.state.RegistrationFields.*
import com.offline.android.ui.baseclass.BaseViewModel
import com.offline.android.ui.respositries.AuthRepository
import javax.inject.Inject

class AuthViewModel
@Inject
constructor(
    val authRepository: AuthRepository
) : BaseViewModel<AuthStateEvent, AuthViewState>() {
    override fun handleStateEvent(stateEvent: AuthStateEvent): LiveData<DataState<AuthViewState>> {
         when (stateEvent) {

            is RegisterAttemptEvent -> {
                return authRepository.attemptToRegistration(
                    stateEvent.email,
                    stateEvent.username,
                    stateEvent.password,
                    stateEvent.confirm_password,
                    stateEvent.lattitude,
                    stateEvent.logitude

                )
            }

            is None -> {
                return liveData {
                    emit(
                        DataState(
                            null,
                            Loading(false),
                            null
                        )
                    )
                }
            }
        }
    }


    override fun initNewViewState(): AuthViewState {
        return AuthViewState()
    }

    fun setRegistrationFields(registrationFields: RegistrationFields) {
        val update = getCurrentViewStateOrNew()
        if (update.registrationFields == registrationFields) {
            return
        }
        update.registrationFields = registrationFields
        setViewState(update)
    }

//    fun setLoginFields(loginFields: LoginFields) {
//        val update = getCurrentViewStateOrNew()
//        if (update.loginFields == loginFields) {
//            return
//        }
//        update.loginFields = loginFields
//        setViewState(update)
//    }


    fun cancelActiveJobs() {
        handlePendingData()
        authRepository.cancelActiveJobs()
    }

    fun handlePendingData() {
        setStateEvent(AuthStateEvent.None())
    }

    override fun onCleared() {
        super.onCleared()
        cancelActiveJobs()
    }
}