package com.offline.android.ui.respositries

import android.util.Log
import androidx.lifecycle.LiveData
import com.offline.android.api.auth.networkResponse.SignuoAuthService
import com.offline.android.api.auth.networkResponse.signupresponse.SignUpresponse
import com.offline.android.di.auth.state.AuthViewState
import com.offline.android.di.auth.state.RegistrationFields
import com.offline.android.session.SessionManager
import com.offline.android.ui.DataState
import com.offline.android.ui.JobManager
import com.offline.android.ui.Response
import com.offline.android.ui.ResponseType
import com.offline.android.ui.utils.AbsentLiveData
import com.offline.android.ui.utils.ApiSuccessResponse
import com.offline.android.ui.utils.ErrorHandling.Companion.GENERIC_AUTH_ERROR
import com.offline.android.ui.utils.GenericApiResponse
import kotlinx.coroutines.Job
import javax.inject.Inject

class AuthRepository
@Inject
constructor(
    val signuoAuthService: SignuoAuthService, val sessionManager: SessionManager
) : JobManager("AuthRepository") {
    private val TAG: String = "AppDebug"

    fun attemptToRegistration(
        email: String,
        username: String,
        password: String,
        cconfirmPasword: String,
        lattitude: String,
        longitude: String
    ): LiveData<DataState<AuthViewState>> {
        val registrationFieldErrors =
            RegistrationFields(
                email,
                username,
                password,
                cconfirmPasword
            ).isValidForRegistration()
        if (!registrationFieldErrors.equals(RegistrationFields.RegistrationError.none())) {
            return returnErrorResponse(registrationFieldErrors, ResponseType.Dialog())
        }

        return object : NetworkBoundResource<SignUpresponse, Any, AuthViewState>(
            sessionManager.isConnectedToTheInternet(),
            true,
            true,
            false
        ) {
            // Ignore
            override fun loadFromCache(): LiveData<AuthViewState> {
                return AbsentLiveData.create()
            }

            // Ignore
            override suspend fun updateLocalDb(cacheObject: Any?) {

            }

            // not used in this case
            override suspend fun createCacheRequestAndReturn() {

            }

            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<SignUpresponse>) {
                Log.d(TAG, "handleApiSuccessResponse: ${response}")
                val response = response as SignUpresponse
                if (response.success != false) {

                } else {

                }

            }

            override fun createCall(): LiveData<GenericApiResponse<SignUpresponse>> {
                return signuoAuthService.signUpRequest(
                    email,
                    username,
                    password,
                    lattitude,
                    longitude
                )
            }

            override fun setJob(job: Job) {
                addJob("attemptRegistration", job)
            }

        }.asLiveData()

    }


    private fun returnErrorResponse(
        errorMessage: String,
        responseType: ResponseType
    ): LiveData<DataState<AuthViewState>> {
        Log.d(TAG, "returnErrorResponse: ${errorMessage}")

        return object : LiveData<DataState<AuthViewState>>() {
            override fun onActive() {
                super.onActive()
                value = DataState.error(
                    Response(
                        errorMessage,
                        responseType
                    )
                )
            }
        }
    }

}